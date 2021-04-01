package Domain;

import java.util.HashMap;

public interface InterfaceDictionary<Key, Type> {
    //adding an element with a key method
    void add(Key key, Type elem);
    //get the element with a key method
    Type get(Key key);
    //check if there's a key method
    boolean exists(Key key);
    //change element at a key method
    void update(Key key, Type elem);
    //remove element at a key method
    void remove(Key key);
    //clear dictionary method
    void clear();

    HashMap<Key, Type> getContent();
    void setContent(HashMap<Key, Type> content);

    InterfaceDictionary<Key, Type> deepCopy();
}
