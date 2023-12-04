package interface_adapters.localsave;

import use_cases.localsave.LocalSaveInputBoundary;
import use_cases.localsave.LocalSaveInputData;

public class LocalSaveController {
    /**
     * Controller for local save use case. It's called in RecommendPaperView.
     */
    final LocalSaveInputBoundary localSaveInteractor;

    public LocalSaveController(LocalSaveInputBoundary localSaveInteractor){
        this.localSaveInteractor = localSaveInteractor;
    }
    public void execute(String paperUrl, String paperName){
        /*
        Creates local save input data with collected paperUrl and paperName from RecommendPaperView
        Passes the created local save input data to interactor's execute method.
         */
        LocalSaveInputData localSaveInputData = new LocalSaveInputData(paperUrl, paperName);
        localSaveInteractor.execute(localSaveInputData);

    }

}
