package use_cases.recommend;

import entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* KAIWEN ZHENG, Oct 28 2023
* Currently, it is assumed that a user is asked for providing answers
* to the following options (in the ordered listed) as the preference data:
*   - Category of interest (restricted to the ones provided in the drop-down menu;
*     should select one option only) (MANDATORY)
*   - Sub-categories of interest (restricted to the ones existing under the corresponding
*     category; should select at least one option) (MANDATORY)
*   - Order in which filtered papers are sorted (OPTIONAL)
* */
public class RecommendInputData {

    private final String username;
    private final List<Category> preferenceData = new ArrayList<>();
    private final boolean wantAutoRecommend;

    /* TODO: choices of sorting/ordering to be added */

    public RecommendInputData(String username, List<Category> preferredCategories,
                              boolean wantAutoRecommend) {
        this.username = username;
        this.preferenceData.addAll(preferredCategories);
        this.wantAutoRecommend = wantAutoRecommend;
    }

    public String getUsername() { return this.username; }

    public List<Category> getPreferenceData() { return this.preferenceData; }

    public boolean wantAutoRecommendMode() { return this.wantAutoRecommend; }

}
