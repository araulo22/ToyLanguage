package Expressions;

import Domain.*;
import Types.IntType;
import Values.BoolValue;
import Values.IntValue;
import Values.InterfaceValue;

public class Relational implements InterfaceExpression {
    InterfaceExpression one;
    InterfaceExpression two;
    String operator;

    public Relational(InterfaceExpression one, InterfaceExpression two, String operator) {
        this.one = one;
        this.two = two;
        this.operator = operator;
    }

    @Override
    public InterfaceValue eval(InterfaceDictionary<String, InterfaceValue> table, InterfaceHeap<InterfaceValue> heap) throws MyExc {
        InterfaceValue value1,value2;
        if(!operator.equals("<") && !operator.equals("<=") && !operator.equals(">") && !operator.equals(">=") && !operator.equals("==") && !operator.equals("!=")) {
            throw new MyExc("value.Operator: '" + operator + "' is not a valid operator");
        }
        value1 = one.eval(table, heap);
        if(!value1.getType().equals(new IntType())) {
            throw new MyExc("type.First: '" + value1.toString() + "' is not an integer");
        }
        value2 = two.eval(table,heap);
        if(!value2.getType().equals(new IntType())) {
            throw new MyExc("type.Second: '" + value2.toString() + "' is not an integer");
        }
        IntValue first = (IntValue)value1;
        IntValue second = (IntValue)value2;
        int aux1, aux2;
        aux1 = first.getValue();
        aux2 = second.getValue();
        switch (operator) {
            case "<":
                return new BoolValue(aux1 < aux2);
            case "<=":
                return new BoolValue(aux1 <= aux2);
            case ">":
                return new BoolValue(aux1 > aux2);
            case ">=":
                return new BoolValue(aux1 >= aux2);
            case "==":
                return new BoolValue(aux1 == aux2);
            case "!=":
                return new BoolValue(aux1 != aux2);
            default:
                throw new MyExc("value.Operator: '" + operator + "' is not a valid operator");
        }
    }

    @Override
    public String toString() {
        switch (operator) {
            case "<":
                return this.one + "<" + this.two;
            case "<=":
                return this.one + "<=" + this.two;
            case ">":
                return this.one + ">" + this.two;
            case ">=":
                return this.one + ">=" + this.two;
            case "==":
                return this.one + "==" + this.two;
            case "!=":
                return this.one + "!=" + this.two;
            default:
                return "value.Operator: '" + operator + "' is not a valid operator";
        }
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new Relational(one.deepCopy(), two.deepCopy(), operator);
    }
}