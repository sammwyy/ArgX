package com.sammwy.argx.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sammwy.argx.ArgX;
import com.sammwy.argx.errors.ArgXException;
import com.sammwy.argx.errors.MissingRequiredException;

public class ParseTest {
    @Test
    public void checkRequired() {
        try {
            ArgX.parse(Schema.class, "");
            assertTrue(false);
        } catch (ArgXException e) {
            if (e instanceof MissingRequiredException) {
                assertTrue(true);
            } else {
                e.printStackTrace();
                assertTrue(false);
            }
        }
    }

    @Test
    public void checkParsing() {
        try {
            Schema schema = ArgX.parse(Schema.class, "--name sammwy -a 21 --enabled yes hello world");

            assertEquals(schema.age, 21);
            assertEquals(schema.name, "sammwy");
            assertTrue(schema.enabled);
        } catch (ArgXException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
