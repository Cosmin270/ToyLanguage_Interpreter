package model.statement;

import model.expression.Expression;
import model.state.ProgramState;
import model.value.Value;

public record PrintStatement(Expression expression) implements Statement {

    public String toString(){
        return "print(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        Value expressionValue = this.expression.evaluate(state.getSymbolTable());
        state.getOut().add(expressionValue);
        return state;
    }
    @Override
    public Statement deepCopy() {
        return new PrintStatement(this.expression);
    }
}
