package Statements;

import Domain.*;
import Values.InterfaceValue;

import java.util.Map;

public class Fork implements InterfaceStatement {
    private InterfaceStatement statement;

    public Fork(InterfaceStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceDictionary<String, InterfaceValue> newSymTable = new CustomDictionary<>();
        for (Map.Entry<String, InterfaceValue> entry: state.getSymbTable().getContent().entrySet()) {
            newSymTable.add(entry.getKey(), entry.getValue());
        }
        InterfaceStack<InterfaceStatement> stack = new CustomStack<>();
        stack.push(statement);
        return new ProgramState(stack, newSymTable, state.getOutput(), state.getFileTable(), state.getHeap());
    }

    @Override
    public String toString() {
        return "fork(" + statement + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new Fork(statement.deepCopy());
    }
}
