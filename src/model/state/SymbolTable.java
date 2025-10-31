package model.state;

import model.type.*;
import model.value.*;

public interface SymbolTable {
    boolean isDefined(String variableName);
    Type getType(String variableName);
    void declareVariable(String variableName, Type type);
    void update(String variableName, Value value);
    Value getValue(String variableName);
    String toString();
}
