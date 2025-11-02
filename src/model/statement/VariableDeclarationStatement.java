package model.statement;

import model.exception.StatementException;
import model.state.ProgramState;
import model.type.IType;

public class VariableDeclarationStatement implements IStatement {
    private String variableName;
    private IType type;

    public VariableDeclarationStatement(String variableName, IType type) {
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
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(this.variableName, this.type);
    }
}
