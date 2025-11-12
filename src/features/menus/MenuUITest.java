package features.menus;

import features.items.ItemUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuUITest {

    private void clearSetup() {
        MenuRepository.getInstance().clear();
    }

    @Test
    @DisplayName("Test creating empty menu")
    public void case0(){
        MenuUI menuUI = new MenuUI();
        menuUI.addMenu("Empty Menu", "This is an empty menu", "");
        Assertions.assertEquals(1, MenuRepository.getInstance().size());
        Menu menu = MenuRepository.getInstance().get("Empty Menu");
        Assertions.assertNotNull(menu);
        Assertions.assertEquals("Empty Menu", menu.title);
        Assertions.assertEquals("This is an empty menu", menu.description);
        Assertions.assertEquals(0, menu.items.size());
        clearSetup();
    }

    @Test
    @DisplayName("Test displaying non-existent menu")
    public void case1() {
        MenuUI menuUI = new MenuUI();
        String result = menuUI.displayMenu("NonExistentMenu");
        Assertions.assertNull(result);
        clearSetup();
    }

    @Test
    @DisplayName("Test displaying an empty menu")
    public void case2() {
        MenuUI menuUI = new MenuUI();
        menuUI.addMenu("Empty Menu", "This is an empty menu", "");
        String result = menuUI.displayMenu("Empty Menu");
        Assertions.assertEquals("Menu is empty.", result);
        clearSetup();
    }

    @Test
    @DisplayName("Test displaying a menu with items")
    public void case3() {
        MenuUI menuUI = new MenuUI();
        ItemUI itemUI = new ItemUI();
        itemUI.addCategory("C1");
        itemUI.addItem("Burger", 5f, "C1");
        itemUI.addItem("Pizza", 5f, "C1");
        itemUI.addItem("Salad", 5f, "C1");
        menuUI.addMenu("Food Menu", "Delicious food items", "Burger, Pizza, Salad");
        String result = menuUI.displayMenu("Food Menu");
        String expected = """
                C1
                  * Burger : $5.00
                  * Pizza : $5.00
                  * Salad : $5.00
                """;

        Assertions.assertEquals(expected, result);
        clearSetup();
    }

    @Test
    @DisplayName("Test displaying all menus")
    public void case4() {
        MenuUI menuUI = new MenuUI();
        menuUI.addMenu("Menu1", "First Menu", "");
        menuUI.addMenu("Menu2", "Second Menu", "");
        String result = menuUI.displayMenu();
        String expected = """
                Available Menus:
                - Menu1: First Menu
                - Menu2: Second Menu
                """;
        Assertions.assertEquals(expected, result);
        clearSetup();
    }

    @Test
    @DisplayName("Test displaying menu with nested categories")
    public void case5() {
        MenuUI menuUI = new MenuUI();
        ItemUI itemUI = new ItemUI();
        itemUI.addCategory("C1");
        itemUI.addCategory("C2", "C1");
        itemUI.addCategory("C3", "C1>C2");

        itemUI.addItem("Item1", 3f, "C1");
        itemUI.addItem("Item2", 4f, "C1>C2");
        itemUI.addItem("Item3", 5f, "C1>C2>C3");

        menuUI.addMenu("Nested Menu", "Menu with nested categories", "Item1, Item2, Item3");

        String result = menuUI.displayMenu("Nested Menu");
        String expected = """
                C1
                  * Item1 : $3.00
                  C2
                    * Item2 : $4.00
                    C3
                      * Item3 : $5.00
                """;
        Assertions.assertEquals(expected, result);
        clearSetup();
    }

}
