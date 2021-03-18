package com.epam.jwd.core_final.exception;

public class UnknownEntityException extends RuntimeException {

    private final String entityName;
    private final Object[] args;

    public UnknownEntityException(String entityName) {
        super();
        this.entityName = entityName;
        this.args = null;
    }

    public UnknownEntityException(String entityName, Object[] args) {
        super();
        this.entityName = entityName;
        this.args = args;
    }

    @Override
    public String getMessage() {
        System.out.println("there is no such entity. check the input data " + entityName);
        // todo
        // you should use entityName, args (if necessary)
        return null;
    }
}
