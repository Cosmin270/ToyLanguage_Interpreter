package model.state;

import model.statement.IStatement;
import view.Colors;


public class ProgramState{
    private final IExecutionStack executionStack;
    private final ISymbolTable symbolTable;
    private final ListOut out;
    private final IStatement originalProgram;

    public ProgramState(IExecutionStack executionStack, ISymbolTable symbolTable, ListOut out, IStatement originalProgram){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
    }
    public ProgramState(IStatement originalProgram){
        this.executionStack = new StackExecutionStack();
        this.symbolTable = new MapSymbolTable();
        this.out = new ListOut();
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
    }

    public IExecutionStack getExecutionStack() {return this.executionStack;}
    public ISymbolTable getSymbolTable() {return this.symbolTable;}
    public ListOut getOut() {return this.out;}
    public IStatement getOriginalProgram() {return this.originalProgram;}

    @Override
    public String toString(){
        return Colors.CYAN + "Execution Stack: " + Colors.RESET + Colors.BRIGHT_WHITE + this.executionStack.toString() + Colors.RESET + Colors.CYAN +
                "\nSymbol Table: " + Colors.RESET + Colors.BRIGHT_WHITE + this.symbolTable.toString() + Colors.RESET + Colors.CYAN +
                "\nOut: " + Colors.RESET + Colors.BRIGHT_WHITE + this.out.toString() + Colors.RESET;
    }
}
