package model.state;

import model.map.MyMap;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class FileTable implements IFileTable{
//    private final Map<StringValue, BufferedReader> fileTable = new HashMap<>();
    private final MyMap<StringValue, BufferedReader> fileTable = new MyMap<>();

    @Override
    public boolean isDefined(StringValue variableName) {
        return fileTable.containsKey(variableName);
    }
    @Override
    public BufferedReader getValue(StringValue variableName){
        return fileTable.get(variableName);
    }
    @Override
    public void clear() {
        fileTable.clear();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<StringValue, BufferedReader> entry: fileTable){
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("; ");
        }
        return sb.toString();
    }
    @Override
    public void declareFile(StringValue variableName, BufferedReader fd){
        fileTable.put(variableName, fd);
    }
    @Override
    public void removeByKey(StringValue variableName) {
        this.fileTable.removeByKey(variableName);
    }
}
