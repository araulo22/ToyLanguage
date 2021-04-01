package Domain;

import java.util.Map;

public interface InterfaceHeap<V> {
    int allocate(V value);
    void update(int addr, V value);
    V get(int addr);
    void deallocate(int addr);
    boolean contains(int addr);
    Map<Integer, V> getContent();
    void setContent(Map<Integer, V> content);
    public void add(Integer id, V value) throws MyExc;
}
