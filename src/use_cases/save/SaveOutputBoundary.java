package use_cases.save;

public interface SaveOutputBoundary {

    /**
     * Prepare the view when the paper is successfully saved.
     * @param saveOutputData the output data for Save use case
     */
    void prepareSuccessView(SaveOutputData saveOutputData);

    /**
     * Prepare the view when the paper is not successfully saved.
     * @param error the error message
     */
    void prepareFailView(String error);
}
