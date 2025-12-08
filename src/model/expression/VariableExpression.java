package model.expression;

import model.exception.ExpressionsException;
import model.exception.MyException;
import model.map.MyIMap;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.type.IType;
import model.value.*;

public record VariableExpression(String variableName) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) throws MyException {
        if(!symbolTable.isDefined(variableName)){
            throw new ExpressionsException("Variable \"" +this.variableName + "\" is not defined");
        }
        return symbolTable.getValue(variableName);
    }

    @Override
    public String toString(){
        return variableName;
    }

    @Override
    public IType typeCheck(MyIMap<String, IType> typeEnv) throws ExpressionsException{
        return typeEnv.get(this.variableName);
    }
}
