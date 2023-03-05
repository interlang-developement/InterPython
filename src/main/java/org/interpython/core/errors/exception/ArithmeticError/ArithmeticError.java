package org.interpython.core.errors.exception.ArithmeticError;

import org.interpython.core.errors.exception.Exception;
import org.interpython.core.types.PyObject;
import org.interpython.core.types.PyString;

import java.util.List;

public class ArithmeticError extends Exception {
    public ArithmeticError() {
        super();
    }

    public ArithmeticError(List<PyObject> objects) {
        super(objects);
    }

    @Override
    public PyString __name__() {
        return new PyString("ArithmeticError");
    }
}
