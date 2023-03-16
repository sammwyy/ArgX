package com.sammwy.argx.errors;

public class InstantiateException extends ArgXException {
    public InstantiateException(Class<?> clazz, Exception cause) {
        super("Cannot instantiate object with class " + clazz.getName(), cause);
    }
}
