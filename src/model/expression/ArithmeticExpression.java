package model.expression;

import model.exception.ExpressionsEvaluation;
import model.exception.MyException;
import model.state.SymbolTable;
import model.value.*;
import model.type.*;

public record ArithmeticExpression(String operator, Expression left, Expression right) implements Expression {

    @Override
    public Value evaluate(SymbolTable symbolTable) throws ExpressionsEvaluation {
        var leftValue = (IntegerValue) left.evaluate(symbolTable);
        var rightValue = (IntegerValue) right.evaluate(symbolTable);

        checktype(leftValue, rightValue, new IntType());

        return switch (operator) {
            case "+" -> new IntegerValue(leftValue.value() + rightValue.value());
            case "-" -> new IntegerValue(leftValue.value() - rightValue.value());
            case "*" -> new IntegerValue(leftValue.value() * rightValue.value());
            case "/" -> {
                if (rightValue.value() == 0)
                    throw new ExpressionsEvaluation("Division by zero");
                yield new IntegerValue(leftValue.value() / rightValue.value());
            }
            default -> throw new ExpressionsEvaluation("Arithmetic operator -> \"" + this.operator + "\" is not recognized");
        };

    }

    private void checktype(Value left, Value right, Type type) {
        if (!left.getType().equals(type) || !right.getType().equals(type)) {
            throw new ExpressionsEvaluation("Arithmetic operators are not compatible");
        }
    }

    @Override
    public String toString(){
        return this.left.toString() + " " + this.operator + " " + this.right.toString();
    }
}
