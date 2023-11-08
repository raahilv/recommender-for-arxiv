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
        } else if (recommendInputData.wantAutoRecommendation()) {
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
            List<String> potentialPapers =
                    this.userDataAccessObject.filterPapersByRootCategory(category.getRootCategory());
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
     * A match score of 0 indicates that the paper is not a good match.
     * ...............................................................................
     * The *match score* of a paper is defined as follows:
     * (1) Count the number of categories the given paper shares with the preferred categories;
     * (2) Adjust the result calculated from (1);
     * (3) Calculate the percentage of likes (upvote) a paper has;
     * (4) Give a weight of 10 for the search criterion the user selects (i.e., prioritize
     *     category search, prioritize upvote percentage search);
     * (5) The result from Step (4) is the match score.
     * */
    private double getMatchScore(String paperId, List<Category> preferredCategories,
                                 boolean prioritizeCategorySearch,
                                 boolean prioritizeUpvotePercentageSearch) {
        double matchScore = 0;

        ResearchPaper paper = this.userDataAccessObject.getPaperById(paperId);
        double adjustedMatchCount = adjust(getCategoryMatchCount(paper, preferredCategories));
        double upvotePercentage = getUpvotePercentage(paper.getUpvoteCount(), paper.getDownvoteCount());

        if (!prioritizeCategorySearch && !prioritizeUpvotePercentageSearch) {
            matchScore += adjustedMatchCount + upvotePercentage;
        } else if (prioritizeCategorySearch) {
            matchScore += 10 * adjustedMatchCount;
            matchScore += upvotePercentage;
        } else {
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

    /** Count the number of categories the given paper shares with the preferred categories. */
    private int getCategoryMatchCount(ResearchPaper paper, List<Category> preferredCategories) {
        int count = 0;
        List<Category> paperCategories = paper.getCategories();
        for (Category paperCategory : paperCategories) {
            for (Category preferredCategory : preferredCategories) {
                if (paperCategory.isSame(preferredCategory)) {
                    count++;
                }
            }
        }
        return count;
    }

    private double getUpvotePercentage(long upvoteCount, long downvoteCount) {
        return upvoteCount == downvoteCount ?
                1.0 : 100.0 * upvoteCount / (upvoteCount + downvoteCount);
    }

}
