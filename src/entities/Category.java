package entities;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String rootCategory;
    private final String subcategory;

    public Category(String rootCategory, String subcategory){
        this.rootCategory = rootCategory;
        this.subcategory = subcategory;
    }

    public String getRootCategory() {
        return this.rootCategory;
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
        return categoryMetadata;
    }

}
