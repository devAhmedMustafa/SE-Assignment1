package features.cart;

import features.customizationAddons.CustomizationOption;

import java.util.Map;

public class CustomizedAddonItem extends DecoratedItem {
    private final Map<String, CustomizationOption> selectedOption;

    public CustomizedAddonItem(PlainItem plaintItem, Map<String, CustomizationOption> selectedOption) {
        super(plaintItem);
        this.selectedOption = selectedOption;
    }

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder(this.item.getDetails());
        for (Map.Entry<String, CustomizationOption> entry : selectedOption.entrySet()) {
            details.append(", ").append(entry.getKey()).append(": ").append(entry.getValue().value);
        }
        return details.toString();
    }

    @Override
    public int getPrice() {
        int totalPrice = this.item.getPrice();
        for (CustomizationOption option : selectedOption.values()) {
            totalPrice += option.deltaPrice;
        }
        return totalPrice;
    }
}
