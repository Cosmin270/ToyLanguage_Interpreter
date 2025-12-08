package model.statement;

import model.exception.StatementException;
import model.map.MyIMap;
import model.state.ProgramState;
import model.type.IType;

public interface IStatement {
    ProgramState execute(ProgramState state);
    IStatement deepCopy();
    @Override
    String toString();
    MyIMap<String, IType> typeCheck(MyIMap<String, IType> typeEnv) throws StatementException;
}
