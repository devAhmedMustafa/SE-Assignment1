package features.customizationAddons;

import features.items.CategoryRepository;
import features.items.ItemRepository;
import features.items.ItemUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddonsTest {

    private void clearSetup(){
        CustomizationAddonRepository.getInstance().clear();
        ItemRepository.getInstance().clear();
        CategoryRepository.getInstance().clear();
    }

    @Test
    @DisplayName("Create an addon for non existing item")
    void case1() {
        CustomizationAddonUI customizationAddonUI = new CustomizationAddonUI();
        customizationAddonUI.addCustomizationAddon("Extra Cheese", "NonExistingItem", true, "Cheddar:2,Mozzarella:3");

        Assertions.assertEquals(0, CustomizationAddonRepository.getInstance().size());
        clearSetup();
    }

    @Test
    @DisplayName("Create a valid addon")
    void case2() {
        ItemUI itemUI = new ItemUI();
        itemUI.addCategory("Main Course");
        itemUI.addItem("Pizza", 10, "Main Course");

        CustomizationAddonUI customizationAddonUI = new CustomizationAddonUI();
        customizationAddonUI.addCustomizationAddon("Extra Cheese", "Pizza", true, "Cheddar:2,Mozzarella:3");

        Assertions.assertEquals(1, CustomizationAddonRepository.getInstance().size());

        CustomizationAddon fetchedAddon = CustomizationAddonRepository.getInstance().get("Extra Cheese.Pizza");
        Assertions.assertNotNull(fetchedAddon);
        Assertions.assertEquals("Extra Cheese", fetchedAddon.name);
        Assertions.assertEquals("Pizza", fetchedAddon.item.name);
        Assertions.assertTrue(fetchedAddon.isMandatory);
        Assertions.assertEquals(2, fetchedAddon.options.size());

        clearSetup();
    }

}
