package model.expression;

import model.exception.ExpressionsException;
import model.map.MyIMap;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.type.IType;
import model.value.IValue;

public interface IExpression {
    IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionsException;
    @Override
    String toString();
    IType typeCheck(MyIMap<String, IType> typeEnv) throws ExpressionsException;
}
