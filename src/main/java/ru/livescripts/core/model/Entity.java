package ru.livescripts.core.model;

import java.io.Serializable;

public interface Entity<ID> extends Serializable {

    ID getId();
    void setId(final ID id);

    boolean isNew();

}
