package LocalSaveDataAccess;

import use_cases.showpdf.ShowPdfDataAccessInterface;

import java.awt.*;
import java.net.URI;

public class ShowPdfDataAccessObject implements ShowPdfDataAccessInterface {
    public void showPdf(String paperUrl) {
        /* Opens paper URL from the browser. If desktop.browse is not supported might throw exception */
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI pdf = new URI(paperUrl);
            desktop.browse(pdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
