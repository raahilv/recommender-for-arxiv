package use_cases.save;

import entities.ResearchPaper;

public class SaveInteractor implements SaveInputBoundary{
    final SaveDataAccessInterface saveDataAccessObject;
    final SaveOutputBoundary savePresenter;

    public SaveInteractor(SaveDataAccessInterface saveDataAccessObject, SaveOutputBoundary savePresenter) {
        this.saveDataAccessObject = saveDataAccessObject;
        this.savePresenter = savePresenter;
    }

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
