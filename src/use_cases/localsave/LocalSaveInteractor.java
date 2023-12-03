package use_cases.localsave;

public class LocalSaveInteractor implements LocalSaveInputBoundary {
    final LocalSaveDataAccessInterface localSaveDataAccessObject;

    public LocalSaveInteractor(LocalSaveDataAccessInterface localSaveDataAccessObject) {
        this.localSaveDataAccessObject = localSaveDataAccessObject;

    }
    public void execute(LocalSaveInputData localSaveInputData) {
        localSaveDataAccessObject.localSave(localSaveInputData.getPaperUrl(), localSaveInputData.getPaperName());
    }
}
