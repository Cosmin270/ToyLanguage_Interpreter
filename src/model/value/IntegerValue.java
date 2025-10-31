package model.value;

import model.type.*;
public record IntegerValue(int value) implements Value {
    @Override
    public Type getType(){return new IntType();}
    public int getValue(){return value;}

    @Override
    public String toString(){return "" + value;}
}
