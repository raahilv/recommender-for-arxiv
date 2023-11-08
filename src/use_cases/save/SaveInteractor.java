package use_cases.save;

import entities.ResearchPaper;
import entities.User;
import interface_adapeters.save.SavePresenter;

public class SaveInteractor implements SaveInputBoundary{
    final SaveDataAccessInterface savedataAccessObject;
    final SaveOutputBoundary savePresenter;

    public SaveInteractor(SaveDataAccessInterface savedataAccessObject, SaveOutputBoundary savePresenter) {
        this.savedataAccessObject = savedataAccessObject;
        this.savePresenter = savePresenter;
    }

    public void execute(SaveInputData saveInputData) {
        String userName = saveInputData.getUserName();
        String paperId = saveInputData.getPaperId();
        if (savedataAccessObject.getUser(userName).getLibrary().containsKey(paperId)) {
            savePresenter.prepareFailView("Error: Paper already saved.");
        } else {
            ResearchPaper paper = savedataAccessObject.getPaper(paperId);
            savedataAccessObject.getUser(userName).getLibrary().put(paper.getId(), paper);
            SaveOutputData saveOutputData = new SaveOutputData(paperId);
            savePresenter.prepareSuccessView(saveOutputData);
        }
    }
}
