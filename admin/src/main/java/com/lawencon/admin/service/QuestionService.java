package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.admin.dao.ApplicantDao;
import com.lawencon.admin.dao.AssessmentDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.QuestionAssessmentDao;
import com.lawencon.admin.dao.QuestionDao;
import com.lawencon.admin.dao.QuestionOptionDao;
import com.lawencon.admin.dao.QuestionTopicDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.question.QuestionAssessmentInsertReqDto;
import com.lawencon.admin.dto.question.QuestionAssessmentInsertSeekerReqDto;
import com.lawencon.admin.dto.question.QuestionAssessmentSubmittedReqDto;
import com.lawencon.admin.dto.question.QuestionResDto;
import com.lawencon.admin.dto.question.QuestionTopicInsertReqDto;
import com.lawencon.admin.dto.question.QuestionTopicInsertSeekerReqDto;
import com.lawencon.admin.model.Applicant;
import com.lawencon.admin.model.Assessment;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.Question;
import com.lawencon.admin.model.QuestionAssessment;
import com.lawencon.admin.model.QuestionOption;
import com.lawencon.admin.model.QuestionTopic;
import com.lawencon.base.ConnHandler;
import com.lawencon.util.GeneratorUtil;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuestionAssessmentDao questionAssessmentDao;
    @Autowired
    QuestionTopicDao qTopicDao;
    @Autowired
    QuestionOptionDao qOptionDao;
    @Autowired
    JobVacancyDao jobVacancyDao;
    @Autowired
    ApplicantDao applicantDao;
    @Autowired
    AssessmentDao assessmentDao;
    @Autowired
    RestTemplate restTemplate;

    public InsertResDto createQuestion(QuestionTopicInsertReqDto data) {
        ConnHandler.begin();
        final InsertResDto response = new InsertResDto();

        QuestionTopic topic = new QuestionTopic();
        topic.setTitle(data.getTopicTitle());
        topic.setCode(GeneratorUtil.generateCode());
        QuestionTopic createdTopic = qTopicDao.save(topic);

        for (int i = 0; i < data.getQuestions().size(); i++) {
            Question question = new Question();
            question.setQuestion(data.getQuestions().get(i).getQuestionDesc());
            question.setQuestionTopic(createdTopic);
            Question createdQuestion = questionDao.save(question);
            for (int j = 0; j < data.getQuestions().get(i).getOptions().size(); j++) {
                QuestionOption option = new QuestionOption();
                option.setAnswer(data.getQuestions().get(i).getOptions().get(j).getAnswerText());
                option.setIsCorrect(data.getQuestions().get(i).getOptions().get(j).getIsCorrect());
                option.setQuestionId(createdQuestion);
                qOptionDao.save(option);
            }
        }
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<QuestionTopicInsertSeekerReqDto> reqBody = new HttpEntity<QuestionTopicInsertSeekerReqDto>(
                    new QuestionTopicInsertSeekerReqDto(data.getTopicTitle(), createdTopic.getCode(),
                            data.getQuestions()),
                    headers);
            ResponseEntity<InsertResDto> res = restTemplate.exchange("http://localhost:8081/seeker/questions",
                    HttpMethod.POST, reqBody, InsertResDto.class);
            if (res.getStatusCode().equals(HttpStatus.CREATED)) {
                ConnHandler.commit();
                response.setId(createdTopic.getId());
                response.setMessage("Topic question sukses dibuat");
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            ConnHandler.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        }
        return response;
    }

    public List<QuestionResDto> getAll() {
        List<QuestionResDto> responses = new ArrayList<>();
        questionDao.getAll(Question.class).forEach(c -> {
            QuestionResDto response = new QuestionResDto();
            response.setId(c.getId());
            response.setQuestionDesc(c.getQuestion());
            responses.add(response);
        });

        return responses;
    }

    public InsertResDto insertAssessment(QuestionAssessmentInsertReqDto data) {
        ConnHandler.begin();
        final InsertResDto response = new InsertResDto();

        QuestionAssessment assessment = new QuestionAssessment();

        JobVacancy vacancy = jobVacancyDao.getById(JobVacancy.class, data.getJobVacancyId());
        assessment.setJobVacancy(vacancy);

        QuestionTopic qTopic = qTopicDao.getById(QuestionTopic.class, data.getTopicId());
        assessment.setQuestionTopic(qTopic);

        QuestionAssessment createdAssessment = questionAssessmentDao.save(assessment);
        if (createdAssessment != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<QuestionAssessmentInsertSeekerReqDto> reqBody = new HttpEntity<QuestionAssessmentInsertSeekerReqDto>(
                    new QuestionAssessmentInsertSeekerReqDto(qTopic.getCode(), vacancy.getJobVacancyCode()), headers);

            ResponseEntity<InsertResDto> res = restTemplate.exchange(
                    "http://localhost:8081/seeker/questions/assessment", HttpMethod.POST, reqBody, InsertResDto.class);
            if (res.getStatusCode().equals(HttpStatus.CREATED)) {
                ConnHandler.commit();
                response.setId(createdAssessment.getId());
                response.setMessage("Sukses memasangkan question assessment!");
            } else {
                ConnHandler.rollback();
                throw new RuntimeException();
            }
        }
        return response;
    }

    public UpdateResDto updateScoreAssessment(QuestionAssessmentSubmittedReqDto data) {
        ConnHandler.begin();
        Supplier<String> supplier = () -> "System";

        final UpdateResDto response = new UpdateResDto();
        try {

            Applicant applicant = applicantDao.getByCode(data.getApplicantCode());

            Assessment assessment = assessmentDao.getByApplicantId(applicant.getId());
            System.out.println(assessment.getId());
            assessment.setAssessmentScore(data.getScore());
            assessment.setVersion(assessment.getVersion() + 1);
            assessmentDao.saveNoLogin(assessment, supplier);

            ConnHandler.commit();
            response.setVer(assessment.getVersion());
            response.setMessage("Sukses update nilai candidate!");
        } catch (Exception e) {
            ConnHandler.rollback();
            throw new RuntimeException();
        }

        return response;
    }
}
