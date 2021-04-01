package Expressions;

import Domain.*;
import Types.BoolType;
import Values.BoolValue;
import Values.InterfaceValue;

public class Logic implements InterfaceExpression {
    InterfaceExpression one;
    InterfaceExpression two;
    String operator;

    public Logic(InterfaceExpression one, InterfaceExpression two, String operator) {
        this.one = one;
        this.two = two;
        this.operator = operator;
    }

    @Override
    public InterfaceValue eval(InterfaceDictionary<String, InterfaceValue> table, InterfaceHeap<InterfaceValue> heap) throws MyExc {
        InterfaceValue value1, value2;
        if(!operator.equals("AND") && !operator.equals("OR")) {
            throw new MyExc("value.Operator: '" + operator + "' is not a valid operator");
        }
        value1 = one.eval(table, heap);
        if(!value1.getType().equals(new BoolType())) {
            throw new MyExc("type.First: '" + value1.toString() + "' is not a boolean");
        }
        value2 = two.eval(table, heap);
        if(!value2.getType().equals(new BoolType())) {
            throw new MyExc("type.Second: '" + value2.toString() + "' is not a boolean");
        }
        BoolValue first = (BoolValue)value1;
        BoolValue second = (BoolValue)value2;
        boolean aux1, aux2;
        aux1 = first.getValue();
        aux2 = second.getValue();
        if(operator.equals("AND")) {
            return new BoolValue(aux1 && aux2);
        }
        else {
            return new BoolValue(aux1 || aux2);
        }
    }

    @Override
    public String toString() {
        if (operator.equals("AND")) {
            return this.one + " AND " + this.two;
        } else {
            return this.one + " OR " + this.two;
        }
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new Logic(one.deepCopy(), two.deepCopy(), operator);
    }
}
