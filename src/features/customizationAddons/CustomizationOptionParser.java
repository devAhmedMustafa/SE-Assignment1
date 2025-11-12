package features.customizationAddons;

import utils.PriceAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomizationOptionParser {

    public static List<CustomizationOption> parseStringToList(String input) {

        // Scheme: "Option1:Price1,Option2:Price2,..."
        List<CustomizationOption> optionsList = new ArrayList<>();
        String[] options = input.split(",");
        for (String option : options) {
            String[] parts = option.split(":");
            if (parts.length == 2) {
                String optionName = parts[0].trim();
                int optionPrice;
                try {
                    optionPrice = PriceAdapter.floatToCents(Float.parseFloat(parts[1].trim()));
                } catch (NumberFormatException e) {
                    continue;
                }
                optionsList.add(new CustomizationOption(optionName, optionPrice));
            }
        }

        return optionsList;
    }

}
