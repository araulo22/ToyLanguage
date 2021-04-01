package Statements;

import Domain.*;
import Expressions.InterfaceExpression;
import Types.StringType;
import Values.InterfaceValue;
import Values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class OpenFile implements InterfaceStatement {
    private InterfaceExpression exp;

    public OpenFile(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceDictionary<String, InterfaceValue> symbTable = state.getSymbTable();
        InterfaceValue val = exp.eval(symbTable, state.getHeap());
        if(val.getType().equals(new StringType())) {
            InterfaceDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
            StringValue strVal = (StringValue) val;
            if(!fileTable.exists(strVal)) {
                try {
                    Reader reader = new FileReader(strVal.getValue());
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    fileTable.add(strVal, bufferedReader);
                } catch (FileNotFoundException exc) {
                    throw new MyExc(exc.getMessage());
                }
            }
            else {
                throw new MyExc("File error: The file is already used!");
            }
        } else {
            throw new MyExc("Type error: The value couldn't be evaluated to a string value!");
        }
        return null;
    }

    @Override
    public String toString() {
        return "Open(" + exp + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new OpenFile(exp.deepCopy());
    }
}
