package interface_adapeters.save;

import entities.ResearchPaper;
import entities.User;
import use_cases.save.SaveInputBoundary;
import use_cases.save.SaveInputData;

public class SaveController {
    final SaveInputBoundary saveInteractor;

    public SaveController(SaveInputBoundary saveInteractor) {
        this.saveInteractor = saveInteractor;
    }

    public void execute(String userName, String paperId) {
        SaveInputData saveInputData = new SaveInputData(userName, paperId);
        saveInteractor.execute(saveInputData);
    }
}
