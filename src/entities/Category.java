package entities;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String rootCategory;
    private final String subcategory;
    private final String description;

    public Category(String rootCategory, String subcategory, String description) {
        this.rootCategory = rootCategory;
        this.subcategory = subcategory;
        this.description = description;
    }

    public String getRootCategory() {
        return this.rootCategory;
    }

    public String getSubcategory() {
        return this.subcategory;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean hasSameRootCategory(Category other) {
        return this.rootCategory.equalsIgnoreCase(other.rootCategory);
    }

    public boolean isSame(Category other) {
        return this.hasSameRootCategory(other) &&
                this.subcategory.equalsIgnoreCase(other.subcategory);
    }

    public List<String> toList() {
        List<String> categoryMetadata = new ArrayList<>();
        categoryMetadata.add(this.rootCategory);
        categoryMetadata.add(this.subcategory);
        categoryMetadata.add(this.description);
        return categoryMetadata;
    }

    public String toString() {
        return "(" + this.rootCategory + "|" +
                this.subcategory + "|" +
                this.description + ")";
    }

}
