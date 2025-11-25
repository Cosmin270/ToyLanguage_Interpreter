package model.state;

import model.expression.IExpression;
import model.map.MyMap;
import model.type.IType;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.util.Map;

public class HeapTable implements IHeapTable {
    private final MyMap<Integer, IValue> heap;
    private int nextFreeLocation;

    public HeapTable() {
        this.heap = new MyMap<>();
        this.nextFreeLocation = 1;
    }

    @Override
    public int addHeapEntry(IValue value) {
        this.heap.put(this.nextFreeLocation++, value);
        return nextFreeLocation-1;
    }

    @Override
    public IValue getValue(int address) {
        return this.heap.get(address);
    }
    @Override
    public void updateHeapEntry(int address, IValue newValue) {
        this.heap.put(address, newValue);
    }
    @Override
    public void clear(){
        this.heap.clear();
    }
    @Override
    public boolean isDefined(int address) {
        return this.heap.containsKey(address);
    }
    @Override
    public MyMap<Integer, IValue> getHeap(){
        return this.heap;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, IValue> entry: heap){
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("; ");
        }
        return sb.toString();
    }
    @Override
    public void setContent(MyMap<Integer, IValue> heap) {
        this.heap.clear();
        this.heap.putAll(heap);
    }
}
