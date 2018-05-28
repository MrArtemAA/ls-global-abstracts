package ru.livescripts.core.testtools;

import ru.livescripts.core.Acceptor;
import ru.livescripts.core.CRUD;
import ru.livescripts.core.model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapDao<T extends Entity<ID>, ID> implements CRUD<T, ID> {
    private Map<ID, T> storage = new HashMap<>();

    private final IdGenerator<ID> idGenerator;

    public MapDao(IdGenerator<ID> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public T get(ID id) {
        return storage.get(id);
    }

    @Override
    public T get(Acceptor<? extends T> acceptor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<? extends T> getSome(Acceptor<? extends T> acceptor) {
        return getAll();
    }

    @Override
    public Iterable<? extends T> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public T save(T entity) {
        if (entity.isNew()) {
            ID id = idGenerator.next();
            entity.setId(id);
            storage.put(id, entity);
            return entity;
        } else {
            return storage.replace(entity.getId(), entity);
        }
    }

    @Override
    public boolean delete(ID id) {
        return storage.remove(id) != null;
    }

    public interface IdGenerator<ID> {
        ID next();
    }

    public static <T extends Entity<ID>, ID> Builder<T, ID> builder(IdGenerator<ID> idGenerator) {
        return new Builder<>(idGenerator);
    }

    public static final class Builder<T extends Entity<ID>, ID> {
        private final IdGenerator<ID> idGenerator;
        private final List<T> entities = new ArrayList<>();

        private Builder(IdGenerator<ID> idGenerator) {
            this.idGenerator = idGenerator;
        }

        public Builder add(T entity) {
            entities.add(entity);
            return this;
        }

        public MapDao<T, ID> build() {
            MapDao<T, ID> mapDao = new MapDao<>(idGenerator);
            entities.forEach(entity -> mapDao.storage.put(entity.getId(), entity));
            return mapDao;
        }
    }
}
