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
        User user = saveInputData.getUser();
        ResearchPaper paper = saveInputData.getPaper();
        if (user.getLibrary().containsKey(paper.getId())) {
            savePresenter.prepareFailView("Error: Paper already saved.");
        } else {
            user.getLibrary().put(paper.getId(), paper);
            SaveOutputData saveOutputData = new SaveOutputData(paper.getId());
            savePresenter.prepareSuccessView(saveOutputData);
        }
    }
}
