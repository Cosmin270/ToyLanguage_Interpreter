package model.statement;

import model.exception.StatementException;
import model.expression.IExpression;
import model.state.ProgramState;
import model.state.ISymbolTable;
import model.value.IValue;

public record AssignmentStatement(String variableName, IExpression expression) implements IStatement {

    @Override
    public ProgramState execute(ProgramState state){
            ISymbolTable symbolTable = state.getSymbolTable();
            if(!symbolTable.isDefined(this.variableName)){
                throw new StatementException("Variable " + this.variableName + " is not defined");
            }
            IValue value = expression.evaluate(symbolTable);
            if(!value.getType().equals(symbolTable.getType(this.variableName))){
                throw new StatementException("Type mismatch");
            }
            symbolTable.update(this.variableName, value);
            return null;
    }
    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(this.variableName, this.expression);
    }

    @Override
    public String toString(){
        return this.variableName + " = " + this.expression.toString();
    }
}
