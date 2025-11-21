package features.cart;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private static Cart instance = null;
    private Cart() {}
    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    private final Map<OrderedItem, Integer> items = new HashMap<>();

    public Map<OrderedItem, Integer> getItems() {
        return items;
    }

    public void addItem(OrderedItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(OrderedItem item) {
        items.remove(item);
    }

    public void updateItemQuantity(OrderedItem item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, quantity);
        }
    }

    public int calculateTotal() {
        int total = 0;
        for (Map.Entry<OrderedItem, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void clearCart() {
        items.clear();
    }

}
