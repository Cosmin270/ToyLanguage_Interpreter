package model.state;

import model.list.MyList;
import model.value.Value;


public class ListOut implements Out {

    private final MyList<Value> list = new MyList<>();

    @Override
    public void add(Value value) {
        list.add(value);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Value v : list){
            sb.append(v.toString()).append("; ");
        }
        return sb.toString();
    }
}
