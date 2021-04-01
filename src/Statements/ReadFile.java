package Statements;

import Domain.*;
import Expressions.InterfaceExpression;
import Types.IntType;
import Types.StringType;
import Values.IntValue;
import Values.InterfaceValue;
import Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements InterfaceStatement {
    private InterfaceExpression exp;
    private String name;
    private String file;

    public ReadFile(InterfaceExpression exp, String name) {
        this.exp = exp;
        this.name = name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceDictionary<String, InterfaceValue> symbTable = state.getSymbTable();
        InterfaceDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        if(symbTable.exists(name)) {
            if(symbTable.get(name).getType().equals(new IntType())) {
                InterfaceValue val = exp.eval(symbTable, state.getHeap());
                if(val.getType().equals(new StringType())) {
                    StringValue strVal = (StringValue)val;
                    if(fileTable.exists(strVal)) {
                        BufferedReader bufferedReader = fileTable.get(strVal);
                        try {
                            String line = bufferedReader.readLine();
                            InterfaceValue intVal;
                            IntType type = new IntType();
                            if(line == null) {
                                intVal = new IntValue(0);
                            } else {
                                intVal = new IntValue(Integer.parseInt(line));
                            }
                            symbTable.update(name, intVal);
                        } catch (IOException exc) {
                            throw new MyExc(exc.getMessage());
                        }
                    }
                    else {
                        throw new MyExc("Declare error: " + strVal.getValue() + " was not found in the File Table!");
                    }
                }
                else {
                    throw new MyExc("Type error: The value couldn't be evaluated to a string value!");
                }
            }
            else {
                throw new MyExc("Type error: " + name + " is not of int type!");
            }
        }
        else {
            throw new MyExc("Declare error: " + name + " was not found in the Symbol Table!");
        }
        return null;
    }

    @Override
    public String toString() {
        return "Read_from(" + exp + ")to(" + name + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new ReadFile(exp.deepCopy(), new String(name));
    }
}
