package features.menus;

import utils.Repository;

public class MenuRepository extends Repository<Menu> {
    private static MenuRepository instance = null;

    private MenuRepository() {
        super();
    }

    public static MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }
}
