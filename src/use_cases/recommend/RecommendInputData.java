package use_cases.recommend;

import entities.Category;

import java.util.ArrayList;
import java.util.List;

public class RecommendInputData {

    private final String username;
    private final String id;
    private final String title;
    private final String journalReference;
    private final String parentCategory;
    private final List<String> childCategories = new ArrayList<>();
    private final boolean wantAutoRecommendation;
    
    public RecommendInputData(String username, String id, String title, String journalReference,
                        String parentCategory, List<String> childCategories, boolean wantAutoRecommendation) {
        this.username = username;
        this.id = id;
        this.title = title;
        this.journalReference = journalReference;
        this.parentCategory = parentCategory;
        this.childCategories.addAll(childCategories);
        this.wantAutoRecommendation = wantAutoRecommendation;
    }

    public String getUsername() {
        return this.username;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getJournalReference() {
        return this.journalReference;
    }

    public String getParentCategory() {
        return this.parentCategory;
    }

    public List<String> getChildCategories() {
        return this.childCategories;
    }

    public boolean wantAutoRecommendation() {
        return this.wantAutoRecommendation;
    }

}
