package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private final Map<String, Command> commands;

    public TextMenu(){
        commands = new HashMap<>();
    }

    public void addCommand(Command c){
        commands.put(c.getKey(), c);
    }
    public void printMenu(){
        System.out.println(Colors.WHITE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.GRAY + "What program would you like to run?\uD83E\uDD14" + Colors.RESET);
        for(Command c : commands.values()){
            String line = String.format(Colors.BRIGHT_WHITE + "%4s : %s\n", c.getKey(), c.getDescription() + Colors.RESET);
            System.out.print(line);
        }
        System.out.println(Colors.WHITE + "------------------------------" + Colors.RESET);
    }

    public void show(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            waitUser();
            printMenu();
            System.out.print(Colors.GRAY + "> " + Colors.RESET);
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if(command == null){
                System.out.println(Colors.RED + "Command not found!" + Colors.RESET);
                continue;
            }
            command.execute();
        }
    }

    private void waitUser(){
        System.out.println(Colors.GRAY + "Press any key to continue..." + Colors.RESET);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
