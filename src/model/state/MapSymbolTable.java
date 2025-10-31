package model.state;

import model.map.MyMap;
import model.type.*;
import model.value.*;

import java.util.Map;


public class MapSymbolTable implements SymbolTable {

    private final MyMap<String, Value> map = new MyMap<>();

    @Override
    public boolean isDefined(String variableName){
        return map.containsKey(variableName);
    }

    @Override
    public Type getType(String variableName){
        return map.get(variableName).getType();
    }

    @Override
    public Value getValue(String variableName){
        return map.get(variableName);
    }

    @Override
    public void declareVariable(String variableName, Type type){
        map.put(variableName, type.getDefaultValue());
    }

    @Override
    public void update(String variableName, Value value){
        map.put(variableName, value);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Value> entry: map){
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("; ");
        }
        return sb.toString();
    }
}
