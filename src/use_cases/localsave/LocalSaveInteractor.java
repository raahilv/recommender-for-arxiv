package use_cases.localsave;

public class LocalSaveInteractor implements LocalSaveInputBoundary {
    final LocalSaveDataAccessInterface localSaveDataAccessObject;

    public LocalSaveInteractor(LocalSaveDataAccessInterface localSaveDataAccessObject) {
        this.localSaveDataAccessObject = localSaveDataAccessObject;

    }
    public void execute(LocalSaveInputData localSaveInputData) {
        /*
        Calls local save data object's local save method with the paper url and paper name from the
        input data.
         */
        localSaveDataAccessObject.localSave(localSaveInputData.getPaperUrl(), localSaveInputData.getPaperName());
    }
}
