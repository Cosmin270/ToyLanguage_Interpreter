package model.type;

import model.value.*;

public interface IType {
    IValue getDefaultValue();
    IType deepCopy();

}
