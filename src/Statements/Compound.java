package Statements;

import Domain.InterfaceStack;
import Domain.MyExc;
import Domain.ProgramState;

public class Compound implements InterfaceStatement {
    private final InterfaceStatement first;
    private final InterfaceStatement second;

    public Compound(InterfaceStatement one, InterfaceStatement two) {
        this.first = one;
        this.second = two;
    }

    @Override
    public String toString() {
        return "(" + this.first.toString() + " | " + this.second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceStack<InterfaceStatement> stack = state.getExecStack();
        stack.push(second);
        stack.push(first);
        state.setExecStack(stack);
        return state;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new Compound(first.deepCopy(), second.deepCopy());
    }
}
