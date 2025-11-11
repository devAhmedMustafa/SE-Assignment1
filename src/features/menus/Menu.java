package features.menus;

import features.items.Item;
import utils.KeyAttribute;

import java.util.List;

public class Menu {

    @KeyAttribute
    public String title;

    public String description;
    public List<Item> items;

    public Menu(String title, String description, List<Item> items) {
        this.title = title;
        this.description = description;
        this.items = items;
    }
}
