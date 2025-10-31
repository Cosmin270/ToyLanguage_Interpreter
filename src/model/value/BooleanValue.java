package model.value;

import model.type.*;

public record BooleanValue(boolean value) implements Value {
    @Override
    public Type getType(){return new BooleanType();}
    public boolean getValue(){return value;}

    @Override
    public String toString() {return "" + value;}
}
