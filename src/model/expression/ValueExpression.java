package model.expression;

import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.value.IValue;

public record ValueExpression(IValue value) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) {return value;}

    @Override
    public String toString(){
        return value.toString();
    }
}
