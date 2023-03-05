package org.interpython.core.types;

import java.util.ArrayList;
import java.util.List;

public class PyObject {
    public PyObject(List<PyObject> args) {
        this.__init__(args);
    }

    // Un-callable
    public PyObject() {}

    public static PyObject __new__() {
        return new PyObject(new ArrayList<>());
    }

    public void __init__(List<PyObject> args) {
    }

    public PyObject __call__(List<PyObject> args) {
        // TypeError: 'object' object is not callable
        return this;
    }

    public PyObject __add__(PyObject other) {
        // TypeError: unsupported operand type(s) for +: 'object' and 'object'
        return this;
    }

    public PyObject __sub__(PyObject other) {
        // TypeError: unsupported operand type(s) for -: 'object' and 'object'
        return this;
    }

    public PyObject __mul__(PyObject other) {
        // TypeError: unsupported operand type(s) for *: 'object' and 'object'
        return this;
    }

    public PyObject __pow__(PyObject other) {
        // TypeError: unsupported operand type(s) for **: 'object' and 'object'
        return this;
    }

    public PyObject __div__(PyObject other) {
        // TypeError: unsupported operand type(s) for /: 'object' and 'object'
        return this;
    }

    public PyObject __mod__(PyObject other) {
        // TypeError: unsupported operand type(s) for %: 'object' and 'object'
        return this;
    }

    public PyInt __int__() {
        // UnsupportedOperation
        throw new RuntimeException("UnsupportedOperation");
    }
}
