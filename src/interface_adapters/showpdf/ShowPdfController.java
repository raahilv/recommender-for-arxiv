package interface_adapters.showpdf;

import use_cases.showpdf.ShowPdfInputBoundary;
import use_cases.showpdf.ShowPdfInputData;

public class ShowPdfController {
    final ShowPdfInputBoundary showPdfInteractor;

    public ShowPdfController(ShowPdfInputBoundary showPdfInteractor){
        this.showPdfInteractor = showPdfInteractor;
    }
    public void execute(String paperUrl){
        ShowPdfInputData showPdfInputData = new ShowPdfInputData(paperUrl);
        showPdfInteractor.execute(showPdfInputData);
    }
}