package features.cart;

import features.customizationAddons.CustomizationAddon;
import features.customizationAddons.CustomizationAddonRepository;
import features.customizationAddons.CustomizationOption;

import java.util.HashMap;
import java.util.Map;

public class AddonsParser {

    public static Map<String, CustomizationOption> parseAddons(String itemName, String addonsString) {
        Map<String, CustomizationOption> addons = new HashMap<>();
        if (addonsString == null || addonsString.trim().isEmpty()) {
            return addons;
        }

        String[] addonPairs = addonsString.split(",");
        for (String pair : addonPairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                CustomizationAddon category = CustomizationAddonRepository.getInstance().get(key + "." + itemName);
                if (category == null) {
                    throw new IllegalArgumentException("Addon category not found: " + key + " for item: " + itemName);
                }

                CustomizationOption option = category.options.stream()
                        .filter(opt -> opt.value.toString().equals(value))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Addon option not found: " + value + " in category: " + key));

                addons.put(key, option);
            }
        }

        return addons;
    }

}
