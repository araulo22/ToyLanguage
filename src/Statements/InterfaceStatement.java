package Statements;

import Domain.InterfaceDictionary;
import Domain.MyExc;
import Domain.ProgramState;

public interface InterfaceStatement {
    ProgramState execute(ProgramState state) throws MyExc;
    InterfaceStatement deepCopy();
}
