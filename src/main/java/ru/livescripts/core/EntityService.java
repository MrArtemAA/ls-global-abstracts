package ru.livescripts.core;

import ru.livescripts.core.exception.EntityNotFoundException;
import ru.livescripts.core.model.Entity;

public interface EntityService<T extends Entity<ID>, ID, DAO extends CRUD<T, ID>> extends CRUD<T, ID> {

    @Override
    T get(ID id) throws EntityNotFoundException;

    @Override
    T get(Acceptor<? extends T> acceptor) throws EntityNotFoundException;

    @Override
    T save(T entity) throws EntityNotFoundException;

    @Override
    boolean delete(ID id) throws EntityNotFoundException;

    DAO getDao();
    void setDao(DAO dao);

}
