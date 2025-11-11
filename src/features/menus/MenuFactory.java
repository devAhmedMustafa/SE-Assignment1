package features.menus;

import features.items.Item;
import features.items.ItemRepository;
import utils.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuFactory {
    public Menu createMenu(String title, String description, List<String> itemNames) {

        List<Item> items = new ArrayList<>();
        ItemRepository itemRepository = ItemRepository.getInstance();
        for (String itemName : itemNames) {
            Item item = itemRepository.get(itemName);
            if (item == null) {
                Logger.log("Item not found: " + itemName, Color.YELLOW);
                continue;
            }

            items.add(item);
        }

        return new Menu(title, description, items);
    }
}
