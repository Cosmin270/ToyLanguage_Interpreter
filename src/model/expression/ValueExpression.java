package model.expression;

import model.exception.ExpressionsException;
import model.map.MyIMap;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.type.IType;
import model.value.IValue;

public record ValueExpression(IValue value) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) {return value;}

    @Override
    public String toString(){
        return value.toString();
    }

    @Override
    public IType typeCheck(MyIMap<String, IType> typeEnv) throws ExpressionsException{
        return value.getType();
    }
}
