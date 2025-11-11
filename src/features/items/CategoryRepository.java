package features.items;

import utils.Repository;

public class CategoryRepository extends Repository<Category> {

    private static CategoryRepository instance = null;

    private CategoryRepository() {
        super();
    }

    public static CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }
}
