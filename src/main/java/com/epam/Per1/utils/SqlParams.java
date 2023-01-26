package com.epam.Per1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqlParams {
    public List<String> whereList = new ArrayList<>();
    private String where = "";
    private String groupBy = "";
    private String sort = "";
    private int offset = 0;
    private int limit = 0;

    public SqlParams(){}

    public SqlParams(PagingParams pagingParams) {
        this.offset = pagingParams.getOffset();
        this.limit = pagingParams.getLimit();
    }

    public void addWhere(String where){
        this.whereList.add(where);
    }
    public void clearWhere(){
        this.whereList.clear();
    }
    public String getWhereList(){
        if(whereList.size()==0){ return "";}
        return whereList.stream()
                .collect(Collectors.joining(" AND ", " WHERE ", ""));
    }

    public String getWhere() {
        return getWhereList();
    }
    public String getGroupBy() {
        return groupBy;
    }
    public String getSort() {
        return sort;
    }
    public int getOffset() {
        return offset;
    }
    public int getLimit() {
        return limit;
    }
    public String getLimits(){
        if(offset == 0 && limit == 0) return "";
        return " LIMIT " + offset + ", " + limit;
    }

    public void setWhere(String where){
        if(where != null && !where.equals("")) this.where = " WHERE " + where;
    }
    public void setGroupBy(String groupBy){
        if(groupBy != null && !groupBy.equals("")) this.groupBy = " GROUP BY " + groupBy;
    }
    public void setSort(String sort){
        if(sort != null && !sort.equals("")) this.sort = " " + sort;
    }
    public void setOffsetAndLimit(PagingParams pagingParams){
        this.offset = pagingParams.getOffset();
        this.limit = pagingParams.getLimit();
    }

    public static class Builder {
        SqlParams sqlParams = new SqlParams();
        public SqlParams getSqlParams(){
            return sqlParams;
        }
        public Builder setWhere(String where){
            if(where != null && !where.equals("")) sqlParams.where = " WHERE " + where;
            return this;
        }
        public Builder setGroupBy(String groupBy){
            if(groupBy != null && !groupBy.equals("")) sqlParams.groupBy = " GROUP BY " + groupBy;
            return this;
        }
        public Builder setSort(String sort){
            if(sort != null && !sort.equals("")) sqlParams.sort = " " + sort;
            return this;
        }
        public Builder setOffsetAndLimit(PagingParams pagingParams){
            sqlParams.offset = pagingParams.getOffset();
            sqlParams.limit = pagingParams.getLimit();
            return this;
        }
    }
}
