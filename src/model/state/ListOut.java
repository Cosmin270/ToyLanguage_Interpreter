package model.state;

import model.list.MyList;
import model.value.IValue;


public class ListOut implements IOut {

    private final MyList<IValue> list = new MyList<>();

    @Override
    public void add(IValue value) {
        list.add(value);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(IValue v : list){
            sb.append(v.toString()).append("; ");
        }
        return sb.toString();
    }
    @Override
    public void clear(){
        list.clear();
    }
}
