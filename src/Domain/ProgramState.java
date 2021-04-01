package Domain;

import Statements.InterfaceStatement;
import Values.InterfaceValue;
import Values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    InterfaceStack<InterfaceStatement> execStack;
    InterfaceHeap<InterfaceValue> heap;
    InterfaceDictionary<String, InterfaceValue> symbTable;
    InterfaceDictionary<StringValue, BufferedReader> fileTable;
    InterfaceList<InterfaceValue> output;
    public InterfaceStatement mainProgram;
    private int stateId;
    private static int freeId = 0;

    public ProgramState(InterfaceStack<InterfaceStatement> stack,
                        InterfaceDictionary<String, InterfaceValue> symbolTable,
                        InterfaceList<InterfaceValue> output,
                        InterfaceDictionary<StringValue, BufferedReader> flTable,
                        InterfaceHeap<InterfaceValue> hip,
                        InterfaceStatement program) {
        this.execStack = stack;
        this.symbTable = symbolTable;
        this.fileTable = flTable;
        this.output = output;
        this.heap = hip;
        mainProgram = program;
        if(mainProgram != null) {
            stack.push(program);
        }
    }

    public ProgramState(InterfaceStack<InterfaceStatement> stack,
                        InterfaceDictionary<String, InterfaceValue> symbolTable,
                        InterfaceList<InterfaceValue> output,
                        InterfaceDictionary<StringValue, BufferedReader> flTable,
                        InterfaceHeap<InterfaceValue> hip) {
        this.execStack = stack;
        this.symbTable = symbolTable;
        this.fileTable = flTable;
        this.output = output;
        this.heap = hip;
        stateId = getNewProgramStateID();
    }

    public ProgramState(InterfaceStack<InterfaceStatement> stack,
                        CustomDictionary<String, InterfaceValue> stringValueDictionary,
                        CustomList<InterfaceValue> valueList)
    {
        execStack = stack;
        symbTable = stringValueDictionary;
        output = valueList;
        heap = new CustomHeap<InterfaceValue>();
    }

    public ProgramState oneStep() throws MyExc {
        if(execStack.empty()) {
            throw new MyExc("Program stack is empty");
            //return null;
        }
        InterfaceStatement currentStatement = execStack.pop();
        return currentStatement.execute(this);
    }

    public static synchronized int getNewProgramStateID() {
        ++freeId;
        return freeId;
    }

    public boolean isNotCompleted() {
        return !execStack.empty();
    }

    public int getStateId() {return stateId;}
    public InterfaceStatement getMainProgram() {return mainProgram;}
    public InterfaceStack<InterfaceStatement> getExecStack() {
        return execStack;
    }
    public InterfaceHeap<InterfaceValue> getHeap() {return heap;}
    public InterfaceDictionary<String, InterfaceValue> getSymbTable() {
        return symbTable;
    }
    public InterfaceDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }
    public InterfaceList<InterfaceValue> getOutput() {
        return output;
    }
    public void setExecStack(InterfaceStack<InterfaceStatement> stack) {
        execStack = stack;
    }
    public void setHeap(InterfaceHeap<InterfaceValue> hip) {
        heap = hip;
    }
    public void setSymbTable(InterfaceDictionary<String, InterfaceValue> table) {
        symbTable = table;
    }
    public void setOutput(InterfaceList<InterfaceValue> outpt) {
        output = outpt;
    }

    public static ProgramState emptyProgramState(InterfaceStatement program) {
        return new ProgramState(new CustomStack<InterfaceStatement>(),
                new CustomDictionary<String, InterfaceValue>(),
                new CustomList<InterfaceValue>(),
                new CustomDictionary<StringValue, BufferedReader>(),
                new CustomHeap<InterfaceValue>(),
                program);
    }

    @Override
    public String toString() {
        return  "Program State ID: " + stateId + "\n" +
                "Execution Stack: " + execStack + "\n" +
                "Symbol Table: " + symbTable + "\n" +
                "Heap: " + heap + "\n" +
                "File Table: " + fileTable + "\n" +
                "Output: " + output + "\n";
    }


}
