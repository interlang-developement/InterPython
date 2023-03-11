package org.interpython.antlr.parsing.statements;

import org.interpython.antlr.InterPythonBaseVisitor;
import org.interpython.antlr.InterPythonParser;
import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.antlr.parsing.expressions.ExpressionVisitor;
import org.interpython.antlr.parsing.statements.complexStatements.CodeBlock;
import org.interpython.antlr.parsing.statements.complexStatements.IfStatements;
import org.interpython.core.utils.Scope;
import org.interpython.core.utils.Variable;

import java.util.*;

public class StatementVisitor extends InterPythonBaseVisitor<Statements> {
    public Queue<Statements> instructionsQueue = new ArrayDeque<>();
    public Scope scope;

    public StatementVisitor(Scope scope) {
        super();
        this.scope = scope;
    }

    @Override
    public Statements visitStatement(InterPythonParser.StatementContext ctx) {
        var ret = visitChildren(ctx);

        if (ret == null){
            ret = new ExpressionVisitor(scope).visit(ctx);
        }

        instructionsQueue.add(ret);

        return ret;
    }

    @Override
    public AssignmentStatement visitAssignment(InterPythonParser.AssignmentContext ctx) {
        String name = ctx.NAME().getText();
        visit(ctx.expression());

        Expression expr = new ExpressionVisitor(scope).visit(ctx.expression());

        Variable variable = new Variable(
                scope.variables.size(), expr
        );

        scope.setVariable(name, variable);

        return new AssignmentStatement(variable);
    }

    @Override
    public Statements visitCode_block(InterPythonParser.Code_blockContext ctx) {
        return new CodeBlock(
                ctx.lines().statement().stream().map(new StatementVisitor(
                        scope
                )::visit).toList()
        );
    }


    @Override
    public Statements visitIf_statement(InterPythonParser.If_statementContext ctx) {

        return new IfStatements(
                new ExpressionVisitor(scope).visit(ctx.ifexpr), visit(ctx.ifblock), ctx.expression() == null ? new ArrayList<>() :
                ctx.expression().subList(1, ctx.expression().size()) .stream().map(
                x -> new ExpressionVisitor(scope).visit(x)
        ).toList(), ctx.code_block() == null ? new ArrayList<>() : ctx.code_block().subList(
                1, ctx.expression().size()
        ).stream().map(
                this::visit
        ).toList(), ctx.elseblock == null ? null : visit(ctx.elseblock)
        );
    }
}
