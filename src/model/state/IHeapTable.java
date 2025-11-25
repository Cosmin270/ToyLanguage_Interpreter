package model.state;

import model.expression.IExpression;
import model.map.MyMap;
import model.value.IValue;
import model.value.IntegerValue;
import model.value.StringValue;

import java.util.Map;

public interface IHeapTable {
    int addHeapEntry(IValue value);
    IValue getValue(int address);
    void updateHeapEntry(int address, IValue newValue);
    boolean isDefined(int address);
    MyMap<Integer, IValue> getHeap();
    void clear();
    String toString();
    void setContent(MyMap<Integer, IValue> heap);
}
