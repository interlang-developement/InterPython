package org.interpython.core.errors;

import org.interpython.core.types.PyObject;
import org.interpython.core.types.PyString;

import java.util.List;

public class BaseException extends PyObject {
    public BaseException(List<PyObject> args) {
        super();
    }
    public BaseException() {
        super();
    }

    public void raise() {
        throw new RuntimeException(this.__name__().value);
    }

    @Override
    public PyString __name__() {
        return new PyString("BaseException");
    }
}
