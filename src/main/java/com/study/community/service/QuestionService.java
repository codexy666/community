package com.study.community.service;

import com.study.community.dto.PaginationDTO;
import com.study.community.dto.QuestionDTO;
import com.study.community.exception.CustomizeErrorCode;
import com.study.community.exception.CustomizeException;
import com.study.community.mapper.QuestionMapper;
import com.study.community.mapper.UserMapper;
import com.study.community.model.Question;
import com.study.community.model.QuestionExample;
import com.study.community.model.User;
import org.apache.ibatis.session.RowBounds;
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

    //分页查询，用于首页问题列表
    public PaginationDTO loadQuestions(Integer page, Integer size){
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());  //问题总数
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(size * (page - 1), size));
        return initPaginationDTO(totalCount, page, size, questions);
    }

    //根据creator查询，用于个人中心，我的问题列表
    public PaginationDTO loadQuestions(Integer userId, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);  //问题总数
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample, new RowBounds(size * (page - 1), size));
        return initPaginationDTO(totalCount, page, size, questions);
    }

    //加载QuestionDTO通过id，用与显示单个问题
    public QuestionDTO loadQuestionDTOById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    //创建或更新question，用于编辑问题
    public void createOrUpdate(Question question){
        if(question.getId() == null){   //发布问题时没有id的问题，因为主键自动增长
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{  //编辑问题时因为id已经存在，更新问题
            Question questionUpdate = new Question();
            questionUpdate.setGmtModified(System.currentTimeMillis());
            questionUpdate.setTitle(question.getTitle());
            questionUpdate.setDescription(question.getDescription());
            questionUpdate.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(questionUpdate, questionExample);
            if(updated != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    //计算统计要显示的页详细信息
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
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        paginationDTO.setQuestionDTOs(questionDTOs);
        return paginationDTO;
    }
}
