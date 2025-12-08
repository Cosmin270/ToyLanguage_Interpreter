package model.statement.heap;

import model.exception.StatementException;
import model.expression.IExpression;
import model.map.MyIMap;
import model.state.ProgramState;
import model.statement.IStatement;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;

public class New implements IStatement {
    private final String variableName;
    private final IExpression expression;

    public New(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }
    public String getVariableName() {
        return variableName;
    }
    public IExpression getExpression() {
        return expression;
    }
    @Override
    public String toString() {
        return "NEW(" + this.variableName + ";" + this.expression.toString() + ")";
    }
    @Override
    public ProgramState execute(ProgramState state) {
        var symbolTable = state.getSymbolTable();
        var heapTable = state.getHeapTable();
        if(!symbolTable.isDefined(this.variableName)) {
            throw new StatementException("Variable " + this.variableName + " is not defined");
        }
        IValue value = state.getSymbolTable().getValue(this.variableName);
        if(!(value.getType() instanceof RefType)) {
            throw new StatementException("Type of " + this.variableName + " is not a ref");
        }
        IValue expressionValue = this.expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        RefValue refValue = (RefValue) value;
        if(!expressionValue.getType().equals(refValue.getLocationType())) {
            throw new StatementException("Types are not the same");
        }
        int addressUsed = heapTable.addHeapEntry(expressionValue);
        symbolTable.update(this.variableName, new RefValue(addressUsed, refValue.getLocationType()));
        return null;
    }
    @Override
    public IStatement deepCopy() {
        return new New(this.variableName, this.expression);
    }

    @Override
    public MyIMap<String, IType> typeCheck(MyIMap<String, IType> typeEnv) throws StatementException{
        IType typeVar = typeEnv.get(this.variableName);
        IType typeExp = this.expression.typeCheck(typeEnv);

        if(!(typeVar.equals(new RefType(typeExp))))
            throw new StatementException("NEW stm: different types");
        return typeEnv;
    }
}
