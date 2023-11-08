package use_cases.recommend;

import entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecommendInputData {

    private final String username;  // mandatory, create user first if not exists
    private final List<Category> preferenceData = new ArrayList<>();  // mandatory
    private final boolean wantAutoRecommend;  // mandatory
    private final boolean prioritizeSubcategorySearch;  // optional
    private final boolean prioritizeUpvotePercentageSearch;  // optional

    public RecommendInputData(String username, List<Category> preferredCategories,
                              boolean prioritizeSubcategorySearch, boolean prioritizeUpvotePercentageSearch,
                              boolean wantAutoRecommend) {
        this.username = username;
        this.preferenceData.addAll(preferredCategories);
        this.prioritizeSubcategorySearch = prioritizeSubcategorySearch;
        this.prioritizeUpvotePercentageSearch = prioritizeUpvotePercentageSearch;
        this.wantAutoRecommend = wantAutoRecommend;
    }

    public String getUsername() { return this.username; }

    public List<Category> getPreferenceData() { return this.preferenceData; }

    public boolean prioritizeSubcategorySearch() { return this.prioritizeSubcategorySearch; }

    public boolean prioritizeUpvotePercentageSearch() { return this.prioritizeUpvotePercentageSearch; }

    public boolean wantAutoRecommendMode() { return this.wantAutoRecommend; }

}
