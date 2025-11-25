package model.expression;

import model.exception.ExpressionsException;
import model.exception.MyException;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.type.*;
import model.value.*;


public record LogicalExpression(String operator, IExpression left, IExpression right) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws MyException {
        var leftValue = (BooleanValue) left.evaluate(symbolTable, heapTable);
        var rightValue = (BooleanValue) right.evaluate(symbolTable, heapTable);

        checkType(leftValue, rightValue, new BooleanType());

        return switch (operator) {
            case "&" -> new BooleanValue(leftValue.value() && rightValue.value());
            case "|" -> new BooleanValue(leftValue.value() || rightValue.value());
            default -> throw new ExpressionsException("Unknown operator: " + operator);
        };
    }

    private void checkType(IValue left, IValue right, IType type) {
        if (!left.getType().equals(type) || !right.getType().equals(type)) {
            throw new ExpressionsException("Invalid type");
        }
    }
    @Override
    public String toString(){
        return this.left.toString() + " " + this.operator + " " + this.right.toString();
    }

}
