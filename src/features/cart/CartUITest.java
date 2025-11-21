package features.cart;

import features.customizationAddons.CustomizationAddonUI;
import features.items.ItemUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartUITest {

    private final CartUI cartUI = new CartUI();
    private final ItemUI itemUI = new ItemUI();
    private final CustomizationAddonUI customizationAddonUI = new CustomizationAddonUI();

    @Test
    @DisplayName("Test addint item to cart with valid inputs")
    public void testAddingItemToCartWithValidInputs() {

        itemUI.addCategory("Beverages");
        itemUI.addItem("Coffee", 5, "Beverages");
        customizationAddonUI.addCustomizationAddon("Size", "Coffee", true, "S:0,M:1,L:2");

        cartUI.addItemToCart("Coffee", "Size:L", 2);
        Cart cart = Cart.getInstance();
        assert(cart.getItems().size() == 1);
        Assertions.assertEquals(1400, cart.calculateTotal());
    }

}
