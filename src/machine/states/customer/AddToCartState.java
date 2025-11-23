package machine.states.customer;

import features.cart.CartUI;
import features.customizationAddons.CustomizationAddonUI;
import machine.AppMachine;
import machine.AppState;

import java.util.Map;
import java.util.Scanner;

public class AddToCartState implements AppState {

    private final CartUI cartUI = new CartUI();
    private final CustomizationAddonUI customizationAddonUI = new CustomizationAddonUI();

    @Override
    public void onEnter(AppMachine machine) {
        Scanner sc = new Scanner(System.in);
            System.out.println("=== Add Item to Cart ===");
        while (true) {
            try {
                System.out.print("Enter Item Name to add to cart: ");
                String itemName = sc.nextLine();

                System.out.print("Enter Quantity to add to cart: ");
                int quantity = Integer.parseInt(sc.nextLine());

                Map<String, String> availableAddons = customizationAddonUI.listItemCustomizationAddons(itemName);

                if (!availableAddons.isEmpty()) {
                    System.out.println("Available Customization Addons for " + itemName + ":");
                    StringBuilder selectedAddons = new StringBuilder();
                    for (Map.Entry<String, String> entry : availableAddons.entrySet()) {
                        System.out.println(entry.getValue());
                        String addonsInput = sc.nextLine();
                        selectedAddons.append(entry.getKey()).append(": ").append(addonsInput);

                        if (!entry.getKey().equals(availableAddons.keySet().toArray()[availableAddons.size() - 1])) {
                            selectedAddons.append(", ");
                        }
                    }

                    cartUI.addItemToCart(itemName, selectedAddons.toString(), quantity);
                } else {
                    cartUI.addItemToCart(itemName, "", quantity);
                }

                System.out.println("Item added to cart successfully!");
                machine.back();
                break;
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public void onExit(AppMachine machine) {

    }
}
