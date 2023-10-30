package use_cases.recommend;

import entities.ResearchPaper;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class RecommendInteractor implements RecommendInputBoundary {

    static final int THRESHOLD = 1;  // TODO: to be polished
    final RecommendDataAccessInterface userDataAccessObject;
    final RecommendOutputBoundary userPresenter;

    public RecommendInteractor(RecommendDataAccessInterface userDataAccessObject,
                               RecommendOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    /* Assume recommendInputData.existsByUsername() returns true. */
    public void execute(RecommendInputData recommendInputData) {
        /*
           TODO:
            In the corresponding view, if one of the optional fields is
            filled in, then all other fields should be disabled for entering
            values.
        */
        String username = recommendInputData.getUsername();
        User user = this.userDataAccessObject.getUser(username);
        String parentCategory = recommendInputData.getParentCategory();
        String paperId = recommendInputData.getId();
        String paperTitle = recommendInputData.getTitle();
        String paperJournalReference = recommendInputData.getJournalReference();

        List<List<Object>> recommendedPapers = new ArrayList<>();
        if (user.hasEmptyLibrary() || !recommendInputData.wantAutoMode()) {
            if (paperId.equals("") && paperTitle.equals("") && paperJournalReference.equals("")) {
                List<String> candidatePaperIds = this.userDataAccessObject.filterPapersByParentCategory(parentCategory);
                for (String candidatePaperId : candidatePaperIds) {
                    if (getRelevanceFactor(candidatePaperId) >= THRESHOLD) {
                        ResearchPaper recommendedPaper = this.userDataAccessObject.getPaperById(candidatePaperId);
                        recommendedPapers.add(recommendedPaper.toList());

                        user.addPaper(recommendedPaper);
                        // TODO: add categories to preferredCategories
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
                recommendedPapers.add(targetPaper.toList());
            }
        } else {
            // TODO: recommend papers based the saved ones
        }

        if (recommendedPapers.isEmpty()) {
            userPresenter.prepareFailView("Ops, no recommendations found...");
        } else {
            RecommendOutputData recommendOutputData = new RecommendOutputData(recommendedPapers);
            userPresenter.prepareSuccessView(recommendOutputData);
        }
    }

    private int getRelevanceFactor(String paperId) {
        ResearchPaper paper = this.userDataAccessObject.getPaperById(paperId);
        // TODO: to be completed
        return -1;
    }
}
