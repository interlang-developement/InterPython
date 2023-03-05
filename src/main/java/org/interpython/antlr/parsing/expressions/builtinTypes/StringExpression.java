package org.interpython.antlr.parsing.expressions.builtinTypes;

import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.core.types.PyString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class StringExpression implements Expression {
    private String value;

    public StringExpression(String value) {
        this.value = value;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(Opcodes.NEW, "org/interpython/core/types/PyString");
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitLdcInsn(value);

        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "org/interpython/core/types/PyString", "<init>", "(Ljava/lang/String;)V", false);

    }

    @Override
    public int localVariableCount() {
        return 1;
    }
}
