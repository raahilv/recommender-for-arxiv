package use_cases.save;

import entities.ResearchPaper;

public class SaveInteractor implements SaveInputBoundary{
    final SaveDataAccessInterface savedataAccessObject;
    final SaveOutputBoundary savePresenter;

    /**
     * Constructor for interactor for save use case.
     * @param savedataAccessObject data access object for save use case
     * @param savePresenter presenter for save use case
     */
    public SaveInteractor(SaveDataAccessInterface savedataAccessObject, SaveOutputBoundary savePresenter) {
        this.savedataAccessObject = savedataAccessObject;
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
        if (savedataAccessObject.getUser(userName).getLibrary().containsKey(paperId)) {
            savePresenter.prepareFailView("Error: Paper already saved.");
        } else {
            ResearchPaper paper = savedataAccessObject.getPaper(paperId);
            savedataAccessObject.getUser(userName).getLibrary().put(paper.getID(), paper);
            SaveOutputData saveOutputData = new SaveOutputData(paperId);
            savePresenter.prepareSuccessView(saveOutputData);
        }
    }
}
