package model.state;

import model.exception.MyException;
import model.statement.IStatement;


public class ProgramState{
    private final IExecutionStack executionStack;
    private final ISymbolTable symbolTable;
    private final ListOut out;
    private final FileTable fileTable;
    private final HeapTable heapTable;
    private final IStatement originalProgram;
    private static int generalId = 0;
    private final int id;

    public ProgramState(IExecutionStack executionStack, ISymbolTable symbolTable, ListOut out,FileTable filetable,HeapTable heapTable ,IStatement originalProgram){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.fileTable = filetable;
        this.heapTable = heapTable;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
        incrementGeneralId();
        this.id = getGeneralId();
    }
    public ProgramState(IStatement originalProgram){
        this.executionStack = new StackExecutionStack();
        this.symbolTable = new MapSymbolTable();
        this.out = new ListOut();
        this.fileTable = new FileTable();
        this.heapTable = new HeapTable();
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
        incrementGeneralId();
        this.id = getGeneralId();
    }
    public static synchronized int getGeneralId(){return generalId;}
    public static synchronized void incrementGeneralId(){generalId++;}
    public IExecutionStack getExecutionStack() {return this.executionStack;}
    public ISymbolTable getSymbolTable() {return this.symbolTable;}
    public ListOut getOut() {return this.out;}
    public FileTable getFileTable() {return this.fileTable;}
    public HeapTable getHeapTable() {return this.heapTable;}
    public IStatement getOriginalProgram() {return this.originalProgram;}

    @Override
    public String toString(){
        return  "-".repeat(20) + "\n" +
                "ID: " + this.id +
                "\n\nExecution Stack: \n" + this.executionStack.toString() +
                "\n\nSymbol Table: \n"  + this.symbolTable.toString() +
                "\n\nOut: \n" + this.out.toString() +
                "\n\nFileTable: \n" + this.fileTable.toString() +
                "\n\nHeapTable: \n" + this.heapTable.toString() +
                "\n" + "-".repeat(20) + "\n";
    }

    public ProgramState deepCopy(){
        return new ProgramState(this.executionStack, this.symbolTable, this.out, this.fileTable,this.heapTable ,this.originalProgram);
    }

    public boolean isNotComplete(){
        return !this.executionStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException {
        if(this.executionStack.isEmpty()){
            throw new MyException("Execution stack is empty");
        }
        IStatement statement = this.executionStack.pop();
        return statement.execute(this);
    }
}

