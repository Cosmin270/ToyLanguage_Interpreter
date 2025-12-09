

/// if u get an error from type checker do smth w run exemple
/// add a dummy command 


package view;

import controller.Controller;
import model.exception.StatementException;
import model.expression.*;
import model.map.MyIMap;
import model.map.MyMap;
import model.state.ProgramState;
import model.statement.*;
import model.statement.file.CloseRFile;
import model.statement.file.OpenRFile;
import model.statement.file.ReadFile;
import model.statement.heap.HeapWriting;
import model.statement.heap.New;
import model.type.BooleanType;
import model.type.IType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BooleanValue;
import model.value.IntegerValue;
import model.value.StringValue;
import pair.Pair;
import repository.IRepository;
import repository.Repository;

public class Interpreter {
        public  static void main(String[] args) {

                Pair<String, Controller> ex1 = exemple1();
                Pair<String, Controller> ex2 = exemple2();
                Pair<String, Controller> ex3 = exemple3();
                Pair<String, Controller> ex4 = exemple4();
                Pair<String, Controller> ex5 = exemple5();
                Pair<String, Controller> ex6 = exemple6();
                Pair<String, Controller> ex7 = exemple7();
                Pair<String, Controller> ex8 = exemple8();
                Pair<String, Controller> ex9 = exemple9();
                Pair<String, Controller> ex10 = exemple10();

                TextMenu menu = new TextMenu();
                menu.addCommand(new ExitCommand("0", "Exit"));
                addExample(menu, "1", ex1);
                addExample(menu, "2", ex2);
                addExample(menu, "3", ex3);
                addExample(menu, "4", ex4);
                addExample(menu, "5", ex5);
                addExample(menu, "6", ex6);
                addExample(menu, "7", ex7);
                addExample(menu, "8", ex8);
                addExample(menu, "9", ex9);
                addExample(menu, "10", ex10);
                menu.show();
        }

        private static void addExample(TextMenu menu, String id, Pair<String, Controller> ex) {
                String first = ex.first();
                boolean invalid = first.split("\\s+")[0].equals("INVALID");

                if (invalid) {
                        menu.addCommand(new Dummy(id, first));
                } else {
                        menu.addCommand(new RunExemple(id, first, ex.second()));
                }
}

        private static Pair<String, Controller> exemple1(){
                try{
                        IStatement s1 = new CompoundStatement(new VariableDeclarationStatement("v", new BooleanType()),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new PrintStatement(new VariableExpression("v"))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s1.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s1);
                        IRepository repo1 = new Repository(state, "log1.txt");
                        Controller controller1 = new Controller(repo1);
                        String desc = "\n\t\tint v;\n\t\tv=2;\n\t\tPrint(v);\n";
                        return new Pair<>(desc, controller1);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }
    }
        private static Pair<String, Controller> exemple2(){

                try{
                        IStatement s2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("{", new ValueExpression(new IntegerValue(2)), new ArithmeticExpression("*", new ValueExpression(new IntegerValue(3)),
                                                new ValueExpression(new IntegerValue(5))))), new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new IntegerValue(1)))),
                                                new PrintStatement(new VariableExpression("b"))))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s2.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s2);
                        IRepository repo2 = new Repository(state, "log2.txt");
                        Controller controller2 = new Controller(repo2);

                        String desc = "\n\t\tint a;\n\t\tint b;\n\t\ta=2+3*5;\n\t\tb=a+1;\n\t\tPrint(b);\n";

                        return new Pair<>(desc, controller2);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }
    }
        private static Pair<String, Controller> exemple3(){
                try{
                        IStatement s3 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()),
                                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))), new PrintStatement(new VariableExpression("v"))))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s3.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s3);
                        IRepository repo3 = new Repository(state, "log3.txt");
                        Controller controller3 = new Controller(repo3);

                        String desc = "\n\t\tbool a;\n\t\tint v;\n\t\ta=true;\n\t\tIf a Then\n\t\t\t  v=2;\n\t\tElse\n\t\t\t  v=3;\n\t\tPrint(v);\n";

                        return new Pair<>(desc, controller3);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }

    }
        private static Pair<String, Controller> exemple4(){
                try{
                        IStatement s4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")),
                                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new CloseRFile(new VariableExpression("varf"))))))))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s4.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s4);
                        IRepository repo4 = new Repository(state, "log4.txt");
                        Controller controller4 = new Controller(repo4);

                        String desc = """
                                
                                \t\tstring varf;
                                \t\t\
                                varf="test.in";
                                \t\t\
                                openRFile(varf);
                                \t\t\
                                int varc;
                                \t\t\
                                readFile(varf,varc);
                                \t\tprint(varc);
                                \t\t\
                                readFile(varf,varc);
                                \t\tprint(varc)
                                \t\t\
                                closeRFile(varf)
                                """;

                        return new Pair<>(desc, controller4);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }
    }
        private static Pair<String, Controller> exemple5(){
                try{
                        IStatement s5 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntegerValue(5))),
                                        new IfStatement(new RelationalExpression("<", new VariableExpression("a"), new ValueExpression(new IntegerValue(10))), new PrintStatement(new ValueExpression(new StringValue("a<10"))), new PrintStatement(new ValueExpression(new StringValue("a>=10"))))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s5.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s5);
                        IRepository repo5 = new Repository(state, "log5.txt");
                        Controller controller5 = new Controller(repo5);
                        String desc = """
                        
                        \t\tint a;
                        \t\ta=5;
                        \t\tIf a < 10 Then
                        \t\t\tPrint(a<10);
                        \t\tElse
                        \t\t\tPrint(a>=10);
                        """;
                        return new Pair<>(desc, controller5);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }
    }
        private static Pair<String, Controller> exemple6(){
                try{
                        IStatement s6 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                                new CompoundStatement(new New("v", new ValueExpression(new IntegerValue(20))),
                                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                                new CompoundStatement(new New("a", new VariableExpression("v")),
                                                        new CompoundStatement(new New("v", new ValueExpression(new IntegerValue(30))),
                                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s6.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s6);
                        IRepository repo6 = new Repository(state, "log6.txt");
                        Controller controller6 = new Controller(repo6);
                        String desc = """
                                
                                \t\tRef int v;
                                \t\tNew(v, 20);
                                \t\tRef Ref int a;
                                \t\tNew(a, v);
                                \t\tNew(v, 30);
                                \t\tPrint(rH(rH(a));
                                """;
                        return new Pair<>(desc, controller6);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }
    }
        private static Pair<String, Controller> exemple7(){
                try{
                        IStatement s7 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                                new CompoundStatement(new New("v", new ValueExpression(new IntegerValue(20))),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new CompoundStatement(new HeapWriting("v", new ValueExpression(new IntegerValue(30))),
                                                        new PrintStatement(new ArithmeticExpression("+" ,new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntegerValue(5))))))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s7.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s7);
                        IRepository repo7 = new Repository(state, "log7.txt");
                        Controller controller7 = new Controller(repo7);
                        String desc = """
                                
                                \t\tRef int v;
                                \t\tNew(v, 20);
                                \t\tPrint(rH(v));
                                \t\twH(v, 30);
                                \t\tprint(rH(v)+5);
                        
                                """;
                        return new Pair<>(desc, controller7);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }

    }
        private static Pair<String, Controller> exemple8(){
                try{
                        IStatement s8 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                                new CompoundStatement(new New("v", new ValueExpression(new IntegerValue(20))),
                                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                                new CompoundStatement(new New("a", new VariableExpression("v")),
                                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                                new PrintStatement(new ArithmeticExpression("+" , new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntegerValue(5)))))))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s8.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s8);
                        IRepository repo8 = new Repository(state, "log8.txt");
                        Controller controller8 = new Controller(repo8);
                        String desc = """
                                
                                \t\tRef int v;
                                \t\tNew(v, 20);
                                \t\tRef Ref int a;
                                \t\tNew(a, v);
                                \t\tPrint(rH(v));
                                \t\tprint(rH(v)+5);
                                """;
                        return new Pair<>(desc, controller8);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }
    }
        private static Pair<String, Controller> exemple9(){
                try{
                        IStatement s9 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(4))),
                                        new CompoundStatement(new WhileStatement(new RelationalExpression(">", new VariableExpression("v"), new ValueExpression(new IntegerValue(0))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new AssignmentStatement("v", new ArithmeticExpression( "-",new VariableExpression("v"), new ValueExpression(new IntegerValue(1)))))),
                                                new PrintStatement(new VariableExpression("v")))));
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s9.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s9);
                        IRepository repo9 = new Repository(state, "log9.txt");
                        Controller controller9 = new Controller(repo9);
                        String desc = """
                                
                                \t\tint v;
                                \t\tv=4;
                                \t\twhile(v>0){
                                \t\t\tprint(v);
                                \t\t\tv=v-1;
                                \t\t}
                                \t\tprint(v);
                                """;
                        return new Pair<>(desc, controller9);
                }catch(StatementException e){
                        return new Pair<>("INVALID " + e.getMessage(), null);
                }
        }
        private static Pair<String, Controller> exemple10(){
                
                try{
                        IStatement s10 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new IntType())),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(10))),
                                new CompoundStatement(new New("a", new ValueExpression(new IntegerValue(22))),
                                new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWriting("a", new ValueExpression(new IntegerValue(30))),
                                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(32))),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));
                        
                        MyIMap<String, IType> typeEnv = new MyMap<>();
                        s10.typeCheck(typeEnv);
                        ProgramState state = new ProgramState(s10);
                        IRepository repo10 = new Repository(state, "log10.txt");
                        Controller controller10 = new Controller(repo10);
                        String desc = """
                                \t\t...
                                """;
                        return new Pair<>(desc, controller10);
                }catch(StatementException e){
                        return new Pair<>("INVALID" + e.getMessage(), null);
                }
        }
        
}
