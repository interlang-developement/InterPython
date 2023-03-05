package org.interpython.antlr.parsing.expressions.builtinTypes;

import org.interpython.antlr.parsing.expressions.Expression;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class IntExpression implements Expression, Opcodes {
    private final long value;
    public IntExpression(long value) {
        this.value = value;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(Opcodes.NEW, "org/interpython/core/types/PyInt");
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitLdcInsn(value);

        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "org/interpython/core/types/PyInt", "<init>", "(J)V", false);
//        methodVisitor.visitInsn(Opcodes.ASTORE + 2);
    }

    @Override
    public int localVariableCount() {
        return 1;
    }
}
