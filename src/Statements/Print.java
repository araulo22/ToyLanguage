package Statements;

import Domain.*;
import Expressions.InterfaceExpression;
import Values.InterfaceValue;

public class Print implements InterfaceStatement {
    private final InterfaceExpression expression;

    public Print(InterfaceExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceList<InterfaceValue> output = state.getOutput();
        InterfaceDictionary<String, InterfaceValue> table = state.getSymbTable();
        output.add(expression.eval(table, state.getHeap()));
        return state;
    }

    @Override
    public String toString() {
        return "print: (" + expression.toString() + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new Print(expression.deepCopy());
    }
}
