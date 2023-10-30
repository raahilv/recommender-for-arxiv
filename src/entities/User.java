package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private ArrayList<String> preferredCategories = new ArrayList<>();
    private HashMap<String, ResearchPaper> library = new HashMap<>();
    private HashMap<String, ResearchPaper> papersUpvoted = new HashMap<>();
    private HashMap<String, ResearchPaper> papersDownvoted = new HashMap<>();

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

    public ArrayList<String> getPreferredCategories() {
        return preferredCategories;
    }

    public HashMap<String, ResearchPaper> getLibrary() {
        return library;
    }

    public HashMap<String, ResearchPaper> getPapersUpvoted() {
        return papersUpvoted;
    }

    public HashMap<String, ResearchPaper> getPapersDownvoted() {
        return papersDownvoted;
    }
}