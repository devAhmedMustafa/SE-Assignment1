package features.customizationAddons;

import utils.debug.Logger;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class CustomizationAddonUI {

    public void addCustomizationAddon(String addonName, String itemName, boolean isMandatory, String options) {
        try {
            Logger.log("Adding customization addon: " + addonName, Color.BLUE);
            List<CustomizationOption> optionsList = CustomizationOptionParser.parseStringToList(options);
            CustomizationAddon createdAddon = CustomizationAddonFactory.createCustomizationAddon(addonName, itemName, isMandatory, optionsList);

            CustomizationAddonRepository.getInstance().add(createdAddon);
            Logger.log("Customization addon '" + addonName + "' added successfully.", Color.GREEN);
        }
        catch (Exception e) {
            Logger.log("Error adding customization addon: " + e.getMessage(), Color.RED);
        }
    }

    public Map<String, String> listItemCustomizationAddons(String itemName) {
        try {
            Logger.log("Fetching customization addons for item: " + itemName, Color.BLUE);

            List<CustomizationAddon> addons = CustomizationAddonRepository.getInstance().getAll().stream().toList().stream().filter(
                    addon -> addon.item.name.equalsIgnoreCase(itemName)
            ).toList();

            if (addons.isEmpty()) {
                Logger.log("No customization addons found for item: " + itemName, Color.ORANGE);
                return new java.util.HashMap<>();
            }

            Map<String, String> addonDescriptions = new java.util.HashMap<>();

            for (CustomizationAddon addon : addons) {
                StringBuilder sb = new StringBuilder();
                sb.append("Addon Name: ").append(addon.name).append("\n");
                for (CustomizationOption option : addon.options) {
                    sb.append("  - Option: ").append(option.value).append(" (Price: ").append(option.deltaPrice).append(")\n");
                }
                addonDescriptions.put(addon.name, sb.toString());
            }

            Logger.log("Fetched " + addons.size() + " customization addons for item: " + itemName, Color.GREEN);
            return addonDescriptions;
        }
        catch (Exception e) {
            Logger.log("Error fetching customization addons: " + e.getMessage(), Color.RED);
            return new java.util.HashMap<>();
        }
    }

}
