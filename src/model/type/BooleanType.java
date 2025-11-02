package model.type;

import model.value.BooleanValue;
import model.value.IValue;

public class BooleanType implements IType {

    private final boolean value;


    public BooleanType() {
        this.value = false;
    }
    public BooleanType(boolean value) {this.value = value;}

    public boolean getValue() {return value;}


    @Override
    public IValue getDefaultValue() {return new BooleanValue(false);}

    @Override
    public IType deepCopy() {return new BooleanType(value);}

    @Override
    public String toString(){return "boolean";}

    @Override
    public boolean equals(Object other) {
        return other instanceof BooleanType;
    }
}
