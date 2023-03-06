package org.interpython.antlr.parsing.expressions.builtinTypes;

import org.interpython.antlr.parsing.expressions.Expression;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class FloatExpression implements Expression {
    double value;

    public FloatExpression(double value) {
        this.value = value;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(Opcodes.NEW, "org/interpython/core/types/PyFloat");
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitLdcInsn(value);

        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double", false);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "org/interpython/core/types/PyFloat", "<init>", "(Ljava/lang/Number;)V", false);
    }

    @Override
    public int localVariableCount() {
        return 0;
    }
}
