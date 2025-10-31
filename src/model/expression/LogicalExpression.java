package model.expression;

import model.exception.ExpressionsEvaluation;
import model.exception.MyException;
import model.state.SymbolTable;
import model.type.*;
import model.value.*;


public record LogicalExpression(String operator, Expression left, Expression right) implements Expression {

    @Override
    public Value evaluate(SymbolTable symbolTable) throws MyException {
        var leftValue = (BooleanValue) left.evaluate(symbolTable);
        var rightValue = (BooleanValue) right.evaluate(symbolTable);

        checkType(leftValue, rightValue, new BooleanType());

        return switch (operator) {
            case "&" -> new BooleanValue(leftValue.value() && rightValue.value());
            case "|" -> new BooleanValue(leftValue.value() || rightValue.value());
            default -> throw new ExpressionsEvaluation("Unknown operator: " + operator);
        };
    }

    private void checkType(Value left, Value right, Type type) {
        if (!left.getType().equals(type) || !right.getType().equals(type)) {
            throw new ExpressionsEvaluation("Invalid type");
        }
    }
    @Override
    public String toString(){
        return this.left.toString() + " " + this.operator + " " + this.right.toString();
    }

}
