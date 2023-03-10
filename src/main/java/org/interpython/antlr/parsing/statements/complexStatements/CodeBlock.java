package org.interpython.antlr.parsing.statements.complexStatements;

import org.interpython.antlr.parsing.statements.Statements;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CodeBlock implements Statements {
    public List<Statements> statements;

    public CodeBlock(List<Statements> statements) {
        this.statements = statements;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        for (Statements statement : statements)
            if(statement != null)
                statement.apply(methodVisitor);
    }

    @Override
    public int localVariableCount() {
        return statements.stream().mapToInt(Statements::localVariableCount).sum();
    }
}
