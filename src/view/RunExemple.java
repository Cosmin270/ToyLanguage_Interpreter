package view;

import controller.Controller;
import java.util.List;
import model.exception.*;

public class RunExemple extends Command {
    private final Controller controller;
    public RunExemple(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }
    @Override
    public void execute() {
        try{
            List<String>list = this.controller.allStep();
            exec(list);
        }
        catch(ControllerException | ExpressionsException | ADTException | StatementException | RepositoryException e){
            System.out.println(Colors.RED + e.getMessage() + Colors.RESET);
            this.controller.reset();
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
        //this.controller.getRepository().decrement();
        //System.out.println(this.controller.getFinalOutput());
        //this.controller.getRepository().increment();
        System.out.println(Colors.RED + "~~~~~~~~~~~~~~THE END\uD83D\uDC4C\uD83C\uDFFB~~~~~~~~~~~~~~" + Colors.RESET);
        System.out.println();
    }
}
