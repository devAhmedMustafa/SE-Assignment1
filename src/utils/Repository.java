package utils;

import java.util.Map;

public abstract class Repository <T, K> {
    private final Map<K, T> items;


    protected Repository() {
        this.items = new java.util.HashMap<>();
    }

    public void add(K key, T item) {

        if (items.containsKey(key)) {
            throw new IllegalArgumentException("Item with the given key already exists.");
        }

        items.put(key, item);
    }

    public T get(K key) {
        if (!items.containsKey(key)) {
            return null;
        }

        return items.get(key);
    }

    public void remove(K key) {

        if (!items.containsKey(key)) {
            throw new IllegalArgumentException("Item with the given key does not exist.");
        }

        items.remove(key);
    }

    public boolean contains(K key) {
        return items.containsKey(key);
    }

}
