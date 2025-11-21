package utils.storage;

import utils.debug.Logger;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public abstract class Repository <T> {
    private final Map<Object, T> items = new java.util.HashMap<>();
    private Field keyAttribute;


    protected Repository() {

        Class<?> type = (Class<?>) ((java.lang.reflect.ParameterizedType)
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(KeyAttribute.class)) {
                keyAttribute = field;
                keyAttribute.setAccessible(true);
                break;
            }
        }

        if (keyAttribute == null) {
            Logger.log("No field annotated with @KeyAttribute found in the repository class.", Color.RED);
            throw new IllegalStateException("No field annotated with @KeyAttribute found in the repository class.");
        }
    }

    private Object getKeyValue(T item) {
        try {
            return keyAttribute.get(item);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access key field.", e);
        }
    }

    public void add(T item) {

        Object key = getKeyValue(item);

        if (items.containsKey(key)) {
            throw new IllegalArgumentException("Item with the given key already exists.");
        }

        items.put(key, item);
    }

    public T get(Object key) {
        if (!items.containsKey(key)) {
            return null;
        }

        return items.get(key);
    }

    public void remove(Object key) {

        if (!items.containsKey(key)) {
            throw new IllegalArgumentException("Item with the given key does not exist.");
        }

        items.remove(key);
    }

    public List<T> getAll() {
        return List.copyOf(items.values());
    }

    public int size() {
        return items.size();
    }

    public void update(Object key, T item) {
        if (!items.containsKey(key)) {
            throw new IllegalArgumentException("Item with the given key does not exist.");
        }

        items.put(key, item);
    }

    public void clear() {
        items.clear();
    }

    public boolean contains(Object key) {
        return items.containsKey(key);
    }

}
