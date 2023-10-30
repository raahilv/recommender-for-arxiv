package entities;

import java.util.ArrayList;
import java.util.List;

public class Subcategory extends Category {

    private final String childCategory;  // assume all in lower case
    private final String description;

    public Subcategory(String parentCategory, String childCategory, String description) {
        super(parentCategory);
        this.childCategory = childCategory;
        this.description = description;
    }

    public String getChildCategory() {
        return this.childCategory;
    }

    public String getDescription() {
        return this.description;
    }

}
