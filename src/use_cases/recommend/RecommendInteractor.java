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

    /** The *relevance factor* of a paper is defined as follows:
     * (1) If the user prioritizes a search by the subcategory of the paper,
     *     then give a larger weight to the subcategory. TODO: to be modified
     * (2) If the user prioritize a search by the upvote percentage of the
     *     paper, then give a larger weight to the upvote percentage.  TODO: to be modified
     * */
    public void execute(RecommendInputData recommendInputData) {
        String username = recommendInputData.getUsername();
        List<List<Object>> recommendedPapers = new ArrayList<>();

        if (!this.userDataAccessObject.existsByUsername(username)) {
            this.userPresenter.prepareFailView("ERROR: user *" + username + "* does not exist.");
        } else if (recommendInputData.wantAutoRecommendMode()) {
            recommendedPapers.addAll(
                    recommend(
                            this.userDataAccessObject.getUser(username).getPreferredCategories(),
                            recommendInputData.prioritizeSubcategorySearch(),
                            recommendInputData.prioritizeUpvotePercentageSearch()
                    )
            );
        } else {
            recommendedPapers.addAll(
                    recommend(recommendInputData.getPreferenceData(),
                            recommendInputData.prioritizeSubcategorySearch(),
                            recommendInputData.prioritizeUpvotePercentageSearch()
                    )
            );
        }

        if (recommendedPapers.isEmpty()) {
            userPresenter.prepareFailView("Ops, no recommendations found...");
        } else {
            RecommendOutputData recommendOutputData = new RecommendOutputData(recommendedPapers);
            userPresenter.prepareSuccessView(recommendOutputData);
        }
    }

    private List<List<Object>> recommend(List<Category> preferredCategories,
                                         boolean prioritizeSubcategorySearch,
                                         boolean prioritizeUpvotePercentageSearch) {
        List<List<Object>> recommendedPapers = new ArrayList<>();

        for (Category category : preferredCategories) {
            List<String> potentialPapers = this.userDataAccessObject.
                    filterPapersByRootCategory(category.getRootCategory());
            for (String potentialPaper : potentialPapers) {
                if (isGoodMatch(potentialPaper, preferredCategories,
                        prioritizeSubcategorySearch, prioritizeUpvotePercentageSearch)) {
                    recommendedPapers.add(
                            this.userDataAccessObject.getPaperById(potentialPaper).toList()
                    );
                }
            }
        }
        return recommendedPapers;
    }

    private boolean isGoodMatch(String paperId, List<Category> preferredCategories,
                                boolean prioritizeSubcategorySearch,
                                boolean prioritizeUpvotePercentageSearch) {
        return getMatchScore(
                paperId, preferredCategories, prioritizeSubcategorySearch, prioritizeUpvotePercentageSearch
        ) >= THRESHOLD;
    }

    /** Return the *match score* of a paper with respect to the given preference data.
     * A relevance factor of 0 indicates that the paper is not a good match
     * */
    private double getMatchScore(String paperId, List<Category> preferredCategories,
                                 boolean prioritizeSubcategorySearch,
                                 boolean prioritizeUpvotePercentageSearch) {
        /* TODO: Currently, the relevance factor is calculated solely based on whether a given
            paper's category (both root category and subcategory) matches with any of preferred
            categories the user provides. Later, other factors (such as whether a paper's author
            matches with the one the user is looking for) may be integrated here.
        */
        double matchScore = 0;

        ResearchPaper paper = this.userDataAccessObject.getPaperById(paperId);
        double adjustedMatchCount = adjust(getCategoryMatchCount(paper, preferredCategories));
        double upvotePercentage = getUpvotePercentage(paper.getUpvoteCount(), paper.getDownvoteCount());

        if (prioritizeSubcategorySearch) {
            if (adjustedMatchCount > 0) {
                matchScore += 10 * adjustedMatchCount;
            }
            matchScore += upvotePercentage;
        }

        if (prioritizeUpvotePercentageSearch) {
            matchScore += 10 * upvotePercentage;
            matchScore += adjustedMatchCount;
        }

        return matchScore;
    }

    private double adjust(int factor) {
        int scale = 1;
        int tempFactor = factor;
        while (tempFactor / 10 > 0) {
            scale *= 10;
            tempFactor /= 10;
        }

        return 1.0 * factor / scale;
    }

    private int getMatchRootCategoryCount(ResearchPaper paper, List<Category> preferredCategories) {
        int count = 0;
        Category paperCategory = paper.getCategory();
        for (Category category : preferredCategories) {
            if (category.hasSameRootCategory(paperCategory)) {
                count++;
            }
        }
        return count;
    }

    private int getCategoryMatchCount(ResearchPaper paper, List<Category> preferredCategories) {
        int count = 0;
        for (Category category : preferredCategories) {
            if (category.isSame(paper.getCategory())) {
                count++;
            }
        }
        return count;
    }

    private double getUpvotePercentage(long upvoteCount, long downvoteCount) {
        return upvoteCount == downvoteCount ?
                1.0 : 100.0 * upvoteCount / (upvoteCount + downvoteCount);
    }

}
