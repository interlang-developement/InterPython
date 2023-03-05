package org.interpython.core.types;

import java.util.List;

public class PyInt extends PyObject{
    public long value;

    public PyInt(List<PyObject> args) {
        super();
        if(args.size() >= 2) {
            // TypeError: int() takes at most 1 argument (args.size() given)
        }

        if(args.size() == 1) {
            this.value = ((PyInt) args.get(0)).value;
        } else {
            this.value = 0;
        }
    }

    public PyInt(long value) {
        super();
        this.value = value;
    }

    @Override
    public PyObject __add__(PyObject other) {
        try{
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for +: 'int' and 'str'");
        }

        return new PyInt(this.value + other.__int__().value);
    }

    @Override
    public PyObject __sub__(PyObject other) {
        try {
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for -: 'int' and 'str'");
        }

        return new PyInt(this.value - other.__int__().value);
    }

    @Override
    public PyObject __mul__(PyObject other) {
        try {
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for *: 'int' and 'str'");
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
            throw new RuntimeException("TypeError: unsupported operand type(s) for /: 'int' and 'str'");
        }

        return new PyInt(this.value / other.__int__().value);
    }

    @Override
    public PyObject __mod__(PyObject other) {
        try {
            other.__int__();
        } catch(RuntimeException e) {
            throw new RuntimeException("TypeError: unsupported operand type(s) for %: 'int' and 'str'");
        }

        return new PyInt(this.value % other.__int__().value);
    }

    @Override
    public PyInt __int__() {
        return this;
    }
}
