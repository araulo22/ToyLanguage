package Statements;

import Domain.*;
import Expressions.InterfaceExpression;
import Types.StringType;
import Values.InterfaceValue;
import Values.StringValue;

import java.io.*;

public class CloseFile implements InterfaceStatement {
    private InterfaceExpression exp;

    public CloseFile(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceDictionary<String, InterfaceValue> symbTable = state.getSymbTable();
        InterfaceValue val = exp.eval(symbTable, state.getHeap());
        if(val.getType().equals(new StringType())) {
            InterfaceDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
            StringValue strVal = (StringValue) val;
            if(fileTable.exists(strVal)) {
                BufferedReader bufferedReader = fileTable.get(strVal);
                try {
                    bufferedReader.close();
                } catch (IOException exc) {
                    throw new MyExc(exc.getMessage());
                }
                fileTable.remove(strVal);
            }
            else {
                throw new MyExc("Declare error: " + strVal.getValue() + " was not found in the File Table!");
            }
        } else {
            throw new MyExc("Type error: The value couldn't be evaluated to a string value!");
        }
        return null;
    }

    @Override
    public String toString() {
        return "Close(" + exp + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new CloseFile(exp.deepCopy());
    }
}
