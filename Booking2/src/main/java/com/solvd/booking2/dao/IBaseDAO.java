package com.solvd.booking2.dao;

public interface IBaseDAO <T> {
    T save(T entity);
    T getById(Long id);
    void update(T entity);
    void deleteById(Long id);
}