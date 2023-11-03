package use_cases.recommend;

import entities.Category;
import entities.ResearchPaper;

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

    public void execute(RecommendInputData recommendInputData) {
        String username = recommendInputData.getUsername();
        List<List<Object>> recommendedPapers = new ArrayList<>();

        if (!this.userDataAccessObject.existsByUsername(username)) {
            this.userPresenter.prepareFailView("ERROR: user *" + username + "* does not exist.");
        } else if (recommendInputData.wantAutoRecommendMode()) {
            recommendedPapers.addAll(
                    recommend(this.userDataAccessObject.getUser(username).getPreferredCategories())
            );
        } else {
            recommendedPapers.addAll(
                    recommend(recommendInputData.getPreferenceData())
            );
        }

        if (recommendedPapers.isEmpty()) {
            userPresenter.prepareFailView("Ops, no recommendations found...");
        } else {
            RecommendOutputData recommendOutputData = new RecommendOutputData(recommendedPapers);
            userPresenter.prepareSuccessView(recommendOutputData);
        }
    }

    private List<List<Object>> recommend(List<Category> preferredCategories) {
        List<List<Object>> recommendedPapers = new ArrayList<>();
        for (Category category : preferredCategories) {
            List<String> potentialPapers = this.userDataAccessObject.
                    filterPapersByRootCategory(category.getRootCategory());
            for (String potentialPaper : potentialPapers) {
                if (isGoodMatch(potentialPaper, preferredCategories)) {
                    recommendedPapers.add(
                            this.userDataAccessObject.getPaperById(potentialPaper).toList()
                    );
                }
            }
        }
        return recommendedPapers;
    }

    private boolean isGoodMatch(String paperId, List<Category> preferredCategories) {
        return getRelevanceFactor(paperId, preferredCategories) >= THRESHOLD;
    }

    /** Return the *relevance factor* of a paper with respect to the given preference data.
     * A relevance factor of 0 indicates that the paper is not a good match
     * */
    private double getRelevanceFactor(String paperId, List<Category> preferredCategories) {
        double relevanceFactor = 0;

        ResearchPaper paper = this.userDataAccessObject.getPaperById(paperId);
        boolean matchRootCategory = matchRootCategory(paper, preferredCategories);
        boolean matchAll = matchAll(paper, preferredCategories);

        if (matchRootCategory || matchAll) {
            relevanceFactor += getUpvotePercentage(paper.getUpvoteCount(), paper.getDownvoteCount());
            if (matchRootCategory) {
                // Step 1: paper matches with one of the root categories the user is looking for.
                relevanceFactor++;
            } else {
                // Step 2: paper matches in terms of both its root category and its subcategory.
                //         This is the most important one.
                relevanceFactor += 5;
            }
        }
        // TODO: to be completed...
        return relevanceFactor;
    }

    private boolean matchRootCategory(ResearchPaper paper, List<Category> preferredCategories) {
        Category paperCategory = paper.getCategory();
        for (Category category : preferredCategories) {
            if (category.hasSameRootCategory(paperCategory)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchAll(ResearchPaper paper, List<Category> preferredCategories) {
        for (Category category : preferredCategories) {
            if (category.isSame(paper.getCategory())) {
                return true;
            }
        }
        return false;
    }

    private double getUpvotePercentage(long upvoteCount, long downvoteCount) {
        return upvoteCount == downvoteCount ?
                1.0 : 100. * upvoteCount / (upvoteCount + downvoteCount);
    }

}
