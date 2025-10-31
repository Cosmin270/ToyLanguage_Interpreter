package model.state;

import model.statement.Statement;


public class ProgramState{
    private final ExecutionStack executionStack;
    private final SymbolTable symbolTable;
    private final ListOut out;
    private final Statement originalProgram;

    public ProgramState(ExecutionStack executionStack, SymbolTable symbolTable, ListOut out, Statement originalProgram){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
    }
    public ProgramState(Statement originalProgram){
        this.executionStack = new StackExecutionStack();
        this.symbolTable = new MapSymbolTable();
        this.out = new ListOut();
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
    }

    public ExecutionStack getExecutionStack() {return this.executionStack;}
    public SymbolTable getSymbolTable() {return this.symbolTable;}
    public ListOut getOut() {return this.out;}
    public Statement getOriginalProgram() {return this.originalProgram;}

    @Override
    public String toString(){
        return "Execution Stack: " + this.executionStack.toString() +
                "\nSymbol Table: " + this.symbolTable.toString() +
                "\nOut: " + this.out.toString();
    }
}
