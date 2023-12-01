package interface_adapters.library;

import use_cases.showpdf.ShowPdfInputBoundary;
import use_cases.showpdf.ShowPdfInputData;

public class LibraryController {
    final ShowPdfInputBoundary showPdfUseCaseInteractor;
    public LibraryController(ShowPdfInputBoundary showPdfUseCaseInteractor) {
        this.showPdfUseCaseInteractor = showPdfUseCaseInteractor;
    }


    public void execute(String url) {
        ShowPdfInputData pdfInputData = new ShowPdfInputData(
                url);

        showPdfUseCaseInteractor.execute(pdfInputData);
    }
}
