package Expressions;

import Domain.InterfaceDictionary;
import Domain.InterfaceHeap;
import Domain.MyExc;
import Values.InterfaceValue;
import Values.RefValue;

public class ReadHeap implements InterfaceExpression {
    private InterfaceExpression exp;


    public ReadHeap(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public InterfaceValue eval(InterfaceDictionary<String, InterfaceValue> table, InterfaceHeap<InterfaceValue> heap) throws MyExc {
        InterfaceValue value = exp.eval(table, heap);
        if(value instanceof RefValue) {
            RefValue refValue = (RefValue) value;
            if(heap.contains(refValue.getAddr())) {
                return heap.get(refValue.getAddr());
            }
            else {
                throw new MyExc("heap.Declare: The address does not exist in the heap");
            }
        }
        else {
            throw new MyExc("heap.Type: The expression was not evaluated to a RefValue");
        }
    }

    @Override
    public String toString() {
        return "readHeap(" + exp + ")";
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new ReadHeap(exp.deepCopy());
    }
}
