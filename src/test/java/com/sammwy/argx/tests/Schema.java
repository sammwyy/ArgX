package com.sammwy.argx.tests;

import com.sammwy.argx.annotations.Argument;

public class Schema {
    @Argument(key = "n", description = "Your name", required = true)
    public String name;

    @Argument(key = "a", description = "Your age", required = true)
    public int age;

    @Argument(key = "e", description = "Is Enabled?")
    public boolean enabled;
}
