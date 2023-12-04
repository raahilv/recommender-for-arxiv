package interface_adapters.library;

import use_cases.showpdf.ShowPdfInputBoundary;
import use_cases.showpdf.ShowPdfInputData;
import use_cases.switchView.SwitchViewInputData;
import use_cases.switchView.SwitchViewInteractor;

public class LibraryController {
    final ShowPdfInputBoundary showPdfUseCaseInteractor;
    final SwitchViewInteractor switchViewInteractor;
    public LibraryController(ShowPdfInputBoundary showPdfUseCaseInteractor, SwitchViewInteractor switchViewInteractor) {

        this.showPdfUseCaseInteractor = showPdfUseCaseInteractor;
        this.switchViewInteractor = switchViewInteractor;
    }


    public void execute(String url) {
        /*
        Opens pdf of the research file
         */
        ShowPdfInputData pdfInputData = new ShowPdfInputData(
                url);

        showPdfUseCaseInteractor.execute(pdfInputData);
    }
    public void goBack(String viewName) {
        /*
        Goes back to previous view
         */
        SwitchViewInputData viewInputData = new SwitchViewInputData(
                viewName);

        switchViewInteractor.execute(viewInputData);
    }
}
