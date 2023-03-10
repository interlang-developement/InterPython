package org.interpython.antlr.parsing.statements;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public interface Statements extends Opcodes {
    void apply(MethodVisitor methodVisitor);

    int localVariableCount();
}
