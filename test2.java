import org.interpython.core.types.PyInt;
import org.interpython.core.types.PyBool;
import org.interpython.core.types.PyString;
import org.interpython.core.types.PyFloat;
import org.interpython.core.types.PyObject;

import java.util.ArrayList;

public class test2 {
    public static void main(String[] var0) {
        PyObject value = new PyInt(1);

        if (value.__bool__().value){
            PyObject var1 = new PyInt(2);
        } else if (value.__bool__().value){
            PyObject var1 = new PyInt(3);
        } else {
            PyObject var1 = new PyInt(4);
        }
    }
}