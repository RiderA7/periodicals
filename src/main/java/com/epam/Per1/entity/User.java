package com.epam.Per1.entity;

public class User extends Entity {

    private String login;
    private String name;
    private String password;
    private int roleId;
    private double money;
    private boolean blocked;

    public User() {
    }

    public User copyUser(User user) {
        return new Builder()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setRoleId(user.getRoleId())
                .setMoney((int)user.getMoney()*100)
                .setBlocked(user.isBlocked()?1:0)
                .getUser();
    }

    public User(int id, String login, String name, String password,
                int roleId, double money, boolean blocked) {
        super(id);
        this.login = login;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.money = money;
        this.blocked = blocked;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleId() {
        return roleId;
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
                ", money=" + money +
                ", blocked=" + blocked +
                '}';
    }

    public static class Builder {
        User user = new User();

        public Builder setId(int id) {
            user.setId(id);
            return this;
        }

        public Builder setLogin(String login) {
            user.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setName(String name) {
            user.name = name;
            return this;
        }

        public Builder setRoleId(int id) {
            user.roleId = id;
            return this;
        }

        public Builder setMoney(int money) {
            user.money = money / 100.0;
            return this;
        }

        public Builder setBlocked(int blocked) {
            user.blocked = blocked != 0;
            return this;
        }

        public User getUser() {
            return user;
        }
    }
}