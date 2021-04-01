package Controller;

import Repository.*;
import Domain.*;
import Statements.InterfaceStatement;
import Types.RefType;
import Values.InterfaceValue;
import Values.RefValue;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private InterfaceRepo repo;
    boolean debug;
    private ExecutorService executor;

    public Controller(InterfaceRepo repo, boolean debug) {
        this.repo = repo;
        this.debug = debug;
        executor = Executors.newFixedThreadPool(4);
    }

    public ProgramState step(ProgramState state) throws MyExc {
        InterfaceStack<InterfaceStatement> stack = state.getExecStack();
        if(stack.empty())
            throw new MyExc("step.Stack: Program stack is empty");
        InterfaceStatement statement = stack.pop();
        return statement.execute(state);
    }

    List<ProgramState> removeCompleted(List<ProgramState> programList) {
        return programList.stream()
                .filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    void oneStepForAllPrograms(List<ProgramState> programList) throws MyExc {
        programList.forEach(prg -> {
            try {
                repo.logProgramState(prg);
            } catch (MyExc | IOException e) {
                e.printStackTrace();
            }
        });
        List<Callable<ProgramState>> callableList = programList.stream()
                .map((ProgramState p)->(Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());
        try {
            List<ProgramState> newProgramList = executor.invokeAll(callableList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            programList.addAll(newProgramList);
        } catch(InterruptedException e) {
            throw new MyExc(e.getMessage());
        }
        programList.forEach(prg -> {
            try {
                repo.logProgramState(prg);
            } catch (MyExc | IOException e) {
                e.printStackTrace();
            }
        });
        repo.setPrograms(programList);
    }

    public void run() throws MyExc, IOException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompleted(repo.getPrograms());
        while (programList.size() > 0) {
            garbageCollector(programList);
            oneStepForAllPrograms(programList);
            programList = removeCompleted(repo.getPrograms());
        }
        executor.shutdownNow();
        repo.setPrograms(programList);
    }

    Map<Integer, InterfaceValue> unsafeGC(List<Integer> addr, Map<Integer, InterfaceValue> heap) {
        return heap.entrySet().stream().filter(
                elem -> addr.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    Map<Integer, InterfaceValue> safeGC(List<Integer> addr, Map<Integer, InterfaceValue> heap) {
        Map<Integer, InterfaceValue> map = heap.entrySet().stream()
                .filter(elem -> addr.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<Integer, InterfaceValue> map2 = new HashMap<>();
        map2.putAll(map);
        for(Map.Entry<Integer, InterfaceValue> entry:map2.entrySet()) {
            Integer key= entry.getKey();
            InterfaceValue value = entry.getValue();
            while (value instanceof RefValue) {
                RefValue ref = (RefValue) value;
                Integer address = ref.getAddr();
                InterfaceValue aux = heap.get(address);
                map.put(address, aux);
                value = aux;
            }
        }
        return map;
    }

    void garbageCollector(List<ProgramState> programList) {
        List<Integer> addr = Objects.requireNonNull(programList.stream()
        .map(p -> getAddrFromSymbTable(
                p.getSymbTable().getContent().values(),
                p.getHeap().getContent().values()))
        .map(Collection::stream)
        .reduce(Stream::concat).orElse(null).collect(Collectors.toList()));

    }

    List<Integer> getAddrFromSymbTable(Collection<InterfaceValue> tableValues, Collection<InterfaceValue> heap) {
        return Stream.concat(
                heap.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddr();
                })
                ,tableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddr();
                })
        )
                .collect(Collectors.toList());
        }

    public ProgramState getMainProgram() {
        return this.repo.getCurrentProgram();
    }
}
