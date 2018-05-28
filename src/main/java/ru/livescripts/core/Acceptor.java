package ru.livescripts.core;

import ru.livescripts.core.model.Entity;

public interface Acceptor<T extends Entity> {
    String toString();
}
