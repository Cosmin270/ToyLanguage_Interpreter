package model.value;

import model.type.*;
public record IntegerValue(int value) implements IValue {
    @Override
    public IType getType(){return new IntType();}
    public int getValue(){return value;}

    @Override
    public String toString(){return "" + value;}
}
