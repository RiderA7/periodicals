package com.epam.Per1.dao.entity;

import java.util.Date;

public class User extends Entity{

    private String login;
    private String name;
    private Long roleId;
    private String email;
    private Date createDate;
    private double money;
    private boolean blocked;

    public User(){}

    public User(Long id, String login, String name,
                Long roleId, String email, Date createDate,
                double money, boolean blocked) {
        super(id);
        this.login = login;
        this.name = name;
        this.roleId = roleId;
        this.email = email;
        this.createDate = createDate;
        this.money = money;
        this.blocked = blocked;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public double getMoney() {
        return money;
    }

    public boolean isBlocked() {
        return blocked;
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
                ", money=" + money +
                ", blocked=" + blocked +
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
        public Builder setRoleId(Long id){
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
        public Builder setMoney(int money){
            user.money = money/100.0;
            return this;
        }
        public Builder setBlocked(int blocked){
            user.blocked = blocked != 0;
            return this;
        }
        public User getUser(){
            return user;
        }
    }
}
