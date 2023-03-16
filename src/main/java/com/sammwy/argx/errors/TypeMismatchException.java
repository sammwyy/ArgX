package com.sammwy.argx.errors;

public class TypeMismatchException extends ArgXException {
    public TypeMismatchException(String value, Class<?> target) {
        super(value + " isn't a valid " + target.getSimpleName());
    }
}
