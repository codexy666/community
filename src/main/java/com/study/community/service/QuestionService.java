package com.study.community.service;

import com.study.community.dto.QuestionDTO;
import com.study.community.mapper.QuestionMapper;
import com.study.community.mapper.UserMapper;
import com.study.community.model.Question;
import com.study.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> loadQuestions(){
        List<Question> questions = questionMapper.loadQuestions();
        List<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>(questions.size());
        for(Question question : questions){
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findById(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        return questionDTOs;
    }
}
