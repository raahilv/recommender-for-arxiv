package use_cases.recommend;

public interface RecommendOutputBoundary {

    void prepareSuccessView(RecommendOutputData clearOutputData);
    void prepareFailView(String error);

}
