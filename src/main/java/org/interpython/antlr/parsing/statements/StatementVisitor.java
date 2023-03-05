package org.interpython.antlr.parsing.statements;

import org.interpython.antlr.InterPythonBaseVisitor;
import org.interpython.antlr.InterPythonParser;
import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.antlr.parsing.expressions.ExpressionVisitor;
import org.interpython.core.utils.Variable;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class StatementVisitor extends InterPythonBaseVisitor<Statements> {
    public Queue<Statements> instructionsQueue = new ArrayDeque<>();
    public Map<String, Variable> variables = new HashMap<>();

    public int variableCount = 0;

    @Override
    public AssignmentStatement visitAssignment(InterPythonParser.AssignmentContext ctx) {
        String name = ctx.NAME().getText();
        visit(ctx.expression());

        Expression expr = new ExpressionVisitor().visit(ctx.expression());

        Variable variable = new Variable(
                variableCount, expr
        );

        variables.put(name, variable);

        variableCount ++;
        variableCount += expr.localVariableCount();

        AssignmentStatement statement = new AssignmentStatement(variable);

        instructionsQueue.add(statement);

        return statement;
    }
}
