package use_cases.showpdf;

public class ShowPdfInputData {
    /* Contains the paper's URL */
    public String paperUrl;
    public ShowPdfInputData(String paperUrl){
        this.paperUrl = paperUrl;
    }
    public String getUrl(){
        return this.paperUrl;
    }
}
