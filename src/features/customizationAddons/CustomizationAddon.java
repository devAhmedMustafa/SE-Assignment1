package features.customizationAddons;

import features.items.Item;
import utils.storage.KeyAttribute;

import java.util.List;

public class CustomizationAddon {

    @KeyAttribute
    private String _id;

    public String name;
    public Item item;
    public boolean isMandatory;
    public List<CustomizationOption> options;

    public CustomizationAddon(String name, Item item, boolean isMandatory, List<CustomizationOption> options) {
        this.name = name;
        this.item = item;
        this.isMandatory = isMandatory;
        this.options = options;
        _id = name + "." + item.name;
    }

}
