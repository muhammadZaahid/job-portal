package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.QuestionDao;
import com.lawencon.candidate.dao.QuestionOptionDao;
import com.lawencon.candidate.dao.QuestionTopicDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.question.QuestionTopicInsertSeekerReqDto;
import com.lawencon.candidate.model.Question;
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

}
