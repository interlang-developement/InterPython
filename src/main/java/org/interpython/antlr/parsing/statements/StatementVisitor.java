package org.interpython.antlr.parsing.statements;

import org.interpython.antlr.InterPythonBaseVisitor;
import org.interpython.antlr.InterPythonParser;
import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.antlr.parsing.expressions.ExpressionVisitor;
import org.interpython.antlr.parsing.statements.complexStatements.CodeBlock;
import org.interpython.antlr.parsing.statements.complexStatements.IfStatements;
import org.interpython.core.utils.Variable;

import java.util.*;

public class StatementVisitor extends InterPythonBaseVisitor<Statements> {
    public Queue<Statements> instructionsQueue = new ArrayDeque<>();
    public static Map<String, Variable> variables = new HashMap<>();

    public static int variableCount = 0;

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
        variableCount += expr.localVariableCount() ;

        AssignmentStatement statement = new AssignmentStatement(variable);

        instructionsQueue.add(statement);

        return statement;
    }

    @Override
    public Statements visitCode_block(InterPythonParser.Code_blockContext ctx) {
        return new CodeBlock(
                ctx.lines().statement().stream().map(new StatementVisitor()::visit).toList()
        );
    }

    @Override
    public Statements visitIf_expr(InterPythonParser.If_exprContext ctx) {
        var var1 = ctx.expression();
        var ret = new IfStatements(
                new ExpressionVisitor().visit(ctx.ifexpr), visit(ctx.ifblock), ctx.expression() == null ? new ArrayList<>() :
                ctx.expression().subList(1, ctx.expression().size()) .stream().map(
                x -> new ExpressionVisitor().visit(x)
        ).toList(), ctx.code_block() == null ? new ArrayList<>() : ctx.code_block().subList(
                1, ctx.expression().size()
        ).stream().map(
                this::visit
        ).toList(), ctx.elseblock == null ? null : visit(ctx.elseblock)
        );

        instructionsQueue.add(ret);

        return ret;
    }
}
