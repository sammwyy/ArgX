package com.sammwy.argx.errors;

public class ArgXException extends Exception {
    public ArgXException(String message) {
        super(message);
    }

    public ArgXException(String message, Exception child) {
        super(message, child);
    }
}
