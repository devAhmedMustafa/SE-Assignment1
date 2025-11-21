package features.customizationAddons;

import utils.storage.Repository;

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
