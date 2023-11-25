package entities;

import java.util.List;

public class CategoryFactory {

    public Category create(List<String> preferredCategoryRawData) {
        return new Category(
                preferredCategoryRawData.get(0),  // root category
                preferredCategoryRawData.get(1),  // subcategory
                preferredCategoryRawData.get(2)  // category description
        );
    }

}
