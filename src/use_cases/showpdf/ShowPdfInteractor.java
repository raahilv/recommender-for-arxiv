package use_cases.showpdf;

import use_cases.localsave.LocalSaveDataAccessInterface;

public class ShowPdfInteractor implements ShowPdfInputBoundary {

    final ShowPdfDataAccessInterface showPdfDataAccessObject;

    public ShowPdfInteractor(ShowPdfDataAccessInterface showPdfDataAccessObject){
        this.showPdfDataAccessObject = showPdfDataAccessObject;
    }
    public void execute(ShowPdfInputData showPdfInputData){
        showPdfDataAccessObject.showPdf(showPdfInputData.getUrl());
    }

}
