package entities;

import java.util.ArrayList;
import java.util.List;

public class PreferenceDataFactory {

    public PreferenceData createWithRawData(String username, List<List<String>> preferredCategoriesRawData,
                                            boolean prioritizeSubcategorySearch,
                                            boolean prioritizeUpvotePercentageSearch,
                                            boolean wantAutoRecommend) {
        List<Category> preferredCategories = new ArrayList<>();
        for (List<String> preferredCategory : preferredCategoriesRawData) {
            preferredCategories.add(new CategoryFactory().create(preferredCategory));
        }

        return new PreferenceData(username, preferredCategories, prioritizeSubcategorySearch,
                prioritizeUpvotePercentageSearch, wantAutoRecommend);
    }

    public PreferenceData createWithObject(String username, List<Category> preferredCategories,
                                 boolean prioritizeSubcategorySearch, boolean prioritizeUpvotePercentageSearch,
                                 boolean wantAutoRecommend) {
        return new PreferenceData(username, preferredCategories, prioritizeSubcategorySearch,
                prioritizeUpvotePercentageSearch, wantAutoRecommend);
    }

}