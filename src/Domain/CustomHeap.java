package Domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomHeap<V> implements InterfaceHeap<V> {
    private Map<Integer, V> map;
    private AtomicInteger freeLoc;

    public CustomHeap() {
        this.map = new ConcurrentHashMap<Integer, V>();
        this.freeLoc = new AtomicInteger(0);
    }

    @Override
    public int allocate(V value) {
        int newLoc = freeLoc.incrementAndGet();
        map.put(newLoc, value);
        return newLoc;
    }

    @Override
    public void update(int address, V value) {
        map.put(address, value);
    }

    @Override
    public V get(int address) {
        return map.get(address);
    }

    @Override
    public void deallocate(int address) {
        map.remove(address);
    }

    @Override
    public boolean contains(int address) {
        return map.containsKey(address);
    }

    @Override
    public Map<Integer, V> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Integer, V> content) {
        map = content;
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public void add(Integer id, V value) throws MyExc {
        if(map.containsKey(id)) {
            throw new MyExc("Heap: element already exists");
        }
        map.put(id, value);
    }
}
