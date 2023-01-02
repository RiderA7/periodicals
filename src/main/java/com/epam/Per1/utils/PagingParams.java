package com.epam.Per1.utils;

public class PagingParams {

    private int offset;
    private int limit;
    private int total;
    private int maxPageNum;
    private int currentPage;

    public PagingParams(int total, int limit) {
        this.total = total;
        this.limit = limit;
        this.maxPageNum = total/limit;
        this.currentPage = 0;
        this.offset = 0;
    }

    public void setTotal(int total){
        this.total = total;
        this.maxPageNum = total/limit;
    }

    public void setPage(int page){
        currentPage = page;
        if(currentPage<1){
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

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getMaxPageNum() {
        return maxPageNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
