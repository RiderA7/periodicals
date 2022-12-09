package com.epam.Per1.db.model;

import java.util.Date;

public class User {

    private int id;
    private String login;
    private String name;
    private int roleId;
    private String email;
    private Date createDate;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public static class Builder {
        User user = new User();

        public Builder setId(int id){
            user.id = id;
            return this;
        }
        public Builder setLogin(String login){
            user.login = login;
            return this;
        }
        public Builder setName(String name){
            user.name = name;
            return this;
        }
        public Builder setRoleId(int id){
            user.roleId = id;
            return this;
        }
        public Builder setEmail(String email){
            user.email = email;
            return this;
        }
        public Builder setCreateDate(Date date){
            user.createDate = date;
            return this;
        }
        public User getUser(){
            return user;
        }
    }
}
