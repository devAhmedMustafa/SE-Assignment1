package features.items;

import utils.KeyAttribute;

public class Category {

    @KeyAttribute
    private String _id;

    public String name;
    public Category parent;

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;

        _id = name;
        Category temp = parent;

        while (temp != null) {
            _id = temp.name + ">" + _id;
            temp = temp.parent;
        }
    }

    public String getId() {
        return _id;
    }
}
