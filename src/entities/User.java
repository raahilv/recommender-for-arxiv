package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private Map<Category, List<Subcategory>> preferredCategories = new HashMap<>();
    private Map<String, ResearchPaper> library = new HashMap<>();  // TODO: maybe change to a list holding the actual objects?
    private Map<String, ResearchPaper> upvotedPapers = new HashMap<>();
    private Map<String, ResearchPaper> downvotedPapers = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

// >>>>>>> main
    public String getPassword() {
        return password;
    }

// <<<<<<< kaiwenzheng
//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public Map<String, ResearchPaper> getLibrary() {
//         return library;
//     }

//     public boolean hasParentCategory(String parentCategory) {
//         return this.preferredCategories.containsKey(parentCategory);
//     }

//     public boolean addPaper(ResearchPaper paper) {
//         if (this.library.containsKey(paper.getId())) {
//             return false;
//         } else {
//             this.library.put(paper.getId(), paper);
//             return true;
//         }
//     }

//     public boolean hasEmptyLibrary() {
//         return this.library.isEmpty();
//     }

//     public Map<String, ResearchPaper> getUpvotedPapers() {
//         return upvotedPapers;
//     }

//     public void addUpvotedPaper(ResearchPaper upvotedPaper) {
//         this.downvotedPapers.put(upvotedPaper.getId(), upvotedPaper);
//     }

//     public Map<String, ResearchPaper> getPapersDownvoted() {
//         return downvotedPapers;
//     }

//     public void addDownvotedPaper(ResearchPaper downvotedPaper) {
//         this.downvotedPapers.put(downvotedPaper.getId(), downvotedPaper);
//     }

// =======
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
// >>>>>>> main
}