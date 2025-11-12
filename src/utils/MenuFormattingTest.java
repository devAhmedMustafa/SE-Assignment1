package utils;

import features.items.Category;
import features.items.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuFormattingTest {

    @Test
    @DisplayName("Test getMaxCharLevel with various category levels")
    public void testGetMaxCharLevel() {
        Map<Integer, List<Category>> categoryLevels = Map.of(
            1, List.of(new Category("Appetizers", null), new Category("Main Course", null)),
            2, List.of(new Category("Desserts", null)),
            3, List.of(new Category("Beverages", null), new Category("Sides", null))
        );

        int maxCharLevel = MenuFormatting.getMaxCharLevel(categoryLevels);
        // Appetizers (11) + Main Course (11) + space (1) = 23
        // Desserts (8) = 8
        // Beverages (9) + Sides (5) + space (1) = 15
        assert maxCharLevel == 23 : "Expected maxCharLevel to be 23, but got " + maxCharLevel;
    }

    @Test
    @DisplayName("Test recursiveCategoryDisplay with nested categories")
    public void testRecursiveCategoryDisplay() {
        Category appetizers = new Category("Appetizers", null);
        Category salads = new Category("Salads", appetizers);
        Category mainCourse = new Category("Main Course", null);

        Map<Category, List<Category>> categoryLevels = Map.of(
                appetizers, List.of(salads),
                mainCourse, List.of(),
                salads, List.of()
        );

        List<features.items.Item> tempItems = new ArrayList<>(List.of(
                new features.items.Item("Caesar Salad", 799, salads),
                new features.items.Item("Grilled Chicken", 1299, mainCourse)
        ));

        String result = MenuFormatting.recursiveCategoryDisplay(categoryLevels, tempItems, appetizers, (short) 0);
        String expected = """
                Appetizers
                  Salads
                    * Caesar Salad : $7.99
                """;

        assert result.startsWith(expected) : "Expected result to start with:\n" + expected + "\nbut got:\n" + result;
    }

    @Test
    @DisplayName("Test buildCategoryLevels with items")
    public void testBuildCategoryLevels() {
        Category appetizers = new Category("Appetizers", null);
        Category salads = new Category("Salads", appetizers);
        Category mainCourse = new Category("Main Course", null);

        List<features.items.Item> items = List.of(
                new features.items.Item("Caesar Salad", 799, salads),
                new features.items.Item("Grilled Chicken", 1299, mainCourse)
        );

        Map<Category, List<Category>> categoryLevels = new HashMap<>();
        List<Category> topCategories = new ArrayList<>();

        MenuFormatting.buildCategoryLevels(items, categoryLevels, topCategories);

        assert categoryLevels.containsKey(appetizers) : "Category levels should contain Appetizers";
        assert categoryLevels.get(appetizers).contains(salads) : "Appetizers should contain Salads as subcategory";
        assert topCategories.contains(appetizers) : "Top categories should contain Appetizers";
        assert topCategories.contains(mainCourse) : "Top categories should contain Main Course";
    }

    @Test
    @DisplayName("Test buildCategoryLevels with nested categories")
    public void testBuildCategoryLevelsNested() {
        Category c1 = new Category("C1", null);
        Category c2 = new Category("C2", c1);
        Category c3 = new Category("C3", c2);

        List<features.items.Item> items = List.of(
                new features.items.Item("Item1", 500, c1),
                new features.items.Item("Item2", 600, c2),
                new features.items.Item("Item3", 700, c3)
        );
        Map<Category, List<Category>> categoryLevels = new HashMap<>();
        List<Category> topCategories = new ArrayList<>();

        MenuFormatting.buildCategoryLevels(items, categoryLevels, topCategories);

        assert categoryLevels.containsKey(c1) : "Category levels should contain C1";
        assert categoryLevels.get(c1).contains(c2) : "C1 should contain C2 as subcategory";
        assert categoryLevels.get(c2).contains(c3) : "C2 should contain C3 as subcategory";
        assert categoryLevels.size() == 3 : "There should be three categories in total";
        assert topCategories.contains(c1) : "Top categories should contain C1";
        assert topCategories.size() == 1 : "There should be only one top category";
    }

    @Test
    @DisplayName("Test recursiveCategoryDisplay with nested categories and multiple items")
    public void testRecursiveCategoryDisplayMultipleItems() {
        Category c1 = new Category("C1", null);
        Category c2 = new Category("C2", c1);
        Category c3 = new Category("C3", c2);
        Map<Category, List<Category>> categoryLevels = Map.of(
                c1, List.of(c2),
                c2, List.of(c3),
                c3, List.of()
        );
        List<Item> tempItems = new ArrayList<>(List.of(
                new Item("Item1", 300, c1),
                new Item("Item2", 400, c2),
                new Item("Item3", 500, c3)
        ));
        String result = MenuFormatting.recursiveCategoryDisplay(categoryLevels, tempItems, c1, (short) 0);
        String expected = """
                C1
                  * Item1 : $3.00
                  C2
                    * Item2 : $4.00
                    C3
                      * Item3 : $5.00
                """;
        assert result.equals(expected) : "Expected result to be:\n" + expected + "\nbut got:\n" + result;
    }
}
