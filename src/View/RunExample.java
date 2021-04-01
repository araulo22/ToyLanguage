package View;

import Controller.Controller;
import Domain.*;
import Expressions.*;
import Repository.Repo;
import Statements.*;
import Types.BoolType;
import Types.IntType;
import Types.RefType;
import Types.StringType;
import Values.BoolValue;
import Values.IntValue;
import Values.InterfaceValue;
import Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class RunExample extends Command {
    private int number;
    public InterfaceStatement examples(int number) {
        //int x = 10;
        //print(x);
        if(number == 1) return
                new Compound(
                        new Declare("x", new IntType()),
                        new Compound(
                                new Assign("x", new Value(new IntValue(10))),
                                new Print(new Variable("x"))));

            //int a = 5;
            //int b = 10;
            //int c = a * b;
            //print(c);
        else if(number == 2) return
                new Compound(
                        new Declare("a", new IntType()),
                        new Compound(
                                new Declare("b", new IntType()),
                                new Compound(
                                        new Assign("a", new Value(new IntValue(5))),
                                        new Compound(
                                                new Assign("b", new Value(new IntValue(10))),
                                                new Compound(
                                                        new Declare("c", new IntType()),
                                                        new Compound(
                                                                new Assign("c", new Arithmetic(new Variable("a"), new Variable("b"), "*")),
                                                                new Print(new Variable("c"))))))));

            //bool a = true;
            //bool b = false;
            //int c;
            //if(a OR b):
            //then c = 1;
            //else c = 2;
            //print(c);
        else if(number == 3) return
                new Compound(
                        new Declare("a", new BoolType()),
                        new Compound(
                                new Declare("b", new BoolType()),
                                new Compound(
                                        new Assign("a", new Value(new BoolValue(true))),
                                        new Compound(
                                                new Assign("b", new Value(new BoolValue(false))),
                                                new Compound(
                                                        new Declare("c", new IntType()),
                                                        new Compound(
                                                                new If(new Logic(new Variable("a"), new Variable("b"), "OR"),
                                                                        new Assign("c", new Value(new IntValue(1))),
                                                                        new Assign("c", new Value(new IntValue(2)))
                                                                ),
                                                                new Print(new Variable("c"))))))));
        else if(number == 4) return
                new Compound(
                        new Declare("fileName", new IntType()),
                        new Compound(
                                new Assign("fileName", new Value(new StringValue("test.txt"))),
                                new Compound(
                                        new OpenFile(new Variable("fileName")),
                                        new Compound(
                                                new Declare("a", new IntType()),
                                                new Compound(
                                                        new ReadFile(new Variable("fileName"), "a"),
                                                        new Compound(
                                                                new Print(new Variable("a")),
                                                                new Compound(
                                                                        new ReadFile(new Variable("fileName"), "a"),
                                                                        new Compound(
                                                                                new Print(new Variable("a")),
                                                                                new CloseFile(new Variable("fileName"))
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                );
        else if(number == 5) return
            new Compound(
                    new Declare("v", new RefType(new IntType())),
                    new Compound(
                            new NewHeap("v", new Value(new IntValue(20))),
                            new Compound(
                                    new Declare("a", new RefType(new RefType(new IntType()))),
                                    new Compound(
                                            new NewHeap("a", new Variable("v")),
                                            new Compound(
                                                    new NewHeap("v", new Value(new IntValue(30))),
                                                    new Print(new ReadHeap(new Variable("a")))
                                            )
                                    )
                            )
                    )
            );
        else if(number == 6) return
            new Compound(
                    new Declare("v", new IntType()),
                    new Compound(
                            new Assign("v", new Value(new IntValue(4))),
                            new Compound(
                                    new While(
                                            new Relational(new Variable("v"), new Value(new IntValue(0)), ">"),
                                            new Compound(
                                                    new Print(new Variable("v")),
                                                    new Assign("v", new Arithmetic(new Variable("v"), new Value(new IntValue(1)), "-"))
                            )
                    ),
                                    new Print(new Variable("v"))
            )));
        else if(number == 7) return
            new Compound(
                    new Declare("v", new IntType()),
                    new Compound(
                            new Declare("a", new RefType(new IntType())),
                            new Compound(
                                    new Assign("v", new Value(new IntValue(10))),
                                    new Compound(
                                            new NewHeap("a", new Value(new IntValue(22))),
                                            new Compound(
                                                    new Fork(
                                                            new Compound(
                                                                    new WriteHeap("a", new Value(new IntValue(30))),
                                                                    new Compound(
                                                                            new Assign("v", new Value(new IntValue(32))),
                                                                            new Compound(
                                                                                    new Print(new Variable("v")),
                                                                                    new Print(new ReadHeap(new Variable("a")))
                                                                            )
                                                                    )
                                                            )
                                                    ),
                                                    new Compound(
                                                            new Print(new Variable("v")),
                                                            new Print(new ReadHeap(new Variable("a")))
                                                    )
                                            )
                                    )
                            )
                    )
            );
        else if(number == 8)
            return new Compound(
                    new Declare("a", new IntType()),
                    new Compound(
                            new Fork(new Assign("a", new Value(new IntValue(5)))),
                            new Compound(
                                    new Declare("b", new IntType()),
                                    new Compound(
                                            new Fork(new Assign("b", new Value(new IntValue(10)))),
                                            new Compound(
                                                    new Print(new Variable("a")),
                                                    new Print(new Variable("b"))
                                            )
                                    )
                            )
                    )
            );
        else return null;
    }

    public RunExample(String command, String description, int number) {
        super(command, description);
        this.number = number;
        this.setDetails(examples(number).toString());
    }

    @Override
    public void run() {
        Repo repo = null;
        InterfaceStack<InterfaceStatement> stack = new CustomStack<>();
        stack.push(this.examples(number));
        try {
            ProgramState ex = new ProgramState(stack, new CustomDictionary<String, InterfaceValue>(), new CustomList<InterfaceValue>(), new CustomDictionary<StringValue, BufferedReader>(), new CustomHeap<InterfaceValue>());
            repo = new Repo(ex, "example" + Integer.toString(number) + "_log.txt");
        } catch (IOException | MyExc exc) {
            System.out.println(exc.toString());
        }
        Controller ctrl = new Controller(repo, false);
        try {
            ctrl.run();
        }
        catch (MyExc | IOException exc) {
            System.out.println(exc.toString());
        }
    }
}
