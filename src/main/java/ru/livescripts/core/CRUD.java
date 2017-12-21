package ru.livescripts.core;

import ru.livescripts.core.model.Entity;

import java.util.List;

public interface CRUD<T extends Entity> {

    T get(String id);
    T get(Acceptor<? extends T> acceptor);
    List<? extends T> getList(Acceptor<? extends T> acceptor);
    void save(T entity);
    void delete(T entity);

}
