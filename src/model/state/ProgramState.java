package model.state;

import model.statement.Statement;
import view.Colors;


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
        return Colors.CYAN + "Execution Stack: " + Colors.RESET + Colors.BRIGHT_WHITE + this.executionStack.toString() + Colors.RESET + Colors.CYAN +
                "\nSymbol Table: " + Colors.RESET + Colors.BRIGHT_WHITE + this.symbolTable.toString() + Colors.RESET + Colors.CYAN +
                "\nOut: " + Colors.RESET + Colors.BRIGHT_WHITE + this.out.toString() + Colors.RESET;
    }
}
