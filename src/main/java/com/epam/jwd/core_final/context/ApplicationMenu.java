package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.IOException;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default String printAvailableOptions() {
        return null;
    }

    default void handleUserInput() throws IOException, InvalidStateException {
    }
}
