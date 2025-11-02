package view;

import controller.Controller;
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

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


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

    private void title(){
        System.out.println(Colors.WHITE + "------------\uD83E\uDDD1\uD83C\uDFFB\u200D\uD83D\uDCBB--------------" + Colors.RESET);
        System.out.println(Colors.CYAN + "\uD83D\uDE82Welcome to Toy Language\uD83E\uDDF8" + Colors.RESET);
        System.out.println(Colors.WHITE +"----------------------------" + Colors.RESET);
        System.out.println();
    }


    private void menu(){


        System.out.println(Colors.WHITE + "--------------------" + Colors.RESET);
        System.out.println(Colors.GRAY + "What program would you like to run?\uD83E\uDD14" + Colors.RESET);
        System.out.println(Colors.GRAY + "1.++++++++++++++++++" + Colors.RESET + Colors.BRIGHT_WHITE + "\nint v;\nv=2;\nPrint(v);\n" + Colors.RESET + Colors.GRAY + "++++++++++++++++++++" + Colors.RESET);
        System.out.println(Colors.GRAY + "2.++++++++++++++++++" + Colors.RESET + Colors.BRIGHT_WHITE +"\nint a;\nint b;\na=2+3*5;\nb=a+1;\nPrint(b);\n"+ Colors.RESET + Colors.GRAY +"++++++++++++++++++++" + Colors.RESET);
        System.out.println(Colors.GRAY + "3.++++++++++++++++++" + Colors.RESET + Colors.BRIGHT_WHITE +"\nbool a;\nint v;\na=true;\nIf a Then\n  v=2;\nElse\n  v=3;\nPrint(v);\n"+ Colors.RESET + Colors.GRAY +"++++++++++++++++++++" + Colors.RESET);
        System.out.println(Colors.GRAY + "0." + Colors.RESET + Colors.BRIGHT_WHITE  +"Exit\uD83D\uDED1\n" + Colors.RESET);
        System.out.println(Colors.GRAY + "(More options in future updates\uD83E\uDD2B)" + Colors.RESET);
        System.out.println(Colors.GRAY + "~VERSION 1.0~" + Colors.RESET);
        System.out.println(Colors.WHITE + "---------------------" + Colors.RESET);
    }


    public void start(){

        title();

        int fails = 0;
        boolean exit = false;
        while(!exit){
            System.out.println(Colors.GRAY + "Press any key to continue..." + Colors.RESET);
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            menu();
            System.out.print("→");
            Scanner input = new Scanner(System.in);
            int choice = 0;
            try {
                choice = input.nextInt();
                switch (choice) {
                    case 0 -> {
                        NORMAL_EXIT.set(true);
                        exit = true;
                        System.out.println(Colors.PEACH + "\uD83D\uDC4BSEE YOU SOON!\uD83E\uDD17" + Colors.RESET);
                    }
                    case 1 -> {

                        IStatement s1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new PrintStatement(new VariableExpression("v"))));
                        this.controller.addProgram(new ProgramState(s1));
                        List<String> list = this.controller.allStep();
                        exec(list);
                        fails = 0;
                    }
                    case 2 -> {

                        IStatement s2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("{", new ValueExpression(new IntegerValue(2)), new ArithmeticExpression("*", new ValueExpression(new IntegerValue(3)),
                                                new ValueExpression(new IntegerValue(5))))), new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new IntegerValue(1)))),
                                                new PrintStatement(new VariableExpression("b"))))));
                        this.controller.addProgram(new ProgramState(s2));
                        List<String> list = this.controller.allStep();
                        exec(list);
                        fails = 0;
                    }
                    case 3 -> {
                        IStatement s3 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()),
                                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))), new PrintStatement(new VariableExpression("v"))))));
                        this.controller.addProgram(new ProgramState(s3));
                        List<String> list = this.controller.allStep();
                        exec(list);
                        fails = 0;

                    }
                }
            }catch (ControllerException | ExpressionsException | ADTException | StatementException e){
                if(fails>=2){
                    System.out.println(Colors.RED +"REALLY?\uD83E\uDD28" + Colors.RESET);
                }
                System.out.println(Colors.RED + "\uD83E\uDD37\u200D\uFE0F" + e.getMessage() + "\uD83D\uDC94" + Colors.RESET);
                fails++;
                this.controller.reset();

            } catch (InputMismatchException e) {
                if(fails>=2){
                    System.out.println(Colors.RED +"REALLY?\uD83E\uDD28" + Colors.RESET);
                }
                System.out.println(Colors.RED + "\uD83E\uDD37\u200D\uFE0FInvalid Input\uD83D\uDC94" + Colors.RESET);
                fails++;
            }
        }
    }

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

        System.out.println(Colors.RED + "~~~~~~~~~~~~~~THE END\uD83D\uDC4C\uD83C\uDFFB~~~~~~~~~~~~~~" + Colors.RESET);
        System.out.println();
    }


}
