package LocalSaveDataAccess;

import use_cases.localsave.LocalSaveDataAccessInterface;
import use_cases.showpdf.ShowPdfDataAccessInterface;

import java.awt.*;
import java.net.URI;

public class ShowPdfDataAccessObject implements ShowPdfDataAccessInterface {
    public void showPdf(String paperUrl) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI pdf = new URI(paperUrl);
            desktop.browse(pdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
