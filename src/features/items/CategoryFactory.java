package features.items;

public class CategoryFactory {

    public static Category create(String name, String parentName) {
        Category parent = CategoryRepository.getInstance().get(parentName);
        if (parent == null) {
            throw new IllegalArgumentException("Parent category does not exist: " + parentName);
        }

        return new Category(name, parent);
    }

    public static Category create(String name) {
        return new Category(name, null);
    }

}
