package use_cases.showpdf;

import LocalSaveDataAccess.ShowPdfDataAccessObject;
import org.junit.Test;
import use_cases.showpdf.ShowPdfInputData;
import use_cases.showpdf.ShowPdfInteractor;


public class TestShowPdf {
    ShowPdfDataAccessObject showPdfDataAccessObject = new ShowPdfDataAccessObject();
    ShowPdfInteractor showPdfInteractor = new ShowPdfInteractor(showPdfDataAccessObject);

    ShowPdfInputData showPdfInputData = new ShowPdfInputData("https://arxiv.org/pdf/2311.18000.pdf");

    @Test
    public void checkIfBrowserOpens() {
        /* Check if the interactor can open the browser with a sample pdf URL */
        try {
            showPdfInteractor.execute(showPdfInputData);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
        assert true;
    }
}