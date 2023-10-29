package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private String username;
    private String password;
    private HashMap<String, Integer> preferredCategories = new HashMap<>();
    // parent categories of papers : count of papers of the corresponding parent category
    private List<String> library = new ArrayList<>();
    private List<String> papersUpvoted = new ArrayList<>();
    private List<String> papersDownvoted = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean upvote(String paperId) {
        // TODO: should this be regarded as a separate use case?
        return false;
    }

    public boolean downvote(String paperId) {
        // TODO: should this be regarded as a separate use case?
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getLibrary() {
        return library;
    }

    public void setLibrary(List<String> library) {
        this.library = library;
    }

    public List<String> getPapersUpvoted() {
        return papersUpvoted;
    }

    public void setPapersUpvoted(List<String> papersUpvoted) {
        this.papersUpvoted = papersUpvoted;
    }

    public List<String> getPapersDownvoted() {
        return papersDownvoted;
    }

    public void setPapersDownvoted(List<String> papersDownvoted) {
        this.papersDownvoted = papersDownvoted;
    }

}