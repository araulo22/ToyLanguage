package Domain;

public interface InterfaceStack<Type> {
    //popping the top element method
    Type pop();
    //pushing the top element method
    void push(Type elem);
    //checking if stack is empty method
    boolean empty();
}
