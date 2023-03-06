package org.interpython.core.types;

import java.util.List;

public class PyInt extends PyObject{
    public long value;

    public PyInt(long value) {
        super();
        this.value = value;
    }

    @Override
    public PyObject __new__() {
        return new PyInt(0);
    }

    @Override
    public void __init__(List<PyObject> args) {
        this.value = args.get(0).__int__().value;
    }

    @Override
    public PyObject __add__(PyObject other) {
        try{
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for +: 'int' and " + other.__name__() +"\"");
        }

        return new PyInt(this.value + other.__int__().value);
    }

    @Override
    public PyObject __sub__(PyObject other) {
        try {
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for -: 'int' and " + other.__name__() +"\"");
        }

        return new PyInt(this.value - other.__int__().value);
    }

    @Override
    public PyObject __mul__(PyObject other) {
        try {
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for *: 'int' and " + other.__name__() +"\"");
        }

        return new PyInt(this.value * other.__int__().value);
    }

    @Override
    public PyObject __pow__(PyObject other) {
        // Unimplemented

        throw new RuntimeException("Unimplemented");
    }

    @Override
    public PyObject __div__(PyObject other) {
        try {
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for /: 'int' and " + other.__name__() +"\"");
        }

        return new PyInt(this.value / other.__int__().value);
    }

    @Override
    public PyObject __mod__(PyObject other) {
        try {
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for %: 'int' and " + other.__name__() +"\"");
        }

        return new PyInt(this.value % other.__int__().value);
    }

    @Override
    public PyInt __int__() {
        return this;
    }

    @Override
    public PyString __name__() {
        return new PyString("int");
    }

    @Override
    public PyFloat __float__() {
        return new PyFloat(this.value);
    }
}
