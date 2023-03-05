package org.interpython.antlr.parsing.expressions;

import org.interpython.core.types.PyObject;
import org.objectweb.asm.MethodVisitor;

public interface Expression {
    void apply(MethodVisitor methodVisitor);

    int localVariableCount();
}
