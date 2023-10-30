package use_cases.recommend;

import java.util.ArrayList;
import java.util.List;

public class RecommendOutputData {

    private final int paperCount;
    private final List<List<Object>> recommendedPapers = new ArrayList<>();

    public RecommendOutputData(List<List<Object>> recommendedPapers) {
        this.paperCount = recommendedPapers.size();
        this.recommendedPapers.addAll(recommendedPapers);
    }

    public int getPaperCount() {
        return this.paperCount;
    }

    public List<List<Object>> getRecommendedPapers() {
        return this.recommendedPapers;
    }

}
