package org.interpython.core.types;

import java.util.List;

public class PyFloat extends PyObject{
    Number value;

    public <T extends Number> PyFloat(T value) {
        super();
        this.value = value;
    }

    @Override
    public PyObject __new__() {
        return new PyFloat(0.0);
    }

    @Override
    public void __init__(List<PyObject> args) {
        this.value = args.get(0).__float__().value;
    }

    @Override
    public PyObject __add__(PyObject other) {
        return new PyFloat(
                this.value.doubleValue() + other.__float__().value.doubleValue()
        );
    }

    @Override
    public PyObject __sub__(PyObject other) {
        return new PyFloat(
                this.value.doubleValue() - other.__float__().value.doubleValue()
        );
    }

    @Override
    public PyObject __mul__(PyObject other) {
        return new PyFloat(
                this.value.doubleValue() * other.__float__().value.doubleValue()
        );
    }

    @Override
    public PyObject __div__(PyObject other) {
        return new PyFloat(
                this.value.doubleValue() / other.__float__().value.doubleValue()
        );
    }

    @Override
    public PyString __name__() {
        return new PyString("float");
    }
}
