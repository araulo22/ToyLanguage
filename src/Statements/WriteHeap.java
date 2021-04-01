package Statements;

import Domain.InterfaceDictionary;
import Domain.InterfaceHeap;
import Domain.MyExc;
import Domain.ProgramState;
import Expressions.InterfaceExpression;
import Types.RefType;
import Values.InterfaceValue;
import Values.RefValue;

public class WriteHeap implements InterfaceStatement {
    private String varName;
    private InterfaceExpression exp;

    public WriteHeap(String varName, InterfaceExpression exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceDictionary<String, InterfaceValue> table = state.getSymbTable();
        InterfaceHeap<InterfaceValue> heap = state.getHeap();
        if(table.exists(varName)) {
            if(table.get(varName).getType() instanceof RefType) {
                RefValue refValue = (RefValue) table.get(varName);
                if(heap.contains(refValue.getAddr())) {
                    InterfaceValue value = exp.eval(table, heap);
                    if(table.get(varName).getType().equals(new RefType(value.getType()))) {
                        int addr = refValue.getAddr();
                        heap.update(addr, value);
                    }
                    else {
                        throw new MyExc("heap.Type: Pointed variable type different than the evaluated expression");
                    }
                }
                else {
                    throw new MyExc("heap.Declare: Address is not in the heap");
                }
            }
            else {
                throw new MyExc("heap.Type: " + varName + " is not a RefType");
            }
        }
        else {
            throw new MyExc("heap.Declare: Variable is not declared");
        }
        return null;
    }

    @Override
    public String toString() {
        return "writeHeap(" + varName + ", " + exp + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new WriteHeap(new String(varName), exp.deepCopy());
    }
}
