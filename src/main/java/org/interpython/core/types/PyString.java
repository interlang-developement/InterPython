package org.interpython.core.types;

import org.interpython.core.errors.exception.ArithmeticError.OverflowError;
import org.interpython.core.errors.exception.valueError.ValueError;

import java.util.List;

public class PyString extends PyObject{
    public String value;

    public PyString(List<PyObject> args) {
        super();
        if(args.size() == 1)
            this.value = args.get(0).toString();
        else
            this.value = "";
    }

    public PyString(String value) {
        super();
        this.value = value;
    }

    @Override
    public PyString __str__() {
        return this;
    }

    @Override
    public PyInt __int__() {
        if(Integer.getInteger(this.value) != null)
            return new PyInt(Integer.parseInt(this.value));
        else
            new ValueError(List.of(new PyString(
                    "Invalid literal for int() with base 10: '" + this.value + "'"
            ))).raise();
        return null;
    }

    @Override
    public PyString __repr__() {
        if(
                this.value.split("\"", -1).length-1 >=
                        this.value.split("'", -1).length-1
        )
            return new PyString("'"
                    + this.value.replace("'", "\\'" )
                    + "'");
        else
            return new PyString("\""
                    + this.value.replace("\"", "\\\"" )
                    + "\"");
    }

    @Override
    public PyString __add__(PyObject other) {
        return new PyString(this.value + other.toString());
    }

    @Override
    public PyString __mul__(PyObject other) {
        if(other.__int__().value < Integer.MAX_VALUE)
            return new PyString(this.value.repeat((int) other.__int__().value));
        else {
            new OverflowError(List.of(new PyString(
                    "The value of the multiplier is too large"
            ))).raise();
        }
        return null;
    }

    @Override
    public PyString __name__() {
        return new PyString("str");
    }
}
