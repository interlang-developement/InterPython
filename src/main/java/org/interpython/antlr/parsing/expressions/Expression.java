package org.interpython.antlr.parsing.expressions;

import org.objectweb.asm.MethodVisitor;

public interface Expression {
    void apply(MethodVisitor methodVisitor);

    int localVariableCount();
}
