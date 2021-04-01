package Domain;

import java.util.ArrayList;

public class CustomList<Type> implements InterfaceList<Type> {
    ArrayList<Type> list;

    public CustomList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(Type elem) {
        list.add(elem);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public void remove(Type item) {
        list.remove(item);
    }

    @Override
    public Type get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
}
