package Statements;

import Domain.MyExc;
import Domain.ProgramState;

public class NOP implements InterfaceStatement {
    @Override
    public ProgramState execute(ProgramState state) throws MyExc {
        return null;
    }


    @Override
    public InterfaceStatement deepCopy() {
        return new NOP();
    }
}
