package model.type;


import model.value.*;

public class IntType implements Type {

    private final int value;

    public IntType(int value) {this.value = value;}

    public IntType() {this.value = 0;}

    public int getValue() {return value;}

    @Override
    public Value getDefaultValue() {return new IntegerValue(0);}

    @Override
    public Type deepCopy() {return new IntType();}

    @Override
    public String toString(){return "int";}

    @Override
    public boolean equals(Object other) {return other instanceof IntType;}
}
