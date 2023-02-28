package com.epam.Per1.entity;

public class User extends Entity {

    private String login;
    private String name;
    private String password;
    private UserRole role;
    private int roleId;
    private double money;
    private boolean isBlocked;

    public User() {
    }

    public User copyUser(User user) {
        return new Builder()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setRole(user.getRole())
                .setRoleId(user.getRoleId())
                .setMoney(user.getMoney())
                .setIsBlocked(user.isBlocked())
                .getUser();
    }

    public User(int id, String login, String name, String password,
                UserRole userRole, double money, boolean isBlocked) {
        super(id);
        this.login = login;
        this.name = name;
        this.password = password;
        this.role = userRole;
        this.roleId = userRole.getId();
        this.money = money;
        this.isBlocked = isBlocked;
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

    public UserRole getRole(){
        return role;
    }

    public int getRoleId() {
        return role.getId();
    }

    public double getMoney() {
        return money;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setBlocked(boolean blocked){
        this.isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", roleId=" + role.getId() +
                ", role=" + role.getUserRole() +
                ", money=" + money +
                ", isBlocked=" + isBlocked +
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

        public Builder setRole(UserRole userRole){
            user.role = userRole;
            return this;
        }

        public Builder setMoney(double money) {
            user.money = money;
            return this;
        }

        public Builder setIsBlocked(boolean isBlocked) {
            user.isBlocked = isBlocked;
            return this;
        }

        public User getUser() {
            return user;
        }
    }
}
