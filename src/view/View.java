package view;

import controller.Controller;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import model.exception.ADTException;
import model.exception.ControllerException;
import model.exception.ExpressionsException;
import model.exception.StatementException;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.state.ProgramState;
import model.statement.*;
import model.type.BooleanType;
import model.type.IntType;
import model.value.BooleanValue;
import model.value.IntegerValue;


public class View {

    private final Controller controller;
    private static final AtomicBoolean NORMAL_EXIT = new AtomicBoolean(false);

    public View(){
        this.controller = new Controller();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(!NORMAL_EXIT.get()){
                System.out.println(Colors.RED + "\n⚠️ Program interrupted!\uFE0F\uD83D\uDE22 You were not nice here!\uD83D\uDE23");
                System.out.println("Goodbye 👋" + Colors.RESET);
            }
        }));
    }
    public void start(){

        this.title();

        int fails = 0;
        boolean exit = false;

        while(!exit){
            this.waitUser();
            String choice = this.menu();
            try {
                switch (choice) {
                    case "0" -> {
                        NORMAL_EXIT.set(true);
                        exit = true;
                        System.out.println(Colors.PEACH + "\uD83D\uDC4BSEE YOU SOON!\uD83E\uDD17" + Colors.RESET);
                    }
                    case "1" -> {
                        this.statement1();
                        fails = 0;
                    }
                    case "2" -> {
                        this.statement2();
                        fails = 0;
                    }
                    case "3" -> {
                        this.statement3();
                        fails = 0;
                    }
                    case "on" ->{
                        this.controller.turnOnDisplayFlag();
                        System.out.println(Colors.BRIGHT_WHITE + "Display flag is ON" + Colors.RESET);
                    }
                    case "off" ->{
                        this.controller.turnOffDisplayFlag();
                        System.out.println(Colors.BRIGHT_WHITE + "Display flag is OFF" + Colors.RESET);
                    }
                    default -> {
                        if(fails>=2){
                            System.out.println(Colors.RED +"REALLY?\uD83E\uDD28" + Colors.RESET);
                        }
                        System.out.println(Colors.RED + "Invalid input\uD83D\uDC94" +  Colors.RESET);
                        fails+=1;
                    }
                }
            }
            catch (ControllerException | ExpressionsException | ADTException | StatementException e){
                if(fails>=2){
                    System.out.println(Colors.RED +"REALLY?\uD83E\uDD28" + Colors.RESET);
                }
                System.out.println(Colors.RED + "\uD83E\uDD37\u200D\uFE0F" + e.getMessage() + "\uD83D\uDC94" + Colors.RESET);
                fails++;
                this.controller.reset();

            }
            catch (InputMismatchException e) {
                if(fails>=2){
                    System.out.println(Colors.RED +"REALLY?\uD83E\uDD28" + Colors.RESET);
                }
                System.out.println(Colors.RED + "\uD83E\uDD37\u200D\uFE0FInvalid Input\uD83D\uDC94" + Colors.RESET);
                fails++;
            }
        }
    }


    // -------- Helper functions --------

    private void title(){
        System.out.println(Colors.WHITE + "------------\uD83E\uDDD1\uD83C\uDFFB\u200D\uD83D\uDCBB--------------" + Colors.RESET);
        System.out.println(Colors.CYAN + "\uD83D\uDE82Welcome to Toy Language\uD83E\uDDF8" + Colors.RESET);
        System.out.println(Colors.WHITE +"----------------------------" + Colors.RESET);
        System.out.println();
    }
    private String menu(){
        Scanner input = new Scanner(System.in);
        String flag = this.controller.getDisplayFlag() ? "on" : "off";
        System.out.println(Colors.WHITE + "---------- OPTIONS ----------" + Colors.RESET);
        System.out.println(Colors.BRIGHT_WHITE + "[Display Mode -> " + flag + " ]" + Colors.RESET);
        System.out.println(Colors.GRAY + "• on  " + Colors.RESET + Colors.WHITE + "→ Show program state after each execution" + Colors.RESET);
        System.out.println(Colors.GRAY + "• off " + Colors.RESET + Colors.WHITE + "→ Run silently (no state shown)" + Colors.RESET);
        System.out.println();
        System.out.println(Colors.WHITE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.GRAY + "What program would you like to run?\uD83E\uDD14" + Colors.RESET);
        System.out.println(Colors.GRAY + "1.++++++++++++++++++" + Colors.RESET + Colors.BRIGHT_WHITE + "\nint v;\nv=2;\nPrint(v);\n" + Colors.RESET + Colors.GRAY + "++++++++++++++++++++" + Colors.RESET);
        System.out.println(Colors.GRAY + "2.++++++++++++++++++" + Colors.RESET + Colors.BRIGHT_WHITE +"\nint a;\nint b;\na=2+3*5;\nb=a+1;\nPrint(b);\n"+ Colors.RESET + Colors.GRAY +"++++++++++++++++++++" + Colors.RESET);
        System.out.println(Colors.GRAY + "3.++++++++++++++++++" + Colors.RESET + Colors.BRIGHT_WHITE +"\nbool a;\nint v;\na=true;\nIf a Then\n  v=2;\nElse\n  v=3;\nPrint(v);\n"+ Colors.RESET + Colors.GRAY +"++++++++++++++++++++" + Colors.RESET);
        System.out.println(Colors.GRAY + "0." + Colors.RESET + Colors.BRIGHT_WHITE  +"Exit\uD83D\uDED1\n" + Colors.RESET);
        System.out.println(Colors.GRAY + "(More options in future updates\uD83E\uDD2B)" + Colors.RESET);
        System.out.println(Colors.GRAY + "~VERSION 1.0~" + Colors.RESET);
        System.out.println(Colors.WHITE + "---------------------" + Colors.RESET);
        System.out.print("→");
        return (String) input.nextLine();
    }
    private void waitUser(){
        System.out.println(Colors.GRAY + "Press any key to continue..." + Colors.RESET);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
    private void statement1(){
        IStatement s1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        this.controller.addProgram(new ProgramState(s1));
        List<String> list = this.controller.allStep();
        exec(list);
    }
    private void statement2(){
        IStatement s2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("{", new ValueExpression(new IntegerValue(2)), new ArithmeticExpression("*", new ValueExpression(new IntegerValue(3)),
                                new ValueExpression(new IntegerValue(5))))), new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new IntegerValue(1)))),
                                new PrintStatement(new VariableExpression("b"))))));
        this.controller.addProgram(new ProgramState(s2));
        List<String> list = this.controller.allStep();
        exec(list);
    }
    private void statement3(){
        IStatement s3 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        this.controller.addProgram(new ProgramState(s3));
        List<String> list = this.controller.allStep();
        exec(list);
    }
//    private void statement4(){
//        IStatement s4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
//                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
//                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")),
//                                new VariableDeclarationStatement("varc", new IntType()),
//                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), )))))
//    }
    private void exec(List<String> list){
        int cnt = 1;
        for(String s : list){
            System.out.println(Colors.BLUE + "~~~~~~~~~~~~~~~~" + Colors.RESET + Colors.BRIGHT_WHITE + cnt + Colors.RESET + Colors.BLUE + "~~~~~~~~~~~~~~~~" + Colors.RESET);
            System.out.println(Colors.WHITE + "----------" + Colors.RESET);
            System.out.println(s);
            System.out.println(Colors.WHITE + "----------" + Colors.RESET);
            System.out.println();
            cnt += 1;
        }
        this.controller.getRepository().decrement();
        //System.out.println(this.controller.getFinalOutput());
        this.controller.getRepository().increment();
        System.out.println(Colors.RED + "~~~~~~~~~~~~~~THE END\uD83D\uDC4C\uD83C\uDFFB~~~~~~~~~~~~~~" + Colors.RESET);
        System.out.println();
    }
}
