package org.interpython.core.errors.exception.ArithmeticError;

import org.interpython.core.types.PyObject;
import org.interpython.core.types.PyString;

import java.util.List;

public class OverflowError extends ArithmeticError{
    public OverflowError() {
        super();
    }

    public OverflowError(List<PyObject> objects) {
        super(objects);
    }

    @Override
    public PyString __name__() {
        return new PyString("OverflowError");
    }
}
