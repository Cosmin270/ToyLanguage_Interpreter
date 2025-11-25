package model.state;

import model.type.IType;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;

public interface IFileTable {
    boolean isDefined(StringValue variableName);
    void declareFile(StringValue variableName, BufferedReader fd);
    BufferedReader getValue(StringValue variableName);
    String toString();
    void clear();
    void removeByKey(StringValue variableName);
}
