package use_cases.recommend;

import java.util.ArrayList;
import java.util.List;

public class RecommendOutputData {

    private final int recommendationCount;
    private final List<List<String>> recommendedPapers = new ArrayList<>();

    public RecommendOutputData(List<List<String>> recommendedPapers) {
        this.recommendationCount = recommendedPapers.size();
        this.recommendedPapers.addAll(recommendedPapers);
    }

    public int getRecommendationCount() {
        return this.recommendationCount;
    }

    public List<List<String>> getRecommendedPapers() {
        return this.recommendedPapers;
    }

}
