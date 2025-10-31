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
import java.util.List;
import java.util.Scanner;

public class View {
    private Controller controller;

    public View(){
        this.controller = new Controller();
    }

    private void menu(){
        System.out.println("---------------------");
        System.out.println("1.int v; v=2;Print(v)");
        System.out.println("2.int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("3.bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("0.Exit");
        System.out.println("---------------------");
    }



    public void start(){
        System.out.println("-------------------------");
        System.out.println("Welcome to Toy Language");
        System.out.println("-------------------------");
        System.out.println();


        boolean exit = false;
        while(!exit){
            menu();
            System.out.print(">");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            try {
                switch (choice) {
                    case 0 -> {
                        exit = true;
                    }
                    case 1 -> {
                        Statement s1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new PrintStatement(new VariableExpression("v"))));
                        this.controller.addProgram(new ProgramState(s1));
                        List<String> list = this.controller.allStep();
                        exec(list);
                    }
                    case 2 -> {
                        Statement s2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntegerValue(2)), new ArithmeticExpression("*", new ValueExpression(new IntegerValue(3)),
                                                new ValueExpression(new IntegerValue(5))))), new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new IntegerValue(1)))),
                                                new PrintStatement(new VariableExpression("b"))))));
                        this.controller.addProgram(new ProgramState(s2));
                        List<String> list = this.controller.allStep();
                        exec(list);
                    }
                    case 3 -> {
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
                System.out.println(e.getMessage());
            }
        }
    }

    private void exec(List<String> list){
        int cnt = 0;
        for(String s : list){
            System.out.println("----------");
            System.out.println(s);
            System.out.println("----------");
            System.out.println();
        }

    }
}
