package model.expression;

import model.exception.ExpressionsException;
import model.map.MyIMap;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.type.*;
import model.value.*;

public record ArithmeticExpression(String operator, IExpression left, IExpression right) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionsException {
        var leftValue = (IntegerValue) left.evaluate(symbolTable, heapTable);
        var rightValue = (IntegerValue) right.evaluate(symbolTable, heapTable);

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

    @Override
    public IType typeCheck(MyIMap<String, IType> typeEnv) throws ExpressionsException{
        IType type1, type2;
        type1 = left.typeCheck(typeEnv);
        type2 = right.typeCheck(typeEnv);


        if(!type1.equals(new IntType()))
            throw new ExpressionsException("ARITHMETIC:First operand is not of type INT");
        if(!type2.equals(new IntType()))
            throw new ExpressionsException("ARITHMETIC:Second operand is not of type INT");

        return new IntType();
    }
}
