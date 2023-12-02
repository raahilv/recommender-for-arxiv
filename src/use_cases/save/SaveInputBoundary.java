package use_cases.save;

public interface SaveInputBoundary {

    /**
     * Execute Save use case.
     * @param saveInputData input data for Save use case
     */
    void execute(SaveInputData saveInputData);
}
