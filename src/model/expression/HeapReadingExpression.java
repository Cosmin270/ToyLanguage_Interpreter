package model.expression;

import model.exception.ExpressionsException;
import model.map.MyIMap;
import model.state.IHeapTable;
import model.state.ISymbolTable;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;

public class HeapReadingExpression implements IExpression {
    IExpression expression;
    public HeapReadingExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeapTable heapTable) {
        IValue value = expression.evaluate(symbolTable, heapTable);
        if(!(value instanceof RefValue refValue)) {
            throw new ExpressionsException("Invalid expression");
        }
        var address = refValue.getAddress();
        if(!heapTable.isDefined(address)){
            throw new ExpressionsException("Not defined address");
        }
        return heapTable.getValue(address);
    }

    @Override
    public String toString() {
        return "Heap Reading: " + expression.toString();
    }

    @Override
    public IType typeCheck(MyIMap<String, IType> typeEnv) throws ExpressionsException{
        IType type = this.expression.typeCheck(typeEnv);

        if(!(type instanceof RefType))
            throw new ExpressionsException("Argument is not of type RefType");

        return ((RefType)type).getInner();
    }
}
