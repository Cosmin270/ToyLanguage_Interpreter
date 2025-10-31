package model.statement;

import model.exception.MyException;
import model.exception.StatementException;
import model.state.ProgramState;
import model.type.Type;

public class VariableDeclarationStatement implements Statement {
    private String variableName;
    private Type type;

    public VariableDeclarationStatement(String variableName, Type type) {
        this.variableName = variableName;
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString() + " " + variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        if(programState.getSymbolTable().isDefined(this.variableName))
            throw new StatementException("Variable " + this.variableName + " is already defined");
        else
            programState.getSymbolTable().update(this.variableName, type.getDefaultValue());
        return programState;

    }

    @Override
    public Statement deepCopy() {
        return new VariableDeclarationStatement(this.variableName, this.type);
    }
}
