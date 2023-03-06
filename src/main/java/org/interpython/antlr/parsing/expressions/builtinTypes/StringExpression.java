package org.interpython.antlr.parsing.expressions.builtinTypes;

import org.interpython.antlr.parsing.expressions.Expression;
import org.objectweb.asm.MethodVisitor;

public class StringExpression implements Expression {
    private final String value;

    public StringExpression(String value) {
        this.value = value;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(NEW, "org/interpython/core/types/PyString");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitLdcInsn(value);

        methodVisitor.visitMethodInsn(INVOKESPECIAL, "org/interpython/core/types/PyString", "<init>", "(Ljava/lang/String;)V", false);

    }

    @Override
    public int localVariableCount() {
        return 0;
    }
}
