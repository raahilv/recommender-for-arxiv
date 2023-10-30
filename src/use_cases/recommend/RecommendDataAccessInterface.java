package use_cases.recommend;

import entities.User;
import entities.ResearchPaper;

import java.util.List;

public interface RecommendDataAccessInterface {
    boolean existsByUsername(String username);
    User getUser(String userName);
    ResearchPaper getPaperById(String id);
    ResearchPaper getPaperByTitle(String title);  // assume all in lower case
    ResearchPaper getPaperByJournalReference(String journalReference);  // assume all in lower case
    List<String> filterPapersByParentCategory(String parentCategory);

}
