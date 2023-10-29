package use_cases.recommend;

import entities.ResearchPaper;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class RecommendInteractor implements RecommendInputBoundary {

    static final int THRESHOLD = 1;
    final RecommendDataAccessInterface userDataAccessObject;
    final RecommendOutputBoundary userPresenter;

    public RecommendInteractor(RecommendDataAccessInterface userDataAccessObject,
                               RecommendOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    /* Assume recommendInputData.existsByUsername() returns true. */
    public void execute(RecommendInputData recommendInputData) {
        // TODO: to be completed
        String username = recommendInputData.getUsername();
        User user = this.userDataAccessObject.getUser(username);
        String parentCategory = recommendInputData.getParentCategory();
        String paperId = recommendInputData.getId();
        String paperTitle = recommendInputData.getTitle();
        String paperJournalReference = recommendInputData.getJournalReference();

        List<ResearchPaper> recommendedPapers = new ArrayList<>();
        if (user.getLibrary().isEmpty() || !recommendInputData.wantAutoMode()) {
            if (paperId.equals("") && paperTitle.equals("") && paperJournalReference.equals("")) {
                List<String> paperIds = this.userDataAccessObject.getPapersByParentCategory(parentCategory);
                for (String paper : paperIds) {
                    if (getRelevanceFactor(paper) >= THRESHOLD) {
                        recommendedPapers.add(this.userDataAccessObject.getPaperById(paper));
                    }
                }
            } else {
                ResearchPaper targetPaper;
                if (!paperId.equals("")) {
                    targetPaper = this.userDataAccessObject.getPaperById(paperId);
                } else if (!paperTitle.equals("")) {
                    targetPaper = this.userDataAccessObject.getPaperByTitle(paperTitle);
                } else {
                    targetPaper = this.userDataAccessObject.getPaperByJournalReference(paperJournalReference);
                }
                recommendedPapers.add(targetPaper);
            }
        } else {
            // TODO: to be completed
        }
    }

    private int getRelevanceFactor(String paperId) {
        ResearchPaper paper = this.userDataAccessObject.getPaperById(paperId);
        // TODO: to be completed
        return -1;
    }
}
