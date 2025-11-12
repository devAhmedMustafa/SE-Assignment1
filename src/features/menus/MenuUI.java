package features.menus;

import features.items.Category;
import features.items.Item;
import utils.Logger;
import utils.MenuFormatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuUI {

    public String displayMenu(String menuName) {
        try{
            Logger.log("Displaying menu: " + menuName, java.awt.Color.BLUE);
            Menu menu = MenuRepository.getInstance().get(menuName);
            if (menu == null) {
                Logger.log("Menu not found: " + menuName, java.awt.Color.RED);
                return null;
            }

            if (menu.items.isEmpty()) {
                return "Menu is empty.";
            } else {
                StringBuilder itemList = new StringBuilder();

                Map<Category, List<Category>> categoryLevels = new HashMap<>();
                List<Category> topCategories = new ArrayList<>();

                MenuFormatting.buildCategoryLevels(menu.items, categoryLevels, topCategories);

                List<Item> tempItems = new ArrayList<>(menu.items);

                for (var topCategory : topCategories){
                    itemList.append(MenuFormatting.recursiveCategoryDisplay(categoryLevels, tempItems, topCategory, (short) 0));
                }

                return itemList.toString();
            }
        }
        catch (Exception e){
            Logger.log("Failed to display menu: " + e.getMessage(), java.awt.Color.RED);
            return null;
        }
    }

    public String displayMenu() {
        try {
            Logger.log("Displaying all menus", java.awt.Color.BLUE);
            List<Menu> menus = MenuRepository.getInstance().getAll();
            if (menus.isEmpty()) {
                Logger.log("No menus available", java.awt.Color.YELLOW);
                return "No menus available.";
            }
            StringBuilder menuList = new StringBuilder("Available Menus:\n");
            for (var menu : menus) {
                menuList.append("- ").append(menu.title).append(": ").append(menu.description).append("\n");
            }
            Logger.log(menuList.toString(), java.awt.Color.GREEN);
            return menuList.toString();
        }
        catch (Exception e) {
            Logger.log("Failed to display menus: " + e.getMessage(), java.awt.Color.RED);
            return null;
        }
    }

    public void addMenu(String title, String description, String items) {
        try {
            Logger.log("Adding menu: " + title, java.awt.Color.BLUE);
            List<String> itemList = List.of(
                    !items.isEmpty() ? items.split(", ") : new String[]{}
            );
            Menu createdMenu = MenuFactory.createMenu(title, description, itemList);
            MenuRepository.getInstance().add(createdMenu);
            Logger.log("Menu added: " + title + " with " + itemList.size() + " items", java.awt.Color.GREEN);
        }
        catch (Exception e) {
            Logger.log("Failed to add menu: " + e.getMessage(), java.awt.Color.RED);
        }
    }

}
