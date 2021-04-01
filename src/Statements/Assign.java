package Statements;

import Domain.*;
import Expressions.InterfaceExpression;
import Types.InterfaceType;
import Values.InterfaceValue;

public class Assign implements InterfaceStatement {
    private final String name;
    private final InterfaceExpression value;

    public Assign(String name, InterfaceExpression value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceStack<InterfaceStatement> stack = state.getExecStack();
        InterfaceDictionary<String, InterfaceValue> table = state.getSymbTable();

        if(table.exists(name)) {
            InterfaceValue val = value.eval(table, state.getHeap());
            InterfaceType type = (table.get(name)).getType();
            if(val.getType().equals(type)) {
                table.update(name, val);
            }
            else {
                throw new MyExc("type.First: Variable type " + name + " does not match the type of the expression (" + val.getType().toString() + "/" + type.toString() + ")");
            }
        }
        else {
            throw new MyExc("type.First: " + name + " is not declared");
        }
        return state;
    }

    @Override
    public String toString() {
        return name + " = " + value.toString();
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new Assign(new String(name), value.deepCopy());
    }
}
