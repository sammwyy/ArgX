package com.sammwy.argx.errors;

import java.lang.reflect.Field;

public class InjectionException extends ArgXException {
    public InjectionException(Field field, Object value, Exception cause) {
        super("Cannot inject value " + value + " into field " + field.getName(), cause);
    }
}
