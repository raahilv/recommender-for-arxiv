package entities;

import java.util.ArrayList;
import java.util.List;

public class PreferenceData {

    private final String username;
    private final List<Category> preferredCategories = new ArrayList<>();
    private final boolean prioritizeSubcategorySearch;
    private final boolean prioritizeUpvotePercentageSearch;
    private final boolean wantAutoRecommend;

    public PreferenceData(String username, List<Category> preferredCategories,
                          boolean prioritizeSubcategorySearch, boolean prioritizeUpvotePercentageSearch,
                          boolean wantAutoRecommend) {
        this.username = username;
        this.preferredCategories.addAll(preferredCategories);
        this.prioritizeSubcategorySearch = prioritizeSubcategorySearch;
        this.prioritizeUpvotePercentageSearch = prioritizeUpvotePercentageSearch;
        this.wantAutoRecommend = wantAutoRecommend;
    }

    public String getUsername() {
        return username;
    }

    public List<Category> getPreferredCategories() {
        return preferredCategories;
    }

    public boolean prioritizeSubcategorySearch() {
        return prioritizeSubcategorySearch;
    }

    public boolean prioritizeUpvotePercentageSearch() {
        return prioritizeUpvotePercentageSearch;
    }

    public boolean wantAutoRecommend() {
        return wantAutoRecommend;
    }

}
