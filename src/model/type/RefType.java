package model.type;

import model.value.IValue;
import model.value.RefValue;

public class RefType implements IType {

    private final IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }
    public IType getInner(){
        return inner;
    }
    @Override
    public boolean equals(Object another){
        if (another instanceof RefType refType){
            return inner.equals(refType.getInner());
        }
        else{
            return false;
        }
    }
    @Override
    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }
    @Override
    public IValue getDefaultValue(){
        return new RefValue(0, inner);
    }
    @Override
    public IType deepCopy(){
        return new RefType(inner);
    }

}
