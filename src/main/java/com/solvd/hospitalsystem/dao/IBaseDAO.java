package com.solvd.hospitalsystem.dao;

public interface IBaseDAO<T> {
    T getEntityById(long id) throws InterruptedException;
    void updateEntity(T entity) throws InterruptedException;
    T createEntity(T entity) throws InterruptedException;
    void removeEntity(long id) throws InterruptedException;
}


