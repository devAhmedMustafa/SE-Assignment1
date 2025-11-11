package features.customizationAddons;

import features.items.Item;
import features.items.ItemRepository;

import java.util.List;

public class CustomizationAddonFactory {

    public static CustomizationAddon createCustomizationAddon(String name, String itemName, boolean isMandatory, List<CustomizationOption> options) {
        Item item = ItemRepository.getInstance().get(itemName);
        if (item == null) {
            throw new IllegalArgumentException("Item with name " + itemName + " does not exist.");
        }
        return new CustomizationAddon(name, item, isMandatory, options);
    }

}
