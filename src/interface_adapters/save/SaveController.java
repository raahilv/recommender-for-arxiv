package interface_adapters.save;

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
