package com.epam.Per1.service;

import com.epam.Per1.exception.NoSuchElementException;
import com.epam.Per1.utils.SqlParams;

import java.util.List;

public interface Service<T> {

    List<T> getAll();
    List<T> getLimit(SqlParams sqlParams);
    int countAll();
// C
    boolean create(T t);
// R
    T getByLogin(String name) throws NoSuchElementException;
    T getById(int id) throws NoSuchElementException;
// U
    boolean update(T t);
// D
    boolean delete(T t);

}
