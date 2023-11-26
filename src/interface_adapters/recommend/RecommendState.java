package interface_adapters.recommend;

import java.util.ArrayList;
import java.util.List;

public class RecommendState {
    private int recommendationCount;
    private List<List<Object>> recommendedPapers = new ArrayList<>();
//    Format:
//              id: String,
//              title: String,
//              categories: List<Category>,
//              publishDate: LocalDate,
//              paperAbstract: String,
//              journalReference: String,
//              url: String,
//              upvoteCount: int,
//              downvoteCount: int,
//              authors: List<Author>

    public RecommendState(RecommendState copy) {
        this.recommendationCount = copy.recommendationCount;
        this.recommendedPapers = copy.recommendedPapers;
    }

    public RecommendState() {
    }

    public int getRecommendationCount() {
        return recommendationCount;
    }

    public void setRecommendationCount(int recommendationCount) {
        this.recommendationCount = recommendationCount;
    }

    public List<List<Object>> getRecommendedPapers() {
        return recommendedPapers;
    }

    public void setRecommendedPapers(List<List<Object>> recommendedPapers) {
        this.recommendedPapers = recommendedPapers;
    }
}
