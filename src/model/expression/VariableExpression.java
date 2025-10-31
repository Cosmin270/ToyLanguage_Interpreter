package model.expression;

import model.exception.ExpressionsEvaluation;
import model.exception.MyException;
import model.value.*;
import model.state.SymbolTable;

public record VariableExpression(String variableName) implements Expression {

    @Override
    public Value evaluate(SymbolTable symbolTable) throws MyException {
        if(!symbolTable.isDefined(variableName)){
            throw new ExpressionsEvaluation("Variable \"" +this.variableName + "\" is not defined");
        }
        return symbolTable.getValue(variableName);
    }

    @Override
    public String toString(){
        return variableName;
    }
}
