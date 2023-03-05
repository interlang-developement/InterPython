package org.interpython.core.errors.exception.valueError;

import org.interpython.core.errors.exception.Exception;
import org.interpython.core.types.PyObject;
import org.interpython.core.types.PyString;

import java.util.List;

public class ValueError extends Exception {
    public ValueError() {
        super();
    }

    public ValueError(List<PyObject> objects) {
        super(objects);
    }

    @Override
    public PyString __name__() {
        return new PyString("ValueError");
    }
}
