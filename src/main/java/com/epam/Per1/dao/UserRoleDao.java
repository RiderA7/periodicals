package com.epam.Per1.dao;

import com.epam.Per1.DbException;
import com.epam.Per1.entity.UserRole;

import java.util.List;

public interface UserRoleDao {

    List<UserRole> getAllUserRoles() throws DbException;

    UserRole getUserRole(Long id) throws DbException;

//    Long createUserRole(String role);



}
