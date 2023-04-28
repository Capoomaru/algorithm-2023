package part2;

import java.util.Iterator;

public interface SymbolTable<Key, Value> {

    void put(Key key, Value value);
    Value get(Key key);
    boolean contains(Key key);
    void delete(Key key);
    boolean isEmpty();
    int size();
    Iterator<Key> keys();
}

