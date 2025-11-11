package features.items;

public class CategoryFactory {

    public Category create(String name, String parentName) {
        Category parent = CategoryRepository.getInstance().get(parentName);
        return new Category(name, parent);
    }

}
