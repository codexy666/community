package com.study.community.service;

import com.study.community.dto.PaginationDTO;
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

    //分页查询
    public PaginationDTO loadQuestions(Integer page, Integer size){
        Integer totalCount = questionMapper.countAll();  //问题总数
        List<Question> questions = questionMapper.loadQuestionsByPage(size * (page - 1), size);
        return initPaginationDTO(totalCount, page, size, questions);
    }

    //根据creator查询
    public PaginationDTO loadQuestions(Integer userId, Integer page, Integer size) {
        Integer totalCount = questionMapper.countByUserId(userId);  //问题总数
        List<Question> questions = questionMapper.loadQuestionsByUserId(userId, size * (page - 1), size);
        return initPaginationDTO(totalCount, page, size, questions);
    }

    public QuestionDTO loadQuestionDTOById(Integer id) {
        Question question = questionMapper.loadQuestionById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findById(question.getCreator());
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    private PaginationDTO initPaginationDTO(Integer totalCount, Integer page, Integer size, List<Question> questions){
        int totalPage = (totalCount / size) + ((totalCount % size) == 0 ? 0 : 1);   //总页数
        PaginationDTO paginationDTO = new PaginationDTO();  //存储问题导航栏信息的对象
        //调整查询页
        if(page < 1) page = 1;
        if(page > totalPage) page = totalPage;

        paginationDTO.init(totalPage, page); //初始化问题导航栏
        List<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>(questions.size());
        for(Question question : questions){
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findById(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        paginationDTO.setQuestionDTOs(questionDTOs);
        return paginationDTO;
    }
}
