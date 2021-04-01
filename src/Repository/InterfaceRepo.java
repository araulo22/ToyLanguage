package Repository;

import Domain.MyExc;
import Domain.ProgramState;
import Statements.InterfaceStatement;

import java.io.IOException;
import java.util.List;

public interface InterfaceRepo {
    void setPrograms(List<ProgramState> list);

    InterfaceStatement getOriginalProgram();

    void addProgram(ProgramState state);

    ProgramState getProgramAndID(int id);

    void logProgramState(ProgramState program) throws MyExc, IOException;

    List<ProgramState> getPrograms();

    public ProgramState getCurrentProgram();
}
