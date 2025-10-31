package model.statement;

import model.state.ProgramState;

public record CompoundStatement(Statement first, Statement second) implements Statement {

    public String toString(){
        return "("+first.toString()+","+second.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state){
        state.getExecutionStack().push(this.second);
        state.getExecutionStack().push(this.first);
        return state;
    }

    @Override
    public Statement deepCopy() {return new CompoundStatement(this.first.deepCopy(), this.second.deepCopy());}
}
