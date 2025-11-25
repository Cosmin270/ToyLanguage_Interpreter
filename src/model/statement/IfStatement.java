package model.statement;

import model.exception.StatementException;
import model.expression.IExpression;
import model.state.ProgramState;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.IValue;

public class IfStatement implements IStatement {

    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "IF(" + this.expression.toString() + ") THEN (" + this.thenStatement.toString() + ") ELSE (" + elseStatement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IValue value = expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(!value.getType().equals(new BooleanType())) {
            throw new StatementException("IfStatement only accepts boolean");
        }
        else{
            BooleanValue booleanValue = (BooleanValue) value;
            if(booleanValue.getValue()) {
                state.getExecutionStack().push(this.thenStatement);
            }
            else{
                state.getExecutionStack().push(this.elseStatement);
            }

        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(this.expression, this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
    }

}
