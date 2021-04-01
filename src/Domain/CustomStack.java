package Domain;

import java.util.Stack;

public class CustomStack<Type> implements InterfaceStack<Type> {
    Stack<Type> stack;

    public CustomStack() {
        this.stack = new Stack<>();
    }

    @Override
    public void push(Type elem) {
        stack.push(elem);
    }

    @Override
    public Type pop() {
        return stack.pop();
    }

    @Override
    public boolean empty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
