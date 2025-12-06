package model.statement.file;

import model.exception.StatementException;
import model.expression.IExpression;
import model.state.ProgramState;
import model.statement.IStatement;
import model.type.StringType;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private final IExpression expression;

    public CloseRFile(IExpression expression) {
        this.expression = expression;
    }
    @Override
    public String toString() {
        return "CloseRFile{" + "expression=" + expression + '}';
    }
    @Override
    public IStatement deepCopy() {
        return new CloseRFile(expression);
    }
    @Override
    public ProgramState execute(ProgramState state) {
        IValue val = this.expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(!val.getType().equals(new StringType())){
            throw new StatementException("Wrong type of expression");
        }
        StringValue strVal = (StringValue) val;
        BufferedReader br = state.getFileTable().getValue(strVal);
        try{
            br.close();
        }catch(IOException e){
            throw new StatementException(e.getMessage());
        }
        state.getFileTable().removeByKey(strVal);
        return null;
    }

}
