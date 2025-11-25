package model.value;

import model.type.*;
public record IntegerValue(int value) implements IValue {
    @Override
    public IType getType(){return new IntType();}
    public int getValue(){return value;}

    @Override
    public String toString(){return "" + value;}

    @Override
    public boolean equals(Object other){return other instanceof IntegerValue;}
    @Override
    public IValue deepCopy() {
        return new IntegerValue(value);
    }
}
