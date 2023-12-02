package interface_adapters.localsave;

import use_cases.localsave.LocalSaveInputBoundary;
import use_cases.localsave.LocalSaveInputData;

public class LocalSaveController {
    final LocalSaveInputBoundary localSaveInteractor;

    public LocalSaveController(LocalSaveInputBoundary localSaveInteractor){
        this.localSaveInteractor = localSaveInteractor;
    }
    public void execute(String paperUrl, String paperName){
        LocalSaveInputData localSaveInputData = new LocalSaveInputData(paperUrl, paperName);
        localSaveInteractor.execute(localSaveInputData);
    }

}
