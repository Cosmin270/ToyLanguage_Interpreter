package model.statement.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.exception.StatementException;
import model.expression.IExpression;
import model.state.ProgramState;
import model.statement.IStatement;
import model.type.StringType;
import model.value.IValue;
import model.value.StringValue;

public class OpenRFile implements IStatement {
    private final IExpression exp;

    public OpenRFile(IExpression exp){this.exp = exp;}

    @Override
    public String toString() {
        return "OpenRFile{" + "exp=" + exp + '}';
    }

    @Override
    public IStatement deepCopy() {
        return new OpenRFile(exp);
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IValue val = this.exp.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(val.getType().equals(new StringType())){
            StringValue strVal = (StringValue) val;
            if(state.getFileTable().isDefined(strVal)){
                throw new StatementException("File already exists");
            }
            try{
                BufferedReader br = new BufferedReader(new FileReader(strVal.getValue()));
                state.getFileTable().declareFile(strVal, br);
            }catch(FileNotFoundException e){
                throw new StatementException("File not found: " + strVal.getValue());
            }

        }
        else{
            throw new StatementException("Invalid expression");
        }
        return null;
    }

}
