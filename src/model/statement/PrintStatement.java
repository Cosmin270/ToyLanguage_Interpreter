package model.statement;

import model.expression.IExpression;
import model.state.ProgramState;
import model.value.IValue;

public record PrintStatement(IExpression expression) implements IStatement {

    public String toString(){
        return "print(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IValue expressionValue = this.expression.evaluate(state.getSymbolTable());
        state.getOut().add(expressionValue);
        return state;
    }
    @Override
    public IStatement deepCopy() {
        return new PrintStatement(this.expression);
    }
}
