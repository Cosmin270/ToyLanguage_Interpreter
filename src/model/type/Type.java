package model.type;

import model.value.*;

public interface Type {
    Value getDefaultValue();
    Type deepCopy();

}
