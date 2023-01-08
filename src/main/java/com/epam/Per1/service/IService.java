package com.epam.Per1.service;

import com.epam.Per1.utils.PagingParams;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    List<T> getAll();
    List<T> getLimit(String where, String groupBy, String sort, PagingParams pagingParams);
// C
    boolean create(T t);
// R
    Optional<T> getByName(String name);
    Optional<T> getById(int id);
// U
    boolean update(T t);
// D
    boolean delete(T t);
    int countAll();

}
