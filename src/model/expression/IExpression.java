package model.expression;

import model.exception.ExpressionsException;
import model.state.ISymbolTable;
import model.value.IValue;

public interface IExpression {
    IValue evaluate(ISymbolTable symbolTable) throws ExpressionsException;
    String toString();
}
