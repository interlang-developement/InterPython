package org.interpython.antlr.parsing.expressions.arithmetic;

import org.interpython.antlr.parsing.expressions.Expression;
import org.objectweb.asm.MethodVisitor;

public class BinaryOperation implements Expression {
    protected Expression left;
    protected Expression right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        left.apply(methodVisitor);
        right.apply(methodVisitor);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "org/interpython/core/types/PyInt", getMethodName(), "(Lorg/interpython/core/types/PyObject;)Lorg/interpython/core/types/PyObject;", false);
    }

    @Override
    public int localVariableCount() {
        return 1;
    }

    public String getMethodName() {
        throw new RuntimeException("Not implemented yet!");
    }
}
