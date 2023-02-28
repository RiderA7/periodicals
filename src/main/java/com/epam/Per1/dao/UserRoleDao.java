package com.epam.Per1.dao;

import com.epam.Per1.entity.UserRole;
import com.epam.Per1.exception.DbException;

import java.util.List;

/**
 * The UserRoleDao interface defines the methods that must be implemented by classes that provide data access
 * functionality for UserRole objects.
 * It declares methods for retrieving all UserRole objects from the database and getting a UserRole object by its id.
 *
 * @author Alexander Bukhalenkov
 */
public interface UserRoleDao {
    List<UserRole> getAllUserRoles() throws DbException;
    UserRole getUserRole(int id) throws DbException;
}
