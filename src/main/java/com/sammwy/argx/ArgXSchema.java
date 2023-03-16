package com.sammwy.argx;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.sammwy.argx.errors.ArgXException;
import com.sammwy.argx.errors.InstantiateException;
import com.sammwy.argx.errors.MissingRequiredException;

public class ArgXSchema<T> {
    private Class<T> clazz;
    private List<ArgXSchemaField> fields;

    protected ArgXSchema(Class<T> clazz) {
        this.clazz = clazz;
        this.fields = new ArrayList<>();
        this.lookupFields(clazz.getDeclaredFields());
    }

    private void lookupFields(Field[] fields) {
        for (Field field : fields) {
            ArgXSchemaField schemaField = new ArgXSchemaField(field);
            if (schemaField.isField()) {
                this.fields.add(schemaField);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public T instantiate(ArgXContext context) throws ArgXException {
        Object obj = null;

        try {
            obj = this.clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new InstantiateException(this.clazz, e);
        }

        for (ArgXSchemaField field : this.fields) {
            String rawValue = context.getValueBySchemaField(field);

            if (rawValue != null) {
                field.inject(obj, rawValue);
            } else {
                if (field.isRequired()) {
                    throw new MissingRequiredException(field.getName());
                }
            }
        }

        return (T) obj;
    }
}
