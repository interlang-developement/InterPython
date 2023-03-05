package org.interpython.core.types;

import java.util.List;

public class PyFloat extends PyObject{
    public double value;

    public PyFloat(List<PyObject> args) {
        super();
        if(args.size() >= 2) {
            // TypeError: float() takes at most 1 argument (args.size() given)
        }
        else if(args.size() == 1) {
            this.value = ((PyFloat) args.get(0)).value;
        } else {
            this.value = 0.0f;
        }
    }
    public PyFloat(double value) {
        super();
        this.value = value;
    }

    @Override
    public PyObject __add__(PyObject other) {
        try{
            other.__float__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for +: 'float' and 'str'");
        }

        return new PyFloat(this.value + other.__float__().value);
    }

    @Override
    public PyObject __sub__(PyObject other) {
        try {
            other.__float__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for -: 'float' and 'str'");
        }

        return new PyFloat(this.value - other.__float__().value);
    }

    @Override
    public PyObject __mul__(PyObject other) {
        try {
            other.__float__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for *: 'float' and 'str'");
        }

        return new PyFloat(this.value * other.__float__().value);
    }

    @Override
    public PyObject __pow__(PyObject other) {
        // Unimplemented

        throw new RuntimeException("Unimplemented");
    }

    @Override
    public PyObject __div__(PyObject other) {
        try {
            other.__float__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for /: 'float' and 'str'");
        }

        return new PyFloat(this.value / other.__float__().value);
    }

    @Override
    public PyObject __mod__(PyObject other) {
        try {
            other.__float__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for %: 'float' and 'str'");
        }

        return new PyFloat(this.value % other.__float__().value);
    }

    @Override
    public PyFloat __float__() {
        return this;
    }
}
