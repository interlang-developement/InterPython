package org.interpython.core.utils;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    public Map<String, Variable> variables = new HashMap<>();

    public Scope parent;

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public Variable getVariable(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        } else if (parent != null) {
            return parent.getVariable(name);
        } else {
            return null;
        }
    }

    public void setVariable(String name, Variable variable) {
        variables.put(name, variable);
    }
}
