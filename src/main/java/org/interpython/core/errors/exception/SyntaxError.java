package org.interpython.core.errors.exception;

import org.interpython.core.types.PyObject;
import org.interpython.core.types.PyString;

import java.util.List;

public class SyntaxError extends Exception{
    public SyntaxError() {
        super();
    }

    public SyntaxError(List<PyObject> objects) {
        super(objects);
    }

    @Override
    public PyString __name__() {
        return new PyString("SyntaxError");
    }
}
