package Expressions;

import Domain.InterfaceDictionary;
import Domain.InterfaceHeap;
import Values.InterfaceValue;
import Domain.MyExc;

public class Variable implements InterfaceExpression {
    String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public InterfaceValue eval(InterfaceDictionary<String, InterfaceValue> table, InterfaceHeap<InterfaceValue> heap) throws MyExc {
        return table.get(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new Variable(new String(name));
    }
}
