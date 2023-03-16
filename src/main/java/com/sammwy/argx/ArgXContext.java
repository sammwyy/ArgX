package com.sammwy.argx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ArgXContext {
    private Map<String, String> args;
    private List<String> cmds;

    protected ArgXContext() {
        this.args = new HashMap<>();
        this.cmds = new ArrayList<>();
    }

    public String getValueBySchemaField(ArgXSchemaField field) {
        String candidate = args.get("-" + field.getName());
        if (candidate == null) {
            candidate = args.get(field.getKey());
        }
        return candidate;
    }

    public Map<String, String> getArguments() {
        return this.args;
    }

    public List<String> getCommands() {
        return this.cmds;
    }

    public String toString() {
        String argsString = "";
        for (Entry<String, String> arg : args.entrySet()) {
            String argStr = arg.getKey() + "=" + arg.getValue();
            if (argsString.isEmpty()) {
                argsString += argStr;
            } else {
                argsString += "," + argStr;
            }
        }

        String cmdStrings = "";
        for (String cmd : cmds) {
            if (cmdStrings.isEmpty()) {
                cmdStrings += cmd;
            } else {
                cmdStrings += "," + cmd;
            }
        }

        String debugString = "ArgXContext{args:%ARGS%, cmds:%CMDS%}";
        return debugString.replace("%ARGS%", argsString).replace("%CMDS%", cmdStrings);
    }

    public ArgXContext parse(String raw) {
        raw = raw + " ";

        String currentKey = null;
        String currentValue = null;
        String currentCMD = "";

        boolean hasDeclaredString = false;

        Iterator<Integer> chars = raw.chars().iterator();
        while (chars.hasNext()) {
            int chr = chars.next();

            if (chr == '-' && currentKey == null) {
                currentKey = "";
            } else if (chr == ' ') {
                if (currentKey != null && currentValue == null) {
                    currentValue = "";
                } else if (currentValue != null) {
                    if (hasDeclaredString) {
                        currentValue = currentValue.concat(" ");
                    } else {
                        this.args.put(currentKey, currentValue);
                        currentKey = null;
                        currentValue = null;
                    }
                } else if (!currentCMD.isEmpty()) {
                    this.cmds.add(currentCMD);
                    currentCMD = "";
                }
            } else {
                if (currentValue != null) {
                    currentValue = currentValue.concat(String.valueOf((char) chr));
                } else if (currentKey != null) {
                    currentKey = currentKey.concat(String.valueOf((char) chr));
                } else {
                    currentCMD = currentCMD.concat(String.valueOf((char) chr));
                    continue;
                }
            }
        }

        return this;
    }

    public ArgXContext parse(String[] raws) {
        String out = null;

        for (String raw : raws) {
            if (out == null) {
                out = raw;
            } else {
                out = " " + raw;
            }
        }

        return this.parse(out);
    }
}
