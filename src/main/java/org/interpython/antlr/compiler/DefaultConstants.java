package org.interpython.antlr.compiler;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class DefaultConstants implements Opcodes {
    public static void apply(ClassWriter classWriter) {

        classWriter.newClass("org/interpython/core/types/PyObject");

        classWriter.newClass("org/interpython/core/types/PyString");
        classWriter.newClass("org/interpython/core/types/PyInt");
    }
}
