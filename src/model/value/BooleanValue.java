package model.value;

import model.type.*;

public record BooleanValue(boolean value) implements IValue {
    @Override
    public IType getType(){return new BooleanType();}
    public boolean getValue(){return value;}

    @Override
    public String toString() {return "" + value;}

    @Override
    public boolean equals(Object other){return other instanceof BooleanValue;}
    @Override
    public IValue deepCopy() {
        return new BooleanValue(value);
    }
}
