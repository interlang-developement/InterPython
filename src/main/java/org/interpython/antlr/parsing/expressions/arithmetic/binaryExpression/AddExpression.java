package org.interpython.antlr.parsing.expressions.arithmetic.binaryExpression;


import org.interpython.antlr.parsing.expressions.Expression;
import org.interpython.antlr.parsing.expressions.arithmetic.BinaryOperation;

public class AddExpression extends BinaryOperation {

    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getMethodName() {
        return "__add__";
    }
}
