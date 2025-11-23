package features.items;

import utils.debug.Logger;
import utils.parsers.PriceParser;

import java.awt.*;

public class ItemUI {
    public void addItem(String itemName, float basePrice, String category) {

        try {
            Logger.log("Adding " + itemName + " to the item list", Color.BLUE);

            Item addedItem = ItemFactory.createItem(itemName, PriceParser.floatToCents(basePrice), category);
            ItemRepository.getInstance().add(addedItem);

            Logger.log("Item added to the item list", Color.GREEN);
        }
        catch (Exception e) {
            Logger.log("Error adding item: " + e.getMessage(), Color.RED);
        }
    }

    public String showAllItems() {
        try {
            StringBuilder itemListStr = new StringBuilder();
            for (Item item : ItemRepository.getInstance().getAll()) {
                itemListStr.append(item.toString()).append("\n");
            }
            return itemListStr.toString();
        }
        catch (Exception e) {
            Logger.log("Error retrieving items: " + e.getMessage(), Color.RED);
            return "Error retrieving items.";
        }
    }

    public void addCategory(String categoryName, String parentCategoryName) {
        try {
            Logger.log("Adding new category: " + categoryName, Color.BLUE);

            Category addedCategory = CategoryFactory.create(categoryName, parentCategoryName);
            CategoryRepository.getInstance().add(addedCategory);

            Logger.log("Category added to the category list", Color.GREEN);
        }
        catch (Exception e) {
            Logger.log("Error adding category: " + e.getMessage(), Color.RED);
        }
    }

    public void addCategory(String categoryName) {
        try {
            Logger.log("Adding new category: " + categoryName, Color.BLUE);

            Category addedCategory = CategoryFactory.create(categoryName);
            CategoryRepository.getInstance().add(addedCategory);

            Logger.log("Category added to the category list", Color.GREEN);
        }
        catch (Exception e) {
            Logger.log("Error adding category: " + e.getMessage(), Color.RED);
        }
    }
}
