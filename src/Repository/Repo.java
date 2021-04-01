package Repository;

import Domain.*;
import Statements.InterfaceStatement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Repo implements InterfaceRepo {
    private List<ProgramState> programs;
    private InterfaceStatement originalProgram;
    private String fileName;

    public Repo(ProgramState program, String fileName) throws IOException, MyExc {
        this.originalProgram = program.getMainProgram();
        this.fileName = fileName;
        File customFile = new File(fileName);
        customFile.createNewFile();
        try(FileWriter fileWriter = new FileWriter(customFile)) {
            fileWriter.write("");
        } catch (IOException exc) {
            throw new MyExc(exc.getMessage());
        }
        programs = new LinkedList<>();
        programs.add(program);
    }

    @Override
    public List<ProgramState> getPrograms() {
        return programs;
    }

    @Override
    public ProgramState getCurrentProgram() {
        ProgramState state = programs.get(0);
        programs.remove(0);
        return state;
    }

    @Override
    public void setPrograms(List<ProgramState> list) {
        programs = list;
    }

    @Override
    public InterfaceStatement getOriginalProgram() {
        return originalProgram;
    }

    @Override
    public void addProgram(ProgramState state) {
        programs.add(state);
    }

    @Override
    public ProgramState getProgramAndID(int id) {
        for (ProgramState p: programs)
            if(p.getStateId() == id)
                return p;
        return null;
    }

    @Override
    public void logProgramState(ProgramState program) throws MyExc, IOException {
        File customFile = new File(fileName);
        customFile.createNewFile();
        try (FileWriter fileWriter = new FileWriter(customFile, true)) {
            fileWriter.write(program + "\n");
            fileWriter.close();
        } catch (IOException exc) {
            throw new MyExc(exc.getMessage());
        }
    }
}
