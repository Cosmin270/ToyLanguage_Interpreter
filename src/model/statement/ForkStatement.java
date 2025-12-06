package model.statement;


import model.map.MyMap;
import model.stack.MyStack;
import model.state.*;
import model.value.IValue;

import java.util.Map;
import java.util.stream.Collectors;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }
    public ForkStatement() {
        this.statement = null;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IExecutionStack forkedStack = new StackExecutionStack();
        MyMap<String, IValue> symbolTable = state.getSymbolTable().getContent();
        MyMap<String, IValue> newSymbolTable = new MyMap<>();
        ISymbolTable forkedSymbolTable = new MapSymbolTable();

        newSymbolTable.setContent(symbolTable.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().deepCopy())));
        forkedSymbolTable.setContent(newSymbolTable);
        return new ProgramState(forkedStack, forkedSymbolTable, state.getOut(), state.getFileTable(), state.getHeapTable(), this.statement);
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(this.statement);
    }
    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
    }
}
