package Domain;

public interface InterfaceList<Type> {
    //adding an element method
    void add(Type elem);
    //clearing the list method
    void clear();
    void remove(Type item);
    Type get(int index);
    int size();
}
