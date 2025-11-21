package features.menus;

import features.items.Category;
import features.items.Item;
import utils.debug.Logger;
import utils.parsers.PriceParser;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

public class MenuFormatting {

    public static int getMaxCharLevel(Map<Integer, List<Category>> categoryLevels) {
        int maxCharLevel = 0;
        for (var level : categoryLevels.values()){
            var levelSize = 0;
            for (var category : level){
                levelSize += category.name.length() + 1;
            }
            if (levelSize > maxCharLevel){
                maxCharLevel = levelSize;
            }
        }
        return maxCharLevel;
    }

    public static String recursiveCategoryDisplay(Map<Category, List<Category>> categoryLevels, List<Item> tempItems, Category topCategory, short depth){
        StringBuilder suffix = new StringBuilder();

        String whitespace = valueOf(' ').repeat(depth * 2);

        suffix.append(whitespace).append(topCategory.name).append("\n");

        for (Iterator<Item> iterator = tempItems.iterator(); iterator.hasNext();) {
            Item item = iterator.next();
            if (item.category == topCategory) {
                suffix.append(whitespace).append("  * ")
                        .append(item.name)
                        .append(" : ")
                        .append(PriceParser.centsToString(item.basePrice))
                        .append("\n");

                iterator.remove();
            }
        }

        for (var subCategory : categoryLevels.get(topCategory)){

            if (categoryLevels.containsKey(subCategory)) {
                suffix.append(recursiveCategoryDisplay(categoryLevels, tempItems, subCategory, (short) (depth + 1)));
            }
        }

        return suffix.toString();
    }

    public static void buildCategoryLevels(List<Item> items, Map<Category, List<Category>> categoryLevels, List<Category> topCategories) {

        for (var item : items) {
            var category = item.category;

            if (category.parent != null) {
                var parent = category.parent;
                categoryLevels.get(parent).add(category);

                Logger.log("Adding category: " + category.getId());
                categoryLevels.put(category, new ArrayList<>());


                while (parent.parent != null) {
                    parent = parent.parent;
                }

                if (!topCategories.contains(parent)) {
                    topCategories.add(parent);
                    Logger.log("Adding parent category: " + category.getId());
                }

            } else {
                if (!topCategories.contains(category)) {
                    topCategories.add(category);
                    Logger.log("Adding parent category: " + category.getId());
                }

                categoryLevels.computeIfAbsent(category, _ -> new ArrayList<>());
            }
        }

        Logger.log("Built category levels with " + categoryLevels.size() + " categories.", Color.MAGENTA);
        Logger.log("Top categories count: " + topCategories.size(), Color.MAGENTA);

    }

}
