package features.items;

import utils.Repository;

public class ItemRepository extends Repository<Item> {
    private static ItemRepository instance;
    private ItemRepository() {
        super();
    }
    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepository();
        }
        return instance;
    }
}
