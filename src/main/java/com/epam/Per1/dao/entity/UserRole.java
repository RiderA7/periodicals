package com.epam.Per1.dao.entity;

public class UserRole extends Entity{

    private String role;

    UserRole(){}

    UserRole(Long id, String role){
        super(id);
        this.role = role;
    }

    public String getUserRole(){
        return role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + getId() +
                "role='" + role + '\'' +
                '}';
    }

    public static class Builder{
        UserRole userRole = new UserRole();

        public Builder setId(Long id){
            userRole.setId(id);
            return this;
        }
        public Builder setUserRole(String role){
            userRole.role = role;
            return this;
        }
        public UserRole getUserRole(){
            return userRole;
        }
    }
}
