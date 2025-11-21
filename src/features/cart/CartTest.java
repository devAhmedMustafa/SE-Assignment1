package features.cart;

import features.items.Category;
import features.items.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CartTest {

    @Test
    @DisplayName("Test adding a plain item to the cart" )
    public void testAddPlainItemToCart() {
        Category category = new Category("Beverages", null);
        Item coffe = new Item("Coffee", 5, category);

        PlainItem plainItem = new PlainItem(coffe);
        Cart cart = Cart.getInstance();
        cart.addItem(plainItem, 2);

        assert cart.calculateTotal() == 10 : "Total should be 10 for 2 Coffees";
    }

    @Test
    @DisplayName("Test calculating total with customized addon item")
    public void testCalculateTotalWithCustomizedAddonItem() {
        Category category = new Category("Beverages", null);
        Item tea = new Item("Tea", 3, category);

        PlainItem plainItem = new PlainItem(tea);
        CustomizedAddonItem customizedItem = new CustomizedAddonItem(plainItem, Map.of(
                "Size", new features.customizationAddons.CustomizationOption("Large", 2),
                "Sugar", new features.customizationAddons.CustomizationOption("Extra", 1)
        ));

        Cart cart = Cart.getInstance();
        cart.addItem(customizedItem, 1);

        assert cart.calculateTotal() == 6 : "Total should be 6 for customized Tea";
    }

}
