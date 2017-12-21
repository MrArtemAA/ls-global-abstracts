package ru.livescripts.core.model;

import org.apache.commons.lang3.StringUtils;

public abstract class BaseEntity implements Entity {
    private String id;

    public boolean isNew() {
        return StringUtils.isBlank(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

}
