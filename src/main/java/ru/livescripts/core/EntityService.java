package ru.livescripts.core;

import ru.livescripts.core.model.Entity;

public interface EntityService<T extends Entity, DAO extends CRUD<T>> extends CRUD<T> {

    DAO getDao();
    void setDao(DAO dao);

}
