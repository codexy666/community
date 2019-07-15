package com.study.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOs;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void init(Integer totalPage, Integer page){
        //添加所有要显示的页
        for(int i = 3; i > 0; i--){
            if(page - i > 0) pages.add(page - i);
        }
        if(0 < page &&  page <= totalPage) pages.add(page);
        for(int i = 1; i <= 3; i++){
            if(page + i <= totalPage) pages.add(page + i);
        }
        //是否显示上一页、下一页
        if(page != 1)  showPrevious = true;
        if(!page.equals(totalPage))  showNext = true;
        //是否显示第一页、最后一页
        if(!pages.contains(1)) showFirstPage = true;
        if(!pages.contains(totalPage)) showEndPage = true;
        this.page = page;
        this.totalPage = totalPage;
    }
}
