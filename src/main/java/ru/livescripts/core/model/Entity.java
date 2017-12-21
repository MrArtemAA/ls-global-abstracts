package ru.livescripts.core.model;

import java.io.Serializable;

public interface Entity extends Serializable {

    String getId();
    void setId(final String id);

    boolean isNew();

}
