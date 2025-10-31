package model.statement;

import model.exception.MyException;
import model.exception.StatementException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.Value;

public class IfStatement implements Statement {

    Expression expression;
    Statement thenStatement;
    Statement elseStatement;

    public IfStatement(Expression expression, Statement thenStatement, Statement elseStatement) {
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
        Value value = expression.evaluate(state.getSymbolTable());
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
    public Statement deepCopy() {
        return new IfStatement(this.expression, this.thenStatement, this.elseStatement);
    }

}
