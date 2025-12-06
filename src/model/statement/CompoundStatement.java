package model.statement;

import model.state.ProgramState;

public record CompoundStatement(IStatement first, IStatement second) implements IStatement {

    public String toString(){
        return "("+first.toString()+","+second.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state){
        state.getExecutionStack().push(this.second);
        state.getExecutionStack().push(this.first);
        return null;
    }

    @Override
    public IStatement deepCopy() {return new CompoundStatement(this.first.deepCopy(), this.second.deepCopy());}
}
