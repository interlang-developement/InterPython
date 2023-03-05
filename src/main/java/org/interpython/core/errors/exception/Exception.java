package org.interpython.core.errors.exception;

import org.interpython.core.errors.BaseException;
import org.interpython.core.types.PyObject;
import org.interpython.core.types.PyString;

import java.util.List;

public class Exception extends BaseException {
    public Exception() {
        super();
    }

    public Exception(List<PyObject> objects) {
        super(objects);
    }

    @Override
    public PyString __name__() {
        return new PyString("Exception");
    }
}
