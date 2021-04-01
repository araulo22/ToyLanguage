package Statements;

import Domain.*;
import Types.InterfaceType;
import Types.RefType;
import Values.*;

public class Declare implements InterfaceStatement {
    private final String name;
    private final InterfaceType type;

    public Declare(String name, InterfaceType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        InterfaceDictionary<String, InterfaceValue> table = state.getSymbTable();
        InterfaceValue value;
        if(table.exists(name)) {
            throw new MyExc("value.Name: '" + name + "' variable name already exists");
        }
        if(type.toString().equals("bool")) {
            value = new BoolValue(false);
        }
        else if(type.toString().equals("int")) {
            value = new IntValue(0);
        }
        else if(type.toString().equals("string")) {
            value = new StringValue("_");
        }
        else if(type instanceof RefType){
            value = new RefValue(((RefType) type).getInner(), 0);
        }
        else {
            throw new MyExc("value.Type: '" + type.toString() + "' does not exist");
        }
        table.add(name, value);
        return state;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new Declare(new String(name), type);
    }
}
