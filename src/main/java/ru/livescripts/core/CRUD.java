package ru.livescripts.core;

import ru.livescripts.core.model.Entity;

public interface CRUD<T extends Entity<ID>, ID> {

    T get(ID id);
    T get(Acceptor<? extends T> acceptor);
    Iterable<? extends T> getAll();
    Iterable<? extends T> getSome(Acceptor<? extends T> acceptor);
    T save(T entity);
    boolean delete(ID id);

}
