package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private List<Category> preferredCategories = new ArrayList<>();
    private Map<String, ResearchPaper> library = new HashMap<>();
    private Map<String, ResearchPaper> upvotedPapers = new HashMap<>();
    private Map<String, ResearchPaper> downvotedPapers = new HashMap<>();

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

    public Map<String, ResearchPaper> getPapersUpvoted() {
        return upvotedPapers;
    }

    public Map<String, ResearchPaper> getPapersDownvoted() {
        return downvotedPapers;
    }

}
