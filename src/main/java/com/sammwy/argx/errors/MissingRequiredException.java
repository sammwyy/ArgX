package com.sammwy.argx.errors;

public class MissingRequiredException extends ArgXException {
    public MissingRequiredException(String name) {
        super(name + " isn't defined but is required.");
    }
}
