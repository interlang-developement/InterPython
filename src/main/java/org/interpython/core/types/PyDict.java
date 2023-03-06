package org.interpython.core.types;

import java.util.HashMap;
import java.util.List;

public class PyDict extends PyObject{
    public HashMap<Integer, String> value;

    public PyDict(HashMap<Integer, String> value) {
        super();
        this.value = value;
    }

    @Override
    public PyObject __new__() {
        return new PyDict(new HashMap<Integer, String>());
    }

    @Override
    public void __init__(List<PyObject> args) {
        this.value = args.get(0).__dict__().value;
    }

    @Override
    public PyObject __add__(PyObject other) {
        throw new RuntimeException("TypeError: unsupported operand type(s) for +: 'dict' and " + other.__name__() +"\"");
    }

    @Override
    public PyObject __sub__(PyObject other) {
        throw new RuntimeException("TypeError: unsupported operand type(s) for -: 'dict' and " + other.__name__() +"\"");
    }

    @Override
    public PyObject __mul__(PyObject other) {
        throw new RuntimeException("TypeError: unsupported operand type(s) for *: 'dict' and " + other.__name__() +"\"");
    }

    @Override
    public PyObject __pow__(PyObject other) {
        // Unimplemented

        throw new RuntimeException("Unimplemented");
    }

    @Override
    public PyObject __div__(PyObject other) {
        throw new RuntimeException("TypeError: unsupported operand type(s) for /: 'dict' and " + other.__name__() +"\"");
    }

    @Override
    public PyObject __mod__(PyObject other) {
        throw new RuntimeException("TypeError: unsupported operand type(s) for %: 'dict' and " + other.__name__() +"\"");
    }

    @Override
    public PyDict __dict__() {
        return this;
    }

    @Override
    public PyString __name__() {
        return new PyString("dict");
    }
}
