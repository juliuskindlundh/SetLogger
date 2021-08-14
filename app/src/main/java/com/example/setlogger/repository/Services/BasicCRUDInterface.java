package com.example.setlogger.repository.Services;

import java.util.List;

public interface BasicCRUDInterface<T> {

    List<T> getAll();

    T findById(int id);

    void create(T toCreate);

    void update(T toUpdate);

    void delete(T toDelete);
}
