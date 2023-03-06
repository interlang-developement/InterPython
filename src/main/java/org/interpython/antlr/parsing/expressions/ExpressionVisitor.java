package org.interpython.antlr.parsing.expressions;

import org.interpython.antlr.InterPythonBaseVisitor;
import org.interpython.antlr.InterPythonParser;
import org.interpython.antlr.parsing.expressions.arithmetic.binaryExpression.AddExpression;
import org.interpython.antlr.parsing.expressions.arithmetic.binaryExpression.MulExpression;
import org.interpython.antlr.parsing.expressions.arithmetic.binaryExpression.SubExpression;
import org.interpython.antlr.parsing.expressions.builtinTypes.FloatExpression;
import org.interpython.antlr.parsing.expressions.builtinTypes.IntExpression;
import org.interpython.antlr.parsing.expressions.builtinTypes.StringExpression;
import org.interpython.antlr.parsing.expressions.builtinTypes.VariableReference;
import org.interpython.antlr.parsing.statements.StatementVisitor;

public class ExpressionVisitor extends InterPythonBaseVisitor<Expression> {
    @Override
    public StringExpression visitSTRING(InterPythonParser.STRINGContext ctx) {
        return new StringExpression(ctx.STRING().toString().substring(1, ctx.STRING().toString().length() - 1));
    }

    @Override
    public IntExpression visitINT(InterPythonParser.INTContext ctx) {
        return new IntExpression(Integer.parseInt(ctx.getText()));
    }

    @Override
    public Expression visitVAR(InterPythonParser.VARContext ctx) {
        return new VariableReference(StatementVisitor.variables.get(ctx.getText()));
    }

    @Override
    public Expression visitFLOAT(InterPythonParser.FLOATContext ctx) {
        return new FloatExpression(Double.parseDouble(ctx.getText()));
    }

    @Override
    public Expression visitADDITIVE_EXPR(InterPythonParser.ADDITIVE_EXPRContext ctx) {
        return switch (ctx.arith_op().getText()) {
            case "+" -> new AddExpression(visit(ctx.expression(0)), visit(ctx.expression(1)));
            case "-" -> new SubExpression(visit(ctx.expression(0)), visit(ctx.expression(1)));
            default -> throw new RuntimeException("Not implemented yet!");
        };
    }

    @Override
    public Expression visitMULTIPLICATIVE_EXPR(InterPythonParser.MULTIPLICATIVE_EXPRContext ctx) {
        return switch (ctx.term_op().getText()) {
            case "*" -> new MulExpression(visit(ctx.expression(0)), visit(ctx.expression(1)));
            case "/" -> throw new RuntimeException("Not implemented yet!");
            default -> throw new RuntimeException("Not implemented yet!");
        };
    }
}
