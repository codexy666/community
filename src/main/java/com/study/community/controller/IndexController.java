package com.study.community.controller;

import com.study.community.dto.PaginationDTO;
import com.study.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size){

        //加载问题列表
        PaginationDTO pagination = questionService.loadQuestions(page, size);
        model.addAttribute("pagination", pagination);
        return "index"; //返回跳转的哪个html路径
    }
}
