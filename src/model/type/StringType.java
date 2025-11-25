package model.type;

import model.value.IValue;
import model.value.StringValue;

public class StringType implements IType {

    private final String value;

    public StringType(String value) {this.value = value;}
    public StringType() {this.value = "";}
    public String getValue() {return this.value;}

    @Override
    public IValue getDefaultValue() {return new StringValue("");}
    @Override
    public boolean equals(Object other) {
        return other instanceof StringType;
    }
    @Override
    public String toString() {return "string";}
    @Override
    public StringType deepCopy() {return new StringType();}
}
