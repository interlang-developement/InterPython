package org.interpython.antlr.parsing.expressions.builtinTypes;

import org.interpython.antlr.parsing.expressions.Expression;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class DictExpression implements Expression {
    private final long value;
    public DictExpression(long value) {
        this.value = value;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(Opcodes.NEW, "org/interpython/core/types/PyDict");
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitLdcInsn(value);

        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "org/interpython/core/types/PyDict", "<init>", "(J)V", false);
    }

    @Override
    public int localVariableCount() {
        return 0;
    }
}
