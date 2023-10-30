package use_cases.save;

import entities.ResearchPaper;
import entities.User;

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
    }
}
