package use_cases.recommend;

import entities.Category;
import entities.User;
import entities.ResearchPaper;

import java.util.List;

public interface RecommendDataAccessInterface {
    boolean existsByUsername(String username);
    User getUser(String userName);
    ResearchPaper getPaperById(String id);
    ResearchPaper getPaperByTitle(String title);
    List<String> filterPapersByRootCategory(Category category);

}
