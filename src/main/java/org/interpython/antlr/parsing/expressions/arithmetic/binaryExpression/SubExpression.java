package org.interpython.antlr.parsing.expressions.arithmetic.binaryExpression;

import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.antlr.parsing.expressions.arithmetic.BinaryOperation;

public class SubExpression extends BinaryOperation {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getMethodName() {
        return "__sub__";
    }
}
