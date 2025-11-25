package model.value;

import model.type.IType;
import model.type.StringType;

public record StringValue(String value) implements IValue {


    public String getValue(){return value;}

    @Override
    public IType getType() {return new StringType();}
    @Override
    public String toString(){return value;}
    @Override
    public boolean equals(Object other){return other instanceof StringValue;}
    @Override
    public IValue deepCopy() {
        return new StringValue(value);
    }
}
