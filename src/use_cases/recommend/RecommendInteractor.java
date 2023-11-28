package use_cases.recommend;

import entities.*;
import java.util.ArrayList;
import java.util.List;

public class RecommendInteractor implements RecommendInputBoundary {

    static final int THRESHOLD = 1;  // TODO: to be polished
    final RecommendDataAccessInterface userDataAccessObject;
    final RecommendOutputBoundary userPresenter;
    final CategoryFactory categoryFactory;
    final PreferenceDataFactory preferenceDataFactory;

    public RecommendInteractor(RecommendDataAccessInterface userDataAccessObject, RecommendOutputBoundary userPresenter,
                               CategoryFactory categoryFactory, PreferenceDataFactory preferenceDataFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.categoryFactory = categoryFactory;
        this.preferenceDataFactory = preferenceDataFactory;
    }

    public void execute(RecommendInputData recommendInputData) {
        String username = recommendInputData.getUsername();
        List<ResearchPaper> recommendedPapers = new ArrayList<>();

        if (!this.userDataAccessObject.existsByUsername(username)) {
            this.userPresenter.prepareFailView("ERROR: user *" + username + "* does not exist.");
        } else if (recommendInputData.wantAutoRecommendation()) {
            recommendedPapers.addAll(
                    recommend(
                            this.preferenceDataFactory.createWithObject(
                                    username,
                                    this.userDataAccessObject.getUser(username).getPreferredCategories(),
                                    recommendInputData.prioritizeSubcategorySearch(),
                                    recommendInputData.prioritizeUpvotePercentageSearch(),
                                    recommendInputData.wantAutoRecommendation()
                            )
                    )
            );
        } else {
            List<List<String>> preferredCategories = recommendInputData.getPreferenceData();
            boolean prioritizeSubcategorySearch = recommendInputData.prioritizeSubcategorySearch();
            boolean prioritizeUpvotePercentageSearch = recommendInputData.prioritizeUpvotePercentageSearch();
            boolean wantAutoRecommend = recommendInputData.wantAutoRecommendation();
            recommendedPapers.addAll(
                    recommend(
                            this.preferenceDataFactory.createWithRawData(
                                    username, preferredCategories,
                                    prioritizeSubcategorySearch,
                                    prioritizeUpvotePercentageSearch,
                                    wantAutoRecommend
                            )
                    )
            );
        }

        if (recommendedPapers.isEmpty()) {
            userPresenter.prepareFailView("Ops, no recommendations found...");
        } else {
            RecommendOutputData recommendOutputData = new RecommendOutputData(toList(recommendedPapers));
            userPresenter.prepareSuccessView(recommendOutputData);
        }
    }

    private List<ResearchPaper> recommend(PreferenceData preferenceData) {
        List<ResearchPaper> recommendedPapers = new ArrayList<>();

        for (Category category : preferenceData.getPreferredCategories()) {
            List<String> potentialPapers =
                    this.userDataAccessObject.filterPapersByRootCategory(category.getRootCategory());
            for (String potentialPaper : potentialPapers) {
                if (isGoodMatch(potentialPaper, preferenceData)) {
                    recommendedPapers.add(
                            this.userDataAccessObject.getPaperById(potentialPaper)
                    );
                }
            }
        }
        return recommendedPapers;
    }

    private boolean isGoodMatch(String paperId, PreferenceData preferenceData) {
        return getMatchScore(paperId, preferenceData) >= THRESHOLD;
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
    private double getMatchScore(String paperId, PreferenceData preferenceData) {
        List<Category> preferredCategories = preferenceData.getPreferredCategories();
        boolean prioritizeCategorySearch = preferenceData.prioritizeSubcategorySearch();
        boolean prioritizeUpvotePercentageSearch = preferenceData.prioritizeUpvotePercentageSearch();
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

    private List<List<Object>> toList(List<ResearchPaper> papers) {
        List<List<Object>> reformatted = new ArrayList<>();
        for (ResearchPaper paper : papers) {
            reformatted.add(paper.toList());
        }
        return reformatted;
    }

    public String toString(List<ResearchPaper> papers) {
        StringBuilder mutablePapersStringRep = new StringBuilder();
        for (ResearchPaper paper : papers) {
            mutablePapersStringRep.append(paper.toString()).append(" ");
        }
        mutablePapersStringRep.deleteCharAt(mutablePapersStringRep.length() - 1);
        return mutablePapersStringRep.toString();
    }

}
