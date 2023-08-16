package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dao.QuestionAssessmentDao;
import com.lawencon.candidate.dao.QuestionDao;
import com.lawencon.candidate.dao.QuestionOptionDao;
import com.lawencon.candidate.dao.QuestionTopicDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.question.QuestionAnswerResDto;
import com.lawencon.candidate.dto.question.QuestionAssessmentInsertReqDto;
import com.lawencon.candidate.dto.question.QuestionTopicInsertSeekerReqDto;
import com.lawencon.candidate.dto.question.QuestionsResDto;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.candidate.model.Question;
import com.lawencon.candidate.model.QuestionAssessment;
import com.lawencon.candidate.model.QuestionOption;
import com.lawencon.candidate.model.QuestionTopic;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuestionTopicDao qTopicDao;
    @Autowired
    QuestionOptionDao qOptionDao;
    @Autowired
    QuestionAssessmentDao qAssessmentDao;
    @Autowired
    JobVacancyDao jobVacancyDao;

    public InsertResDto createQuestion(QuestionTopicInsertSeekerReqDto data) {
        Supplier<String> supplier = () -> "System";
        ConnHandler.begin();
        final InsertResDto response = new InsertResDto();

        try {
            QuestionTopic topic = new QuestionTopic();
            topic.setTitle(data.getTopicTitle());
            topic.setCode(data.getTopicCode());
            QuestionTopic createdTopic = qTopicDao.saveNoLogin(topic, supplier);

            for (int i = 0; i < data.getQuestions().size(); i++) {
                Question question = new Question();
                question.setQuestion(data.getQuestions().get(i).getQuestionDesc());
                question.setQuestionTopic(createdTopic);
                Question createdQuestion = questionDao.saveNoLogin(question, supplier);
                for (int j = 0; j < data.getQuestions().get(i).getOptions().size(); j++) {
                    QuestionOption option = new QuestionOption();
                    option.setAnswer(data.getQuestions().get(i).getOptions().get(j).getAnswerText());
                    option.setIsCorrect(data.getQuestions().get(i).getOptions().get(j).getIsCorrect());
                    option.setQuestionId(createdQuestion);
                    qOptionDao.save(option);
                }
            }

            ConnHandler.commit();
            response.setId(createdTopic.getId());
            response.setMessage("Topic question sukses dibuat");
        } catch (Exception e) {
            e.printStackTrace();
            ConnHandler.rollback();
            throw new RuntimeException();
        }

        return response;
    }

    public InsertResDto insertAssessment(QuestionAssessmentInsertReqDto data){
        Supplier<String> supplier = () -> "System";
        ConnHandler.begin();
        final InsertResDto response = new InsertResDto();

        try{
            QuestionAssessment qAssessment = new QuestionAssessment();
            JobVacancy jobVacancy = jobVacancyDao.getByCode(data.getJobVacancyCode());
            QuestionTopic questionTopic = qTopicDao.getByCode(data.getTopicCode());
            qAssessment.setJobVacancy(jobVacancy);
            qAssessment.setQuestionTopic(questionTopic);

            QuestionAssessment cAssessment = qAssessmentDao.saveNoLogin(qAssessment,supplier);

            if(cAssessment != null){
                ConnHandler.commit();
                response.setId(cAssessment.getId());
                response.setMessage("Sukses memasangkan question assessment!");
            }
        }catch(Exception e){
            e.printStackTrace();
            ConnHandler.rollback();
            throw new RuntimeException();
        }

        return response;
    }
    
    public List<QuestionsResDto> getQuestionByCode (String code){
        List<QuestionsResDto> responses = new ArrayList<>();
        QuestionTopic topic = qTopicDao.getByCode(code);
        questionDao.getQuestionsByTopic(topic.getId()).forEach(c ->{
            QuestionsResDto response = new QuestionsResDto();
            response.setQuestionDesc(c.getQuestion());
            response.setQuestionId(c.getId());
            List<QuestionAnswerResDto> options = new ArrayList<>();
            qOptionDao.getOptionsByQuestion(c.getId()).forEach(o ->{
                QuestionAnswerResDto option = new QuestionAnswerResDto();
                option.setAnswerId(o.getId());
                option.setAnswerText(o.getAnswer());
                options.add(option);
            });
            response.setOptions(options);
            responses.add(response);
        });

        return responses;
    }
}
