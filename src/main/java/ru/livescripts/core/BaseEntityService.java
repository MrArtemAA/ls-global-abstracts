package ru.livescripts.core;

import ru.livescripts.core.exception.EntityNotFoundException;
import ru.livescripts.core.model.Entity;

import java.util.List;

public abstract class BaseEntityService<T extends Entity, DAO extends CRUD<T>> implements EntityService<T, DAO> {

    private DAO dao;

    public T get(String id) {
        T entity = dao.get(id);
        if (entity == null) {
            throw new EntityNotFoundException(id);
        }
        return entity;
    }

    public T get(Acceptor<? extends T> acceptor) {
        T entity = dao.get(acceptor);
        if (entity == null) {
            throw new EntityNotFoundException(acceptor);
        }
        return entity;
    }

    public void save(T entity) {
        dao.save(entity);
    }

    public void delete(T entity) {
        dao.delete(entity);
    }

    public List<? extends T> getList(Acceptor<? extends T> acceptor) {
        return dao.getList(acceptor);
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

}
