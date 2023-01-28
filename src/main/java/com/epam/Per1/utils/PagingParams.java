package com.epam.Per1.utils;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PagingParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private int offset;
    private int limit = 2;
    private int total;
    private int maxPageNum;
    private int currentPage;
    private String filter = "";
    private String orderBy = "";
    private String sort = "";

    public PagingParams(){}

    public PagingParams(int total, int limit) {
        this.total = total;
        this.limit = limit;
        this.maxPageNum = (total % limit == 0) ? total/limit-1 : total/limit ;
        this.currentPage = 0;
        this.offset = 0;
    }

    public void setTotal(int total){
        this.total = total;
        this.maxPageNum = (total % limit == 0) ? total/limit-1 : total/limit;
        if(this.currentPage>this.maxPageNum) this.currentPage = 0;
    }

    public void setPage(int page){
        currentPage = page;
        if(currentPage<0){
            currentPage = 0;
        }
        if(currentPage > maxPageNum){
            currentPage = maxPageNum;
        }
        offset = (currentPage)*limit;
    }

    public void nextPage(){
        setPage(this.currentPage+1);
    }
    public void prevPage(){
        setPage(this.currentPage-1);
    }

}
