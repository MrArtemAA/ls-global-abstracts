package ru.livescripts.core.exception;

import ru.livescripts.core.Acceptor;
import ru.livescripts.core.model.Entity;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id) {
        super("id = " + id);
    }

    public EntityNotFoundException(Acceptor<? extends Entity> acceptor) {
        super("Acceptor = " + acceptor);
    }

}
