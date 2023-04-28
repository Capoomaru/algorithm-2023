package part2;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> implements SymbolTable<Key, Value> {
    private Node<Key, Value> start;
    private int size = 0;


    @Override
    public void put(Key key, Value value) {
        if(start == null) {
            start = new Node<>(key, value, null);
            size++;
        }

        Node<Key, Value> node = new Node<>(key, value, start);
        start = node;
        size++;
    }

    @Override
    public Value get(Key key) {
        if(key == null) return null;
        Node<Key, Value> temp = start;
        while(temp != null) {
            if(temp.getKey().equals(key))
                return temp.getValue();
            temp = temp.getNext();
        }

        return null;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public void delete(Key key) {
        put(key, null);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<Key> keys() {
        return null;
    }
}
