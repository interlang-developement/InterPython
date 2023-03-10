package org.interpython.antlr.parsing.statements.complexStatements;

import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.antlr.parsing.statements.Statements;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.List;

public class IfStatements implements Statements {
    public Expression ifConditions;
    public List<Expression> elifConditions;
    public Statements ifCodes;
    public List<Statements> elifCodes;
    public Statements elseCodes;

    public IfStatements(Expression ifCondition, Statements ifCodes, List<Expression> elifCondition, List<Statements> elifCodes, Statements elseCode) {
        this.ifConditions = ifCondition;
        this.elifConditions = elifCondition;
        this.ifCodes = ifCodes;
        this.elifCodes = elifCodes;
        this.elseCodes = elseCode;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        Label end = new Label();

        /* if START */

        Label next = new Label();

        ifConditions.apply(methodVisitor);

        methodVisitor.visitMethodInsn(
                INVOKEVIRTUAL, "org/interpython/core/types/PyObject", "__bool__", "()Lorg/interpython/core/types/PyBool"
        );
        methodVisitor.visitFieldInsn(
                GETFIELD, "org/interpython/core/types/PyBool", "value", "Z"
        );
        methodVisitor.visitJumpInsn(
                IFEQ, next
        );

        ifCodes.apply(methodVisitor);

        methodVisitor.visitJumpInsn(GOTO, end);

        methodVisitor.visitLabel(next);

        /* IF END */

        for (int i = 0; i < elifCodes.size(); i++){
            next = new Label();

            elifConditions.get(i).apply(methodVisitor);

            methodVisitor.visitMethodInsn(
                    INVOKEVIRTUAL, "org/interpython/core/types/PyObject", "__bool__", "()Lorg/interpython/core/types/PyBool"
            );
            methodVisitor.visitFieldInsn(
                    GETFIELD, "org/interpython/core/types/PyBool", "value", "Z"
            );
            methodVisitor.visitJumpInsn(
                    IFEQ, next
            );

            elifCodes.get(i).apply(methodVisitor);

            methodVisitor.visitJumpInsn(GOTO, end);

            methodVisitor.visitLabel(next);
        }
        if (elseCodes != null)
            elseCodes.apply(methodVisitor);


        methodVisitor.visitLabel(end);
    }

    @Override
    public int localVariableCount() {
        return 0;
    }
}
