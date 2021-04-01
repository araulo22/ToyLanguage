package Statements;

import Domain.*;
import Expressions.InterfaceExpression;
import Types.BoolType;
import Values.BoolValue;
import Values.InterfaceValue;

public class If implements InterfaceStatement {
    private final InterfaceExpression condition;
    private final InterfaceStatement then;
    private final InterfaceStatement otherwise;

    public If(InterfaceExpression condition, InterfaceStatement then, InterfaceStatement otherwise) {
        this.condition = condition;
        this.then = then;
        this.otherwise = otherwise;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceStack<InterfaceStatement> stack = state.getExecStack();
        InterfaceDictionary<String, InterfaceValue> table = state.getSymbTable();
        InterfaceValue value = condition.eval(table, state.getHeap());
        BoolValue result = new BoolValue(true);
        if(value.getType().equals(new BoolType())) {
            if(value.equals(result)) {
                stack.push(then);
            }
            else {
                stack.push(otherwise);
            }
        }
        else throw new MyExc("type.Expression: '" + condition.toString() + "' is not a bool value");
        return state;
    }

    @Override
    public String toString() {
        return "if (" + condition.toString() + ") then: (" + then.toString() + "), else: (" + otherwise.toString() + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new If(condition.deepCopy(), then.deepCopy(), otherwise.deepCopy());
    }
}
