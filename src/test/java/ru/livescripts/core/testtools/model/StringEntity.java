package ru.livescripts.core.testtools.model;

import ru.livescripts.core.model.BaseEntity;
import ru.livescripts.core.testtools.MapDao;

import java.util.UUID;

public class StringEntity extends BaseEntity<String> {
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public static class StringIdGenerator implements MapDao.IdGenerator<String> {
        @Override
        public String next() {
            return UUID.randomUUID().toString();
        }
    }
}
