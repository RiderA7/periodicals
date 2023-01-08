package com.epam.Per1.dao;

import com.epam.Per1.DbException;
import com.epam.Per1.entity.Publication;

import java.util.List;
import java.util.Optional;

public interface PublicationDao {
    int countAll(int topicId) throws DbException;
    List<Publication> getAll(int topicId) throws DbException;
    List<Publication> getLimit(String where, String groupBy, String sort, int offset, int limit) throws DbException;
    Optional<Publication> getById(int id) throws DbException;
    Optional<Publication> getByName(String name) throws DbException;
    boolean create(Publication publication) throws DbException;
    boolean update(Publication publication) throws DbException;
}
