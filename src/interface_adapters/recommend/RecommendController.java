package use_cases.recommend;

import entities.Category;

import java.util.ArrayList;
import java.util.List;

public class RecommendInputData {

    private final String username;
    private final List<Category> preferredCategories = new ArrayList<>();
    private final boolean prioritizeSubcategorySearch;
    private final boolean prioritizeUpvotePercentageSearch;
    private final boolean wantAutoRecommend;
    
    public RecommendInputData(String username, List<Category> preferredCategories,
                              boolean prioritizeSubcategorySearch, boolean prioritizeUpvotePercentageSearch,
                              boolean wantAutoRecommend) {
        this.username = username;
        this.preferredCategories.addAll(preferredCategories);
        this.prioritizeSubcategorySearch = prioritizeSubcategorySearch;
        this.prioritizeUpvotePercentageSearch = prioritizeUpvotePercentageSearch;
        this.wantAutoRecommend = wantAutoRecommend;
    }

    public String getUsername() {
        return this.username;
    }

    public List<Category> getPreferenceData() {
        return preferredCategories;
    }

    public boolean prioritizeSubcategorySearch() {
        return prioritizeSubcategorySearch;
    }

    public boolean prioritizeUpvotePercentageSearch() {
        return prioritizeUpvotePercentageSearch;
    }

    public boolean wantAutoRecommendation() {
        return this.wantAutoRecommend;
    }

}
