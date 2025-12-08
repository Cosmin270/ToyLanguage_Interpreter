package model.statement;

import model.exception.StatementException;
import model.expression.IExpression;
import model.map.MyIMap;
import model.state.ProgramState;
import model.type.IType;
import model.value.IValue;

public record PrintStatement(IExpression expression) implements IStatement {

    public String toString(){
        return "print(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IValue expressionValue = this.expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        state.getOut().add(expressionValue);
        return null;
    }
    @Override
    public IStatement deepCopy() {
        return new PrintStatement(this.expression);
    }

    @Override
    public MyIMap<String, IType> typeCheck(MyIMap<String, IType> typeEnv) throws StatementException{
        this.expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
