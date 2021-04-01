package Statements;

import Domain.InterfaceDictionary;
import Domain.InterfaceStack;
import Domain.MyExc;
import Domain.ProgramState;
import Expressions.InterfaceExpression;
import Types.BoolType;
import Values.BoolValue;
import Values.InterfaceValue;

public class While implements InterfaceStatement {
    private InterfaceExpression exp;
    private InterfaceStatement statement;

    public While(InterfaceExpression exp, InterfaceStatement statement) {
        this.exp = exp;
        this.statement = statement;
    }

    public InterfaceStatement deepCopy() {
        return new While(exp.deepCopy(), statement.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceStack<InterfaceStatement> stack = state.getExecStack();
        InterfaceDictionary<String, InterfaceValue> table = state.getSymbTable();
        InterfaceValue value = exp.eval(table, state.getHeap());
        if(value.getType().equals(new BoolType())) {
            BoolValue boolValue = (BoolValue) value;
            if(boolValue.getValue()) {
                stack.push(this.deepCopy());
                stack.push(statement);
            }
        }
        else {
            throw new MyExc("type.WhileCondition: not boolean");
        }
        return null;
    }

    @Override
    public String toString() {
        return "while (" + exp + ") {" + statement + "}";
    }
}
