package org.interpython.antlr.parsing.expressions;

import org.interpython.antlr.InterPythonBaseVisitor;
import org.interpython.antlr.InterPythonParser;
import org.interpython.antlr.parsing.expressions.builtinTypes.IntExpression;
import org.interpython.antlr.parsing.expressions.builtinTypes.StringExpression;

public class ExpressionVisitor extends InterPythonBaseVisitor<Expression> {
    @Override
    public StringExpression visitSTRING(InterPythonParser.STRINGContext ctx) {
        return new StringExpression(ctx.STRING().toString().substring(1, ctx.STRING().toString().length() - 1));
    }

    @Override
    public IntExpression visitINT(InterPythonParser.INTContext ctx) {
        return new IntExpression(Integer.parseInt(ctx.getText()));
    }
}
