package features.cart;

import features.customizationAddons.CustomizationOption;
import features.items.Item;
import features.items.ItemRepository;
import utils.debug.Logger;

import java.awt.*;
import java.util.Map;

public class CartUI {

    public String displayCart() {
        try {
            Cart cart = Cart.getInstance();
            Map<OrderedItem, Integer> items = cart.getItems();
            StringBuilder cartDisplay = new StringBuilder("Cart Contents:\n");
            for (Map.Entry<OrderedItem, Integer> entry : items.entrySet()) {
                OrderedItem item = entry.getKey();
                int quantity = entry.getValue();
                cartDisplay.append(item.getDetails())
                        .append(" - Quantity: ").append(quantity)
                        .append(" - Price: $").append(item.getPrice() * quantity)
                        .append("\n");

            }

            cartDisplay.append("Total: $").append(cart.calculateTotal()).append("\n");
            return cartDisplay.toString();
        }
        catch (Exception e) {
            Logger.log("Error displaying cart: " + e.getMessage(), Color.RED);
            return "Error displaying cart.";
        }
    }

    public void addItemToCart(String itemName, String addons, int quantity) {
        try {
            Item baseItem = ItemRepository.getInstance().get(itemName);
            if (baseItem == null) {
                Logger.log("Item not found: " + itemName, Color.RED);
                return;
            }

            PlainItem plainItem = new PlainItem(baseItem);

            Map<String, CustomizationOption> parsedAddons = AddonsParser.parseAddons(itemName, addons);

            CustomizedAddonItem customizedItem = new CustomizedAddonItem(plainItem, parsedAddons);

            Cart cart = Cart.getInstance();
            cart.addItem(customizedItem, quantity);
            Logger.log("Added to cart: " + customizedItem.getDetails() + " x" + quantity, Color.GREEN);
        }
        catch (Exception e) {
            Logger.log("Error adding item to cart: " + e.getMessage(), Color.RED);
        }
    }

}
