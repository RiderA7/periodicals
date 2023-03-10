package com.epam.Per1.dao;

import com.epam.Per1.exception.DbException;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.utils.SqlParams;

import java.util.List;
import java.util.Optional;

/**
 * The PublicationDao interface provides methods for accessing and manipulating Publication objects in a database.
 * It defines methods for counting and retrieving Publication objects, as well as creating, updating, and deleting them.
 *
 * @author Alexander Bukhalenkov
 */
public interface PublicationDao {
    int countAll(int topicId) throws DbException;
    int countAll(SqlParams sqlParams) throws DbException;
    List<Publication> getAll(int topicId) throws DbException;
    List<Publication> getLimit(SqlParams sqlParams) throws DbException;
    Optional<Publication> getById(int id) throws DbException;
    Optional<Publication> getByName(String name) throws DbException;
    boolean create(Publication publication) throws DbException;
    boolean update(Publication publication) throws DbException;
    boolean delete(Publication publication) throws DbException;
}
