package Statements;

import Domain.*;
import Expressions.InterfaceExpression;
import Types.RefType;
import Values.InterfaceValue;
import Values.RefValue;

public class NewHeap implements InterfaceStatement {
    String varName;
    InterfaceExpression exp;

    public NewHeap(String varName, InterfaceExpression exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceStack<InterfaceStatement> stack = state.getExecStack();
        InterfaceDictionary<String, InterfaceValue> table = state.getSymbTable();
        InterfaceHeap<InterfaceValue> heap = state.getHeap();
        if(table.exists(varName)) {
            if(table.get(varName).getType() instanceof RefType) {
                InterfaceValue value = exp.eval(table, heap);
                InterfaceValue tablevalue = table.get(varName);
                if(value.getType().equals(((RefType)(tablevalue.getType())).getInner())) {
                    int addr = heap.allocate(value);
                    table.update(varName, new RefValue(value.getType(), addr));
                }
                else {
                    throw new MyExc("heap.Type: Incorrect value type");
                }
            }
            else {
                throw new MyExc("heap.Type: Value's type is not a reference");
            }
        }
        else {
            throw new MyExc("heap.Declare: Value is not in the symbol table");
        }
        state.setSymbTable(table);
        state.setHeap(heap);
        state.setExecStack(stack);
        return state;
    }

    @Override
    public String toString() {
        return "newHeap(" + varName + ", " + exp + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new NewHeap(varName, exp.deepCopy());
    }
}
