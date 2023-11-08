package use_cases.save;

public interface SaveOutputBoundary {
    void prepareSuccessView(SaveOutputData saveOutputData);
    void prepareFailView(String error);
}
