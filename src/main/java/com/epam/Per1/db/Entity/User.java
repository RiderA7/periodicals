package com.epam.Per1.db.Entity;

import java.util.Date;

public class User extends Entity{

    private String login;
    private String name;
    private int roleId;
    private String email;
    private Date createDate;

    public User(){}

    public User(Long id, String login, String name, int roleId, String email, Date createDate) {
        super(id);
        this.login = login;
        this.name = name;
        this.roleId = roleId;
        this.email = email;
        this.createDate = createDate;
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
                "id=" + getId() +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public static class Builder {
        User user = new User();

        public Builder setId(Long id){
            user.setId(id);
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
