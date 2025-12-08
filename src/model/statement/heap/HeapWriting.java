package model.statement.heap;

import model.exception.StatementException;
import model.expression.IExpression;
import model.map.MyIMap;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.state.ProgramState;
import model.statement.IStatement;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;

public class HeapWriting implements IStatement {
    private final String variableName;
    private final IExpression expression;

    public HeapWriting(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }
    @Override
    public String toString() {
        return "Heap Writing " + variableName + " " + expression.toString();
    }
    @Override
    public IStatement deepCopy() {
        return new HeapWriting(variableName, expression);
    }
    @Override
    public ProgramState execute(ProgramState state) {
        ISymbolTable symbolTable = state.getSymbolTable();
        IHeapTable heapTable = state.getHeapTable();
        if(!symbolTable.isDefined(variableName)) {
            throw new StatementException("Variable " + variableName + " is not defined");
        }
        IValue value = symbolTable.getValue(variableName);
        if(!(value.getType() instanceof RefType)){
            throw new StatementException("Variable " + variableName + " is not of type RefType");
        }
        RefValue refValue = (RefValue) value;
        if(!heapTable.isDefined(refValue.getAddress())){
            throw new StatementException("Variable " + variableName + " is not defined");
        }

        IValue expressionValue = this.expression.evaluate(symbolTable, heapTable);
        if(!expressionValue.getType().equals(refValue.getLocationType())){
            throw new StatementException("Variable " + variableName + " is not of type RefType");
        }
        heapTable.updateHeapEntry(refValue.getAddress(), expressionValue);
        return null;
    }

    @Override
    public MyIMap<String, IType> typeCheck(MyIMap<String, IType> typeEnv) throws StatementException{
        IType typeVar = typeEnv.get(this.variableName);
        IType typeExp = this.expression.typeCheck(typeEnv);

        if(! (typeVar.equals(new RefType(typeExp))))
            throw new StatementException("Different types");
        return typeEnv;
    }
}
