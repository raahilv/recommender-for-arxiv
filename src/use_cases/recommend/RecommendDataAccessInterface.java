package use_cases.recommend;

import entities.User;
import entities.ResearchPaper;

import java.util.List;

public interface RecommendDataAccessInterface {
    boolean existsByUsername(String username);
    User getUser(String userName);
    ResearchPaper getPaperById(String id);
    ResearchPaper getPaperByTitle(String title);
    ResearchPaper getPaperByJournalReference(String journalReference);
    List<String> filterPapersByRootCategory(String rootCategory);

}
