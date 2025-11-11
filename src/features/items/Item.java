package features.items;

import utils.KeyAttribute;

public class Item {

    @KeyAttribute
    public String name;

    public int basePrice;
    public Category category;

    public Item(String name, int basePrice, Category category) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
    }
}
