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
        System.out.println(recommendInputData.getPreferenceData().get(0).get(1));
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
        System.out.println(recommendedPapers.size() + "sizeee");
//        if (recommendedPapers.isEmpty()) {
//            userPresenter.prepareFailView("Ops, no recommendations found...");
//        } else {
        for (ResearchPaper paper : recommendedPapers) {
            System.out.println(paper.getUrl());
        }
        RecommendOutputData recommendOutputData = new RecommendOutputData(toList(recommendedPapers));
        userPresenter.prepareSuccessView(recommendOutputData);
    }

    public List<ResearchPaper> recommend(PreferenceData preferenceData) {
        List<ResearchPaper> recommendedPapers = new ArrayList<>();
        System.out.println( preferenceData.getPreferredCategories().get(0) + "cat");
        List<String> potentialPaperIDs = new ArrayList<>();
        // System.out.println("PREFERREDCATEGORIES: " + preferenceData.getPreferredCategories().size());
        for (Category category : preferenceData.getPreferredCategories()) {
            List<String> current = this.userDataAccessObject.filterPapersByRootCategory(category);
            System.out.println("current.size() = " + current.size());
            potentialPaperIDs.addAll(current);
        }

        List<ResearchPaper> potentialPapers = new ArrayList<>();
        for (String ID : potentialPaperIDs) {
            potentialPapers.add(this.userDataAccessObject.getPaperById(ID));
        }

        System.out.println("POTENTIALPAPERS.size() = " + potentialPapers.size());
        for (ResearchPaper paper : potentialPapers) {
            if (isGoodMatch(paper, preferenceData)) {
                recommendedPapers.add(paper);
            }
        }

        return recommendedPapers;
    }

    public boolean isGoodMatch(ResearchPaper paperId, PreferenceData preferenceData) {
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
    public int getMatchScore(ResearchPaper paper, PreferenceData preferenceData) {
        List<Category> preferredCategories = preferenceData.getPreferredCategories();
        boolean prioritizeCategorySearch = preferenceData.prioritizeSubcategorySearch();
        boolean prioritizeUpvotePercentageSearch = preferenceData.prioritizeUpvotePercentageSearch();
        int matchScore = 0;

        int matchCount = getCategoryMatchCount(paper, preferredCategories);
        double upvotePercentage = getUpvotePercentage(paper.getUpvoteCount(), paper.getDownvoteCount());

        System.out.println("matchCount: " + matchCount);
        if (!prioritizeCategorySearch && !prioritizeUpvotePercentageSearch) {
            matchScore += matchCount + upvotePercentage;
        } else {
            if (prioritizeCategorySearch) {
                matchScore += 10 * matchCount;
            } else {
                matchScore += matchCount;
            }

            if (prioritizeUpvotePercentageSearch) {
                matchScore += 10 * upvotePercentage;
            } else {
                matchScore += upvotePercentage;
            }
        }

        return matchScore;
    }

    /**
     * Count the number of categories the given paper shares with the preferred categories.
     * */
    public int getCategoryMatchCount(ResearchPaper paper, List<Category> preferredCategories) {
        int count = 0;
        List<Category> paperCategories = paper.getCategories();
        for (Category paperCategory : paperCategories) {
            for (Category preferredCategory : preferredCategories) {
                if (paperCategory.getRootCategory().equals(preferredCategory.getRootCategory()) && paperCategory.getSubcategory().equals(preferredCategory.getSubcategory())) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Return the percentage of the number of upvotes with respect to the total number
     * of votes for a given paper. When the numbers of upvotes and downvotes are all
     * 0, the returned value is 1.0.
     * */
    public double getUpvotePercentage(long upvoteCount, long downvoteCount) {
        return upvoteCount == 0 && downvoteCount == 0 ?
                1.0 : 100.0 * upvoteCount / (upvoteCount + downvoteCount);
    }

    public List<List<String>> toList(List<ResearchPaper> papers) {
        List<List<String>> reformatted = new ArrayList<>();
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
