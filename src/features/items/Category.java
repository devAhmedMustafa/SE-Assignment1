package features.items;

public class Category {
    public String name;
    public Category parent;

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
