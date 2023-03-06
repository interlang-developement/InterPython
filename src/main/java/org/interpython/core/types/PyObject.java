package org.interpython.core.types;

import java.util.List;

public class PyObject {
    // Un-callable
    public PyObject() {}

    public PyObject __new__() {
        return new PyObject();
    }

    public void __init__(List<PyObject> args) {}

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

    public PyObject __floordiv__(PyObject other) {
        // TypeError: unsupported operand type(s) for //: 'object' and 'object'
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

    public PyFloat __float__() {
        // UnsupportedOperation
        throw new RuntimeException("UnsupportedOperation");
    }

    public PyString __str__() {
        return new PyString("<class 'object'>");
    }

    public PyString __repr__() {
        return new PyString("\"<class 'object'>\"");
    }

    public PyString __name__() {
        return new PyString("object");
    }

    public PyFloat __float__() {
        // UnsupportedOperation
        throw new RuntimeException("UnsupportedOperation");
    }
}
