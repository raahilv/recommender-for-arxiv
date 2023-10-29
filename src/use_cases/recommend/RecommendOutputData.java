package use_cases.recommend;

import java.util.ArrayList;
import java.util.List;

public class RecommendOutputData {

    private List<String> recommendedPapers = new ArrayList<>();  // only paper IDs are saved

    public RecommendOutputData(List<String> recommendedPapers) {
        this.recommendedPapers.addAll(recommendedPapers);
    }

}
