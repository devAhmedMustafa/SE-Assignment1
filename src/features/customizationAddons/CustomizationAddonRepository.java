package features.customizationAddons;

import utils.Repository;

public class CustomizationAddonRepository extends Repository<CustomizationAddon> {
    private static CustomizationAddonRepository instance = null;
    private CustomizationAddonRepository() {
        super();
    }
    public static CustomizationAddonRepository getInstance() {
        if (instance == null) {
            instance = new CustomizationAddonRepository();
        }
        return instance;
    }
}
