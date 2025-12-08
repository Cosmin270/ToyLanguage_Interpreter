package model.statement;

import model.exception.StatementException;
import model.map.MyIMap;
import model.state.ProgramState;
import model.type.IType;

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

    @Override
    public MyIMap<String, IType> typeCheck(MyIMap<String, IType> typeEnv) throws StatementException{
        return second.typeCheck(first.typeCheck(typeEnv));
    }
}
