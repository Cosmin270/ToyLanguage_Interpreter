package model.statement;

import model.exception.StatementException;
import model.expression.Expression;
import model.state.ProgramState;
import model.state.SymbolTable;
import model.value.Value;

public record AssignmentStatement(String variableName, Expression expression) implements Statement {

    @Override
    public ProgramState execute(ProgramState state){
            SymbolTable symbolTable = state.getSymbolTable();
            if(!symbolTable.isDefined(this.variableName)){
                throw new StatementException("Variable " + this.variableName + " is not defined");
            }
            Value value = expression.evaluate(symbolTable);
            if(!value.getType().equals(symbolTable.getType(this.variableName))){
                throw new StatementException("Type mismatch");
            }
            symbolTable.update(this.variableName, value);
            return null;
    }
    @Override
    public Statement deepCopy() {
        return new AssignmentStatement(this.variableName, this.expression);
    }

    @Override
    public String toString(){
        return this.variableName + " = " + this.expression.toString();
    }
}
