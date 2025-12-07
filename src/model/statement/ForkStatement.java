package model.statement;


import java.util.stream.Collectors;
import model.map.MyMap;
import model.state.*;
import model.value.IValue;

public class ForkStatement implements IStatement {
    private final IStatement statement;

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
