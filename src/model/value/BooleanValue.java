package model.value;

import model.type.*;

public record BooleanValue(boolean value) implements IValue {
    @Override
    public IType getType(){return new BooleanType();}
    public boolean getValue(){return value;}

    @Override
    public String toString() {return "" + value;}
}
