package com.sammwy.argx.errors;

public class InvalidTypeException extends ArgXException {
    public InvalidTypeException(Class<?> target) {
        super("Cannot parse unknown type " + target.getSimpleName() + " from " + target.getName());
    }
}
