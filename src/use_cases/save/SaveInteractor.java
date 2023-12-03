package use_cases.save;

import entities.ResearchPaper;

public class SaveInteractor implements SaveInputBoundary{
    final SaveDataAccessInterface saveDataAccessObject;
    final SaveOutputBoundary savePresenter;

    /**
     * Constructor for interactor for save use case.
     * @param saveDataAccessObject data access object for save use case
     * @param savePresenter presenter for save use case
     */
    public SaveInteractor(SaveDataAccessInterface saveDataAccessObject, SaveOutputBoundary savePresenter) {
        this.saveDataAccessObject = saveDataAccessObject;
        this.savePresenter = savePresenter;
    }

    /**
     * Execute the Save use case, saving the paper in the user's library.
     * Call the presenter to prepare successful view or fail view.
     * @param saveInputData input data for Save use case
     */
    @Override
    public void execute(SaveInputData saveInputData) {
        String userName = saveInputData.getUserName();
        String paperId = saveInputData.getPaperId();
        if (saveDataAccessObject.getUser(userName).getLibrary().containsKey(paperId)) {
            savePresenter.prepareFailView("Error: Paper already saved.");
        } else {
            ResearchPaper paper = saveDataAccessObject.getPaper(paperId);
            saveDataAccessObject.save(userName, paper);
            SaveOutputData saveOutputData = new SaveOutputData(paperId);
            savePresenter.prepareSuccessView(saveOutputData);
        }
    }
}
