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

        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";
        String cyan = "\u001B[38;5;45m";
        String whiteV2 = "\u001B[97m";
        return  cyan + "Execution Stack: " + reset + whiteV2 + this.executionStack.toString() + reset + cyan +
                "\nSymbol Table: " + reset + whiteV2 + this.symbolTable.toString() + reset + cyan +
                "\nOut: " + reset + whiteV2 + this.out.toString() + reset;
    }
}
