package interface_adapters.save;

import use_cases.save.SaveInputBoundary;
import use_cases.save.SaveInputData;

public class SaveController {
    final SaveInputBoundary saveInteractor;

    /**
     * Constructor for controller for Save use case.
     * @param saveInteractor the interactor used for executing the use case
     */
    public SaveController(SaveInputBoundary saveInteractor) {
        this.saveInteractor = saveInteractor;
    }

    /**
     * Execute the Save use case, with inputs userName and paperId.
     * @param userName username of the user who wants to save the paper
     * @param paperId paper id for the paper that is wanted to be saved
     */
    public void execute(String userName, String paperId) {
        SaveInputData saveInputData = new SaveInputData(userName, paperId);
        saveInteractor.execute(saveInputData);
    }
}
