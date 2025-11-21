package features.cart;

public class DecoratedItem implements OrderedItem {
    protected PlainItem item;

    protected DecoratedItem(PlainItem item) {
        this.item = item;
    }

    public int getPrice() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "";
    }
}
