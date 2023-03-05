package org.interpython.core.utils;

import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.core.types.PyObject;

public class Variable {
    private int id;
    private Expression value;

    public Variable(int id, Expression value) {
        this.id = id;
        this.value = value;
    }

    public Expression getValue() {
        return value;
    }

    public int getId() {
        return id;
    }
}
