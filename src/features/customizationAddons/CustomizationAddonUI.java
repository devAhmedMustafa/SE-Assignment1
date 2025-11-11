package features.customizationAddons;

import utils.Logger;

import java.awt.*;
import java.util.List;

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

}
