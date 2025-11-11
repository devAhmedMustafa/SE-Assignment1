package features.items;

public class ItemFactory {

    public static Item createItem(String name, int basePrice, String categoryName) {

        Category category = CategoryRepository.getInstance().get(categoryName);

        if (category == null) {
            throw new IllegalArgumentException("Category not found: " + categoryName);
        }

        return new Item(name, basePrice, category);
    }

}
