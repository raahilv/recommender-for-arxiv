package use_cases.recommend;

import java.util.ArrayList;
import java.util.List;

public class RecommendInputData {

    private final String username;
    private final List<List<String>> preferredCategoriesRawData = new ArrayList<>();
    private final boolean prioritizeSubcategorySearch;
    private final boolean prioritizeUpvotePercentageSearch;
    private final boolean wantAutoRecommend;
    
    public RecommendInputData(String username, List<List<String>> preferredCategoriesRawData,
                              boolean prioritizeSubcategorySearch, boolean prioritizeUpvotePercentageSearch,
                              boolean wantAutoRecommend) {
        this.username = username;
        this.preferredCategoriesRawData.addAll(preferredCategoriesRawData);
        this.prioritizeSubcategorySearch = prioritizeSubcategorySearch;
        this.prioritizeUpvotePercentageSearch = prioritizeUpvotePercentageSearch;
        this.wantAutoRecommend = wantAutoRecommend;
    }

    public String getUsername() {
        return this.username;
    }

    public List<List<String>> getPreferenceData() {
        return preferredCategoriesRawData;
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
