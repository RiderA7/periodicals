package com.epam.Per1.dao.entity;

public enum UserRole{

    UNREGISTERED("1", "UNREGISTERED"),
    REGISTERED("2", "REGISTERED"),
    ADMIN("3", "ADMIN");

    private String id;
    private String role;

    UserRole(String id, String role){
        this.id = id;
        this.role = role;
    }

    public String getUserRole(){
        return role;
    }
    public String getUserRoleId(){
        return id;
    }
}
