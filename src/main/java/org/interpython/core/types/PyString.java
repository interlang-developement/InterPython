package org.interpython.core.types;

import java.util.List;

public class PyString extends PyObject{
    public String value;

    public PyString(List<PyObject> args) {
        super();
        if(args.size() == 1)
            this.value = args.get(0).toString();
        else
            this.value = "";
    }

    public PyString(String value) {
        super();
        this.value = value;
    }
}
