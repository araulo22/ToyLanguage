package Domain;

import java.util.HashMap;

public class CustomDictionary<Key, Type> implements InterfaceDictionary<Key, Type> {
    HashMap<Key, Type> dictionary;

    public CustomDictionary() {
        this.dictionary = new HashMap<Key, Type>();
    }

    @Override
    public void add(Key key, Type elem) {
        dictionary.put(key, elem);
    }

    @Override
    public Type get(Key key) {
        return dictionary.get(key);
    }

    @Override
    public boolean exists(Key key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void update(Key key, Type elem) {
        dictionary.replace(key, elem);
    }

    @Override
    public void remove(Key key) {
        dictionary.remove(key);
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    @Override
    public HashMap<Key, Type> getContent() {
        return dictionary;
    }

    @Override
    public void setContent(HashMap<Key, Type> content) {
        dictionary = content;
    }

    @Override
    public InterfaceDictionary<Key, Type> deepCopy() {
        HashMap<Key, Type> newMap = new HashMap<Key, Type>(dictionary);
        CustomDictionary<Key, Type> newDictionary = new CustomDictionary<Key, Type>();
        newDictionary.setContent(newMap);
        return newDictionary;
    }
}
