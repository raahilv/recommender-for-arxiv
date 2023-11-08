package use_cases.recommend;

public interface RecommendOutputBoundary {

    void prepareSuccessView(RecommendOutputData recommendOutputData);
    void prepareFailView(String error);

}
