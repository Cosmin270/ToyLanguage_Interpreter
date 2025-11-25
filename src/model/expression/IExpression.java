package model.expression;

import model.exception.ExpressionsException;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.value.IValue;

public interface IExpression {
    IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws ExpressionsException;
    String toString();
}
