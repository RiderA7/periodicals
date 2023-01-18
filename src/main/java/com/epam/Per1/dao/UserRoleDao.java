package com.epam.Per1.dao;

import com.epam.Per1.entity.UserRole;
import com.epam.Per1.exception.DbException;

import java.util.List;

public interface UserRoleDao {

    List<UserRole> getAllUserRoles() throws DbException;

    UserRole getUserRole(int id) throws DbException;

//    Long createUserRole(String role);



}
