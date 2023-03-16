package com.sammwy.argx;

import java.lang.reflect.Field;

import com.sammwy.argx.annotations.Argument;
import com.sammwy.argx.errors.ArgXException;
import com.sammwy.argx.errors.InjectionException;
import com.sammwy.argx.utils.VarParser;

public class ArgXSchemaField {
    private Field field;
    private Argument meta;

    protected ArgXSchemaField(Field field) {
        this.field = field;
        this.meta = field.getAnnotation(Argument.class);
    }

    public String getName() {
        String name = this.meta.name();
        return name.isEmpty() ? this.field.getName() : name;
    }

    public String getKey() {
        return this.meta.key();
    }

    public void inject(Object object, String rawValue) throws ArgXException {
        Class<?> type = this.field.getType();
        Object value = VarParser.parse(type, rawValue);
        try {
            field.set(object, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new InjectionException(field, value, e);
        }
    }

    public boolean isRequired() {
        return this.meta.required();
    }

    public boolean isField() {
        return this.field != null && this.meta != null;
    }
}
