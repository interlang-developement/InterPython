package org.interpython.core.types;

import java.util.List;

public class PyBool extends PyInt{
    public boolean value;

    public PyBool(boolean value) {
        super(value ? 1 : 0);
        this.value = value;
    }

    @Override
    public PyObject __new__() {
        return new PyBool(false);
    }

    @Override
    public void __init__(List<PyObject> args) {
        this.value = args.get(0).__bool__().value;
    }

    @Override
    public PyBool __bool__() {
        return this;
    }

    @Override
    public PyInt __int__() {
        return new PyInt(value ? 1 : 0);
    }
}
