package org.interpython.antlr.parsing.statements;

import org.interpython.core.types.PyInt;
import org.interpython.core.types.PyString;
import org.interpython.core.utils.Variable;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AssignmentStatement implements Statements, Opcodes {
    protected Variable variable;

    public AssignmentStatement(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        variable.getValue().apply(methodVisitor);
        methodVisitor.visitVarInsn(
                ASTORE,
                variable.getId());
    }

    @Override
    public int localVariableCount() {
        return 1 + variable.getValue().localVariableCount()
                ;
    }
}
