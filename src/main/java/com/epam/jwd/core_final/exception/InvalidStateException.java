package com.epam.jwd.core_final.exception;

public class InvalidStateException extends Exception {
    //  private final Object args;
    private final String entityName;

    public InvalidStateException(String entityName) {
        super();
        // this.args = args;
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        System.out.println(entityName + " didn't load correctly");
        System.out.println("invalid state. please exit the program");
        return null;
    }

    // todo
}
