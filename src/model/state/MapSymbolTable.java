package model.state;

import model.map.MyMap;
import model.type.*;
import model.value.*;

import java.util.Map;


public class MapSymbolTable implements ISymbolTable {

    private final MyMap<String, IValue> map = new MyMap<>();

    @Override
    public boolean isDefined(String variableName){
        return map.containsKey(variableName);
    }

    @Override
    public IType getType(String variableName){
        return map.get(variableName).getType();
    }

    @Override
    public IValue getValue(String variableName){
        return map.get(variableName);
    }

    @Override
    public void declareVariable(String variableName, IType type){
        map.put(variableName, type.getDefaultValue());
    }

    @Override
    public void update(String variableName, IValue value){
        map.put(variableName, value);
    }

    public MyMap<String, IValue> getContent() { return this.map;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, IValue> entry: map){
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("; ");
        }
        return sb.toString();
    }
    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public void setContent(MyMap<String, IValue> content) {
        this.map.setContent(content);
    }
}
