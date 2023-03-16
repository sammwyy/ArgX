package com.sammwy.argx.utils;

import com.sammwy.argx.errors.ArgXException;
import com.sammwy.argx.errors.InvalidTypeException;
import com.sammwy.argx.errors.TypeMismatchException;

public class VarParser {
    private static int parseVarToInt(String raw) throws TypeMismatchException {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new TypeMismatchException(raw, Integer.class);
        }
    }

    private static boolean parseVarToBoolean(String raw) throws TypeMismatchException {
        if (raw.equalsIgnoreCase("y") || raw.equalsIgnoreCase("yes") || raw.equalsIgnoreCase("on")) {
            raw = "true";
        } else if (raw.equalsIgnoreCase("n") || raw.equalsIgnoreCase("no") || raw.equalsIgnoreCase("off")) {
            raw = "false";
        }

        if (!raw.equalsIgnoreCase("true") && !raw.equalsIgnoreCase("false")) {
            throw new TypeMismatchException(raw, Boolean.class);
        }

        return Boolean.parseBoolean(raw);
    }

    public static Object parse(Class<?> clazz, String raw) throws ArgXException {
        if (clazz == String.class) {
            return raw;
        } else if (clazz == int.class || clazz == Integer.class) {
            return parseVarToInt(raw);
        } else if (clazz == boolean.class || clazz == Boolean.class) {
            return parseVarToBoolean(raw);
        } else {
            throw new InvalidTypeException(clazz);
        }
    }
}
