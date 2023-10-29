package entities;

import java.util.ArrayList;
import java.util.List;

public class Subcategory extends Category {

    private final List<String> childCategories = new ArrayList<>();  // assume all in lower case
    private final String description;

    public Subcategory(String parentCategory, List<String> childCategories, String description) {
        super(parentCategory);
        this.childCategories.addAll(childCategories);
        this.description = description;
    }

    public boolean hasChildCategory(String target) {
        return this.childCategories.contains(target);
    }

    public String getDescription() {
        return this.description;
    }

}
