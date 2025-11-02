package model.expression;

import model.exception.ExpressionsException;
import model.state.ISymbolTable;
import model.value.*;
import model.type.*;

public record ArithmeticExpression(String operator, IExpression left, IExpression right) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable) throws ExpressionsException {
        var leftValue = (IntegerValue) left.evaluate(symbolTable);
        var rightValue = (IntegerValue) right.evaluate(symbolTable);

        checktype(leftValue, rightValue, new IntType());

        return switch (operator) {
            case "+" -> new IntegerValue(leftValue.value() + rightValue.value());
            case "-" -> new IntegerValue(leftValue.value() - rightValue.value());
            case "*" -> new IntegerValue(leftValue.value() * rightValue.value());
            case "/" -> {
                if (rightValue.value() == 0)
                    throw new ExpressionsException("Division by zero");
                yield new IntegerValue(leftValue.value() / rightValue.value());
            }
            default -> throw new ExpressionsException("Arithmetic operator -> \"" + this.operator + "\" is not recognized");
        };

    }

    private void checktype(IValue left, IValue right, IType type) {
        if (!left.getType().equals(type) || !right.getType().equals(type)) {
            throw new ExpressionsException("Arithmetic operators are not compatible");
        }
    }

    @Override
    public String toString(){
        return this.left.toString() + " " + this.operator + " " + this.right.toString();
    }
}
