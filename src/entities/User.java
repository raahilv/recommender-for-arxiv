package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String username;
    private final String password;
    private final List<Category> preferredCategories = new ArrayList<>();
    private final Map<String, ResearchPaper> library = new HashMap<>();
    private final Map<String, ResearchPaper> upvotedPapers = new HashMap<>();
    private final Map<String, ResearchPaper> downvotedPapers = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public List<Category> getPreferredCategories() {
        return preferredCategories;
    }

    public Map<String, ResearchPaper> getLibrary() {
        return library;
    }

    public Map<String, ResearchPaper> getUpvotedPapers() {
        return this.upvotedPapers;
    }

    public Map<String, ResearchPaper> getDownvotedPapers() {
        return this.downvotedPapers;
    }

    public void addPreferredCategories(Category category) {
        boolean exists = false;
        for (Category potentialCategory : this.preferredCategories) {
            if (potentialCategory.isSame(category)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            this.preferredCategories.add(category);
        }
    }

    public void savePaperIntoLibrary(ResearchPaper paper) {
        if (!this.library.containsKey(paper.getID())) {
            this.library.put(paper.getID(), paper);
        }
    }

    public void addUpvotedPapers(ResearchPaper researchPaper) {
        if (!this.upvotedPapers.containsKey(researchPaper.getID())) {
            this.upvotedPapers.put(researchPaper.getID(), researchPaper);
        }
    }

    public void addDownvotedPapers(ResearchPaper researchPaper) {
        if (!this.downvotedPapers.containsKey(researchPaper.getID())) {
            this.downvotedPapers.put(researchPaper.getID(), researchPaper);
        }
    }

    public String toString() {
        return "(" + this.username + "|" + this.password + ")";
    }

}
