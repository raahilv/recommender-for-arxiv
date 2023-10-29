package use_cases.recommend;

import java.util.ArrayList;
import java.util.List;

/* KAIWEN ZHENG, Oct 28 2023
* Currently, it is assumed that a user is asked for providing answers
* to the following options (in the ordered listed) as the preference data:
*   - Paper ID (OPTIONAL; if not empty, then disable all other input fields)
*   - Paper title (OPTIONAL; if not empty, then disable all other input fields)
*   - Journal reference (OPTIONAL; if not empty, then disable all other input fields)
*   - Category of interest (restricted to the ones provided in the drop-down menu;
*     should select one option only) (MANDATORY)
*   - Sub-categories of interest (restricted to the ones existing under the corresponding
*     category; should select at least one option) (MANDATORY)
*   - Order in which filtered papers are sorted
* */
public class RecommendInputData {

    private final String username;
    private final String id;
    private final String title;
    private final String journalReference;
    private final String parentCategory;
    private final List<String> childCategories = new ArrayList<>();

    private final boolean wantAutoRecommendation;

    // TODO: choices of sorting/ordering to be added

    public RecommendInputData(String username, String id, String title, String journalReference,
                              String parentCategory, List<String> childCategories,
                              boolean wantAutoRecommendation) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.journalReference = journalReference;
        this.parentCategory = parentCategory;
        this.childCategories.addAll(childCategories);
        this.wantAutoRecommendation = wantAutoRecommendation;
    }

    public String getUsername() { return this.username; }

    public String getId() { return this.id; }

    public String getTitle() { return this.title; }

    public String getJournalReference() { return this.journalReference; }

    public String getParentCategory() { return this.parentCategory; }

    public List<String> getChildCategories() { return this.childCategories; }

    public boolean wantAutoMode() { return this.wantAutoRecommendation; }

}
