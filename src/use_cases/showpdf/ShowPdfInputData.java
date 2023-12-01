package use_cases.showpdf;

public class ShowPdfInputData {
    public String paperUrl;
    public ShowPdfInputData(String paperUrl){
        this.paperUrl = paperUrl;
    }
    public String getUrl(){
        return this.paperUrl;
    }
} //
