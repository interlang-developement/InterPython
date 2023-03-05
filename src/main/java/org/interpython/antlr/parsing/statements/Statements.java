package org.interpython.antlr.parsing.statements;

import org.objectweb.asm.MethodVisitor;

public interface Statements{
    void apply(MethodVisitor methodVisitor);

    int localVariableCount();
}
