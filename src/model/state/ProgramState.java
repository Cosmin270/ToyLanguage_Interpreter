package model.state;

import model.statement.IStatement;
import view.Colors;

import java.io.File;


public class ProgramState{
    private final IExecutionStack executionStack;
    private final ISymbolTable symbolTable;
    private final ListOut out;
    private final FileTable fileTable;
    private final HeapTable heapTable;
    private final IStatement originalProgram;


    public ProgramState(IExecutionStack executionStack, ISymbolTable symbolTable, ListOut out,FileTable filetable,HeapTable heapTable ,IStatement originalProgram){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.fileTable = filetable;
        this.heapTable = heapTable;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
    }
    public ProgramState(IStatement originalProgram){
        this.executionStack = new StackExecutionStack();
        this.symbolTable = new MapSymbolTable();
        this.out = new ListOut();
        this.fileTable = new FileTable();
        this.heapTable = new HeapTable();
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
    }

    public IExecutionStack getExecutionStack() {return this.executionStack;}
    public ISymbolTable getSymbolTable() {return this.symbolTable;}
    public ListOut getOut() {return this.out;}
    public FileTable getFileTable() {return this.fileTable;}
    public HeapTable getHeapTable() {return this.heapTable;}
    public IStatement getOriginalProgram() {return this.originalProgram;}


    @Override
//    public String toString(){
//        return Colors.CYAN + "Execution Stack: \n" + Colors.RESET + Colors.BRIGHT_WHITE + this.executionStack.toString() + Colors.RESET + Colors.CYAN +
//                "\nSymbol Table: \n" + Colors.RESET + Colors.BRIGHT_WHITE + this.symbolTable.toString() + Colors.RESET + Colors.CYAN +
//                "\nOut: \n" + Colors.RESET + Colors.BRIGHT_WHITE + this.out.toString() + Colors.RESET + Colors.CYAN +
//                "\nFileTable: \n" + Colors.RESET + Colors.BRIGHT_WHITE + this.fileTable.toString();
//    }
    public String toString(){
        return  "Execution Stack: \n" + this.executionStack.toString() +
                "\n\nSymbol Table: \n"  + this.symbolTable.toString() +
                "\n\nOut: \n" + this.out.toString() +
                "\n\nFileTable: \n" + this.fileTable.toString() +
                "\n\nHeapTable: \n" + this.heapTable.toString();
    }

    public ProgramState deepCopy(){
        return new ProgramState(this.executionStack, this.symbolTable, this.out, this.fileTable,this.heapTable ,this.originalProgram);
    }
}

