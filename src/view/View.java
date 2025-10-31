package view;

import controller.Controller;
import model.exception.ADTException;
import model.exception.ControllerException;
import model.exception.ExpressionsEvaluation;
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

import javax.management.ValueExp;
import javax.swing.plaf.nimbus.State;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;







public class View {
    private final Controller controller;

    public View(){
        this.controller = new Controller();
    }

    private void menu(){

        String reset = "\u001B[0m";
        String green = "\u001B[32m";
        String white = "\u001B[37m";
        String greenV2 = "\u001B[92m";
        String whiteV2 = "\u001B[97m";
        String gray = "\u001B[38;5;250m";

        System.out.println(white + "---------------------" + reset);
        System.out.println(gray + "1." + reset + whiteV2 + "int v; v=2;Print(v)" + reset);
        System.out.println(gray + "2." + reset + whiteV2 +"int a;int b; a=2+3*5;b=a+1;Print(b)" + reset);
        System.out.println(gray + "3." + reset + whiteV2 +"bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)" + reset);
        System.out.println(gray + "0." + reset + whiteV2  +"Exit\uD83D\uDED1" + reset);
        System.out.println(white + "---------------------" + reset);
    }



    public void start(){



        System.out.println(Colors.WHITE + "------------\uD83E\uDDD1\uD83C\uDFFB\u200D\uD83D\uDCBB-------------" + Colors.RESET);
        System.out.println(Colors.BLUE + "\uD83D\uDE82Welcome to Toy Language\uD83E\uDDF8" + Colors.RESET);
        System.out.println(Colors.WHITE +"-------------------------" + Colors.RESET);
        System.out.println();

        int fails = 0;
        boolean exit = false;
        while(!exit){
            menu();
            System.out.print("\uD83E\uDEF4");
            Scanner input = new Scanner(System.in);
            int choice = 0;
            try {
                choice = input.nextInt();
                switch (choice) {
                    case 0 -> {
                        exit = true;
                        System.out.println(Colors.PEACH + "\uD83D\uDC4BSEE YOU SOON!\uD83E\uDD17" + Colors.RESET);
                    }
                    case 1 -> {
                        fails = 0;
                        Statement s1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new PrintStatement(new VariableExpression("v"))));
                        this.controller.addProgram(new ProgramState(s1));
                        List<String> list = this.controller.allStep();
                        exec(list);
                    }
                    case 2 -> {
                        fails = 0;
                        Statement s2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("{", new ValueExpression(new IntegerValue(2)), new ArithmeticExpression("*", new ValueExpression(new IntegerValue(3)),
                                                new ValueExpression(new IntegerValue(5))))), new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new IntegerValue(1)))),
                                                new PrintStatement(new VariableExpression("b"))))));
                        this.controller.addProgram(new ProgramState(s2));
                        List<String> list = this.controller.allStep();
                        exec(list);
                    }
                    case 3 -> {
                        fails = 0;
                        Statement s3 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()),
                                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))), new PrintStatement(new VariableExpression("v"))))));
                        this.controller.addProgram(new ProgramState(s3));
                        List<String> list = this.controller.allStep();
                        exec(list);


                    }
                }
            }catch (ControllerException | ExpressionsEvaluation | ADTException | StatementException e){
                if(fails>=2){
                    System.out.println(Colors.RED +"REALLY?\uD83E\uDD28" + Colors.RESET);
                }
                System.out.println(Colors.RED + "\uD83E\uDD37\u200D\uFE0F" + e.getMessage() + "\uD83D\uDC94" + Colors.RESET);
                fails++;
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
