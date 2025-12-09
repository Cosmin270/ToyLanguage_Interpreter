package view;

public class Dummy extends Command{
    public Dummy(String key, String desc){
        super(key, desc);
    }
    @Override
    public void execute(){
        System.out.println(Colors.RED + this.getDescription() + Colors.RESET);
    }
    
}
