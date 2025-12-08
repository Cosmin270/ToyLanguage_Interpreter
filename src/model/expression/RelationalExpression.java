package model.expression;

import model.exception.ExpressionsException;
import model.map.MyIMap;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.type.IType;
import model.type.IntType;
import model.value.BooleanValue;
import model.value.IValue;
import model.value.IntegerValue;

public record RelationalExpression(String operator, IExpression left, IExpression right) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionsException{
        IntegerValue left = (IntegerValue) this.left.evaluate(symbolTable, heapTable);
        IntegerValue right = (IntegerValue) this.right.evaluate(symbolTable, heapTable);

        checkType(left, right, new IntType());

        return switch(this.operator) {
            case "==" -> new BooleanValue(left.getValue() == right.value());
            case "!=" -> new BooleanValue(left.getValue() != right.value());
            case "<=" -> new BooleanValue(left.getValue() <= right.value());
            case ">=" -> new BooleanValue(left.getValue() >= right.value());
            case ">" ->  new BooleanValue(left.getValue() > right.value());
            case "<" ->  new BooleanValue(left.getValue() < right.value());
            default -> throw new ExpressionsException("Unknown operator: " + operator);
        };
    }


    private void checkType(IValue left, IValue right, IType type) {
        if (!left.getType().equals(type) || !right.getType().equals(type)) {
            throw new ExpressionsException("Invalid type");
        }
    }

    @Override
    public IType typeCheck(MyIMap<String, IType> typeEnv) throws ExpressionsException{
        IType type1, type2;
        type1 = left.typeCheck(typeEnv);
        type2 = right.typeCheck(typeEnv);


        if(!type1.equals(new IntType()))
            throw new ExpressionsException("First operand is not of type INT");
        if(!type2.equals(new IntType()))
            throw new ExpressionsException("Second operand is not of type INT");

        return new IntType();
    }
}
