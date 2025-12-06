package model.statement;

import model.exception.StatementException;
import model.expression.IExpression;
import model.state.ProgramState;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.IValue;

public class WhileStatement implements IStatement {
    private final IExpression expression;
    private final IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "While( " + expression.toString() + " ) {" + statement.toString() + "}";
    }
    @Override
    public IStatement deepCopy() {
        return new WhileStatement(this.expression, this.statement);
    }
    @Override
    public ProgramState execute(ProgramState state) {
        IValue condition = this.expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(!condition.getType().equals(new BooleanType())){
            throw new StatementException("Condition expression is not a boolean");
        }
        BooleanValue conditionValue = (BooleanValue) condition;
        if(conditionValue.getValue()){
            state.getExecutionStack().push(this);
            state.getExecutionStack().push(statement);
        }
        return null;
    }
}
