package features.cart;

import features.items.Item;

public class PlainItem implements OrderedItem {
    private final Item item;

    public PlainItem(Item item) {
        this.item = item;
    }

    @Override
    public int getPrice() {
        return item.basePrice;
    }

    @Override
    public String getDetails() {
        return item.name;
    }
}
