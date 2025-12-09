package model.statement;

import model.exception.StatementException;
import model.expression.IExpression;
import model.map.MyIMap;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.state.ProgramState;
import model.type.IType;
import model.value.IValue;

public record AssignmentStatement(String variableName, IExpression expression) implements IStatement {

    @Override
    public ProgramState execute(ProgramState state){
            ISymbolTable symbolTable = state.getSymbolTable();
            IHeapTable heapTable = state.getHeapTable();
            if(!symbolTable.isDefined(this.variableName)){
                throw new StatementException("Variable " + this.variableName + " is not defined");
            }
            IValue value = expression.evaluate(symbolTable, heapTable);
            if(!value.getType().equals(symbolTable.getType(this.variableName))){
                throw new StatementException("Type mismatch");
            }
            symbolTable.update(this.variableName, value);
            return null;
    }
    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(this.variableName, this.expression);
    }

    @Override
    public String toString(){
        return this.variableName + " = " + this.expression.toString();
    }

    @Override
    public MyIMap<String, IType> typeCheck(MyIMap<String, IType> typeEnv) throws StatementException{
        IType typeVar = typeEnv.get(this.variableName);
        IType typeExp = this.expression.typeCheck(typeEnv);

        if(!(typeVar.equals(typeExp)))
            throw new StatementException("ASSIGNMENT:Diferrent types");
        
        return typeEnv;
    }
}
