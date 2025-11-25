package model.state;

import model.map.MyMap;
import model.type.*;
import model.value.*;

public interface ISymbolTable {
    boolean isDefined(String variableName);
    IType getType(String variableName);
    void declareVariable(String variableName, IType type);
    void update(String variableName, IValue value);
    IValue getValue(String variableName);
    String toString();
    void clear();
    MyMap<String, IValue> getContent();
}
