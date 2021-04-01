package Expressions;

import Domain.InterfaceDictionary;
import Domain.InterfaceHeap;
import Values.InterfaceValue;
import Domain.MyExc;

public class Value implements InterfaceExpression {
    InterfaceValue value;

    public Value(InterfaceValue value) {
        this.value = value;
    }

    @Override
    public InterfaceValue eval(InterfaceDictionary<String, InterfaceValue> table, InterfaceHeap<InterfaceValue> heap) throws MyExc {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new Value(value.deepCopy());
    }
}
