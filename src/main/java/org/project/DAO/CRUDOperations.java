package org.project.DAO;

import java.util.List;

public interface CRUDOperations<T> {
    void create(T entity);
    List<T> readAll();
    T readById(String id);
    void update(T entity);
    void delete(String id);
}
