package ru.livescripts.core;

import ru.livescripts.core.exception.EntityNotFoundException;
import ru.livescripts.core.model.Entity;

import java.util.Objects;
import java.util.Optional;

public abstract class BaseEntityService<T extends Entity<ID>, ID, DAO extends CRUD<T, ID>> implements EntityService<T, ID, DAO> {

    private DAO dao;

    @Override
    public T get(ID id) throws EntityNotFoundException {
        Objects.requireNonNull(id);
        return checkNotFound(dao.get(id), id);
    }

    @Override
    public T get(Acceptor<? extends T> acceptor) throws EntityNotFoundException {
        Objects.requireNonNull(acceptor);
        return checkNotFound(dao.get(acceptor), acceptor);
    }

    @Override
    public T save(T entity) throws EntityNotFoundException {
        Objects.requireNonNull(entity);
        if (entity.isNew()) {
            return dao.save(entity);
        } else {
            return checkNotFound(dao.save(entity), entity.getId());
        }
    }

    @Override
    public boolean delete(ID id) throws EntityNotFoundException {
        Objects.requireNonNull(id);
        checkNotFound(dao.delete(id), id);
        return true;
    }

    @Override
    public Iterable<? extends T> getAll() {
        return dao.getAll();
    }

    @Override
    public Iterable<? extends T> getSome(Acceptor<? extends T> acceptor) {
        Objects.requireNonNull(acceptor);
        return dao.getSome(acceptor);
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    protected static <T, S> T checkNotFound(T entity, S searchBy) {
        return Optional.ofNullable(entity).orElseThrow(() -> new EntityNotFoundException(searchBy));
    }

    protected static <S> void checkNotFound(boolean found, S searchBy) {
        if (!found) {
            throw new EntityNotFoundException(searchBy);
        }
    }

}
