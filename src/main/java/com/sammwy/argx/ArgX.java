package com.sammwy.argx;

import com.sammwy.argx.errors.ArgXException;

public class ArgX {
    private static <T> T parse(Class<T> schema, ArgXContext ctx) throws ArgXException {
        ArgXSchema<T> argXSchema = new ArgXSchema<T>(schema);
        T object = argXSchema.instantiate(ctx);
        return object;
    }

    public static <T> T parse(Class<T> schema, String[] args) throws ArgXException {
        return parse(schema, new ArgXContext().parse(args));
    }

    public static <T> T parse(Class<T> schema, String args) throws ArgXException {
        return parse(schema, new ArgXContext().parse(args));
    }
}
