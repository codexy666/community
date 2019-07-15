package com.study.community.mapper;

import com.study.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (id, title, description, gmt_create, gmt_modified, creator, tag) " +
            "values(#{id}, #{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    //传非Bean是需要自己加映射
    @Select("select * from question limit #{offset}, #{size}")
    List<Question> loadQuestionsByPage(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer countAll();

    @Select("select * from question where creator = #{userId} limit #{offset}, #{size}")
    List<Question> loadQuestionsByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);
}
