package part2;

import java.util.Iterator;

public abstract class ST<Key, Value> {

    abstract void put(Key key, Value value);
    abstract Value get(Key key);
    boolean contains(Key key) {
        return get(key) != null;
    }
    void delete(Key key) {
        put(key, null);
    }
    boolean isEmpty() {
        return size() ==0;
    }
    abstract int size();
    abstract Iterator<Key> keys();
}