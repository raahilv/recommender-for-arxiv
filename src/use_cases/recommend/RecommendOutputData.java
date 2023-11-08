package use_cases.recommend;

import java.util.ArrayList;
import java.util.List;

public class RecommendOutputData {

    private final int recommendationCount;
    private final List<List<Object>> recommendedPapers = new ArrayList<>();

    public RecommendOutputData(List<List<Object>> recommendedPapers) {
        this.recommendationCount = recommendedPapers.size();
        this.recommendedPapers.addAll(recommendedPapers);
    }

    public int getRecommendationCount() {
        return this.recommendationCount;
    }

    public List<List<Object>> getRecommendedPapers() {
        return this.recommendedPapers;
    }

}
