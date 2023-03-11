package org.interpython.antlr.parsing.expressions;

import org.interpython.antlr.parsing.statements.Statements;
import org.objectweb.asm.MethodVisitor;

public interface Expression extends Statements {
    void apply(MethodVisitor methodVisitor);

    int localVariableCount();
}
