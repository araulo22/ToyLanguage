package Expressions;

import Domain.InterfaceDictionary;
import Domain.InterfaceHeap;
import Values.InterfaceValue;
import Domain.MyExc;

public interface InterfaceExpression {
    InterfaceValue eval(InterfaceDictionary<String, InterfaceValue> table, InterfaceHeap<InterfaceValue> heap) throws MyExc;
    InterfaceExpression deepCopy();
}
