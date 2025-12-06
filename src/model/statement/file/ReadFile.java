package model.statement.file;

import model.exception.StatementException;
import model.expression.IExpression;
import model.state.ProgramState;
import model.statement.IStatement;
import model.type.IntType;
import model.type.StringType;
import model.value.IValue;
import model.value.IntegerValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;


public class ReadFile implements IStatement {
    private IExpression expression;
    private String variableName;

    public ReadFile(IExpression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public String toString() {
        return "ReadFile{" + "expression=" + expression + ", variableName=" + variableName + '}';
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFile(expression, variableName);
    }

    @Override
    public ProgramState execute(ProgramState state) {
        if(!state.getSymbolTable().isDefined(this.variableName)){
            throw new StatementException("Variable " + this.variableName + " is not defined");
        }

        IValue variable =  state.getSymbolTable().getValue(this.variableName);

        if(!variable.getType().equals(new IntType())){
            throw new StatementException("Variable " + this.variableName + " is not of type Int");
        }

        IValue filename = this.expression.evaluate(state.getSymbolTable(), state.getHeapTable());

        if(!filename.getType().equals(new StringType())){
            throw new StatementException("Variable " + this.variableName + " is not of type String");
        }

        StringValue filenameValue = (StringValue) filename;

        if(!state.getFileTable().isDefined(filenameValue)){
            throw new StatementException("Filename " + filenameValue.getValue() + " is not defined");
        }

        BufferedReader fileDescriptor = state.getFileTable().getValue(filenameValue);
        try{
            String line = fileDescriptor.readLine();
            if(line != null){
                state.getSymbolTable().update(this.variableName, new IntegerValue(Integer.parseInt(line)));
            }else{
                state.getSymbolTable().update(this.variableName, new IntType().getDefaultValue());
            }
        }
        catch(IOException e){
            throw new StatementException("Error reading file: " + filenameValue.getValue());
        }
        catch(NumberFormatException e){
            throw new StatementException("Use only integers: " + filenameValue.getValue());
        }
        return null;
    }
}
