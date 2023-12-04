package showpdf;

import LocalSaveDataAccess.ShowPdfDataAccessObject;
import interface_adapters.showpdf.ShowPdfController;
import org.junit.Test;
import use_cases.showpdf.ShowPdfInteractor;


public class TestShowPdf {
    ShowPdfDataAccessObject showPdfDataAccessObject = new ShowPdfDataAccessObject();
    ShowPdfInteractor showPdfInteractor = new ShowPdfInteractor(showPdfDataAccessObject);

    ShowPdfController showPdfController = new ShowPdfController(showPdfInteractor);

    @Test
    public void checkIfBrowserOpens() {
        String sampleUrl = "https://arxiv.org/pdf/2311.18000.pdf";
        try {
            showPdfController.execute(sampleUrl);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
        assert true;
    }
}