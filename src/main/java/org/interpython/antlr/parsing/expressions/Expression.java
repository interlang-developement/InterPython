package org.interpython.antlr.parsing.expressions;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public interface Expression extends Opcodes {
    void apply(MethodVisitor methodVisitor);

    int localVariableCount();
}
