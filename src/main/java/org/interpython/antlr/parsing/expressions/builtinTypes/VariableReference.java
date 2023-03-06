package org.interpython.antlr.parsing.expressions.builtinTypes;

import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.core.utils.Variable;
import org.objectweb.asm.MethodVisitor;

public class VariableReference implements Expression {
    Variable variable;

    public VariableReference(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(ALOAD, variable.getId());
    }

    @Override
    public int localVariableCount() {
        return 0;
    }
}
