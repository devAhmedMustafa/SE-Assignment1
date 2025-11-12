package features.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemUITest {

    @Test
    @DisplayName("Adding Item with Non-Existing Category")
    public void Case1() {
        ItemUI itemUI = new ItemUI();
        itemUI.addItem("Test Item", 100, "NonExistingCategory");
        Assertions.assertEquals(0, ItemRepository.getInstance().size());
    }

    @Test
    @DisplayName("Adding Category without Parent Category")
    public void Case2() {
        ItemUI itemUI = new ItemUI();
        itemUI.addCategory("NewCategory");
        Assertions.assertEquals(1, CategoryRepository.getInstance().size());
        clearRepositories();
    }

    @Test
    @DisplayName("Adding Category with Parent Category")
    public void Case3() {
        ItemUI itemUI = new ItemUI();
        itemUI.addCategory("ParentCategory");
        itemUI.addCategory("ChildCategory", "ParentCategory");
        Assertions.assertEquals(2, CategoryRepository.getInstance().size());
        clearRepositories();
    }

    @Test
    @DisplayName("Adding Category with Parent Category that has another parent")
    public void Case4() {
        ItemUI itemUI = new ItemUI();
        itemUI.addCategory("GrandParentCategory");
        itemUI.addCategory("ParentCategory", "GrandParentCategory");
        itemUI.addCategory("ChildCategory", "GrandParentCategory>ParentCategory");
        Assertions.assertEquals(3, CategoryRepository.getInstance().size());
        clearRepositories();
    }

    @Test
    @DisplayName("Adding Item with Existing Category")
    public void Case5() {
        ItemUI itemUI = new ItemUI();
        itemUI.addCategory("ExistingCategory");
        itemUI.addItem("Test Item", 100, "ExistingCategory");
        Assertions.assertEquals(1, ItemRepository.getInstance().size());
        clearRepositories();
    }

    @Test
    @DisplayName("Add multiple Items and Categories")
    public void Case6() {
        ItemUI itemUI = new ItemUI();
        itemUI.addCategory("Category1");
        itemUI.addCategory("Category2", "Category1");
        itemUI.addItem("Item1", 150, "Category1");
        itemUI.addItem("Item2", 250, "Category1>Category2");
        Assertions.assertEquals(2, CategoryRepository.getInstance().size());
        Assertions.assertEquals(2, ItemRepository.getInstance().size());
        clearRepositories();
    }

    private void clearRepositories() {
        ItemRepository.getInstance().clear();
        CategoryRepository.getInstance().clear();
    }

}
