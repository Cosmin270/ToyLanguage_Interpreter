package model.statement;

import model.exception.StatementException;
import model.map.MyIMap;
import model.state.ProgramState;
import model.type.IType;

public class NoOperationStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state){
        return null;
    }
    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }
    @Override
    public String toString(){return "NopOperation";}

    @Override
    public MyIMap<String, IType> typeCheck(MyIMap<String, IType> typeEnv) throws StatementException{
        return null;
    }
}
