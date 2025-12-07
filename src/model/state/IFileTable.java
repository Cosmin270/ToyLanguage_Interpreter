package model.state;

import java.io.BufferedReader;
import model.value.StringValue;

public interface IFileTable {
    boolean isDefined(StringValue variableName);
    void declareFile(StringValue variableName, BufferedReader fd);
    BufferedReader getValue(StringValue variableName);
    @Override
    String toString();
    void clear();
    void removeByKey(StringValue variableName);
}
