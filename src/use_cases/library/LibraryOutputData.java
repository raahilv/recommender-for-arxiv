package use_cases.library;

import java.util.List;

public class LibraryOutputData {
    private List<String> paperID;
    private List<String> paperName;
    private List<String> paperPDF;

    public void addID(String id){
        paperID.add(id);
    }
    public void addPaperName(String paperName){
        this.paperName.add(paperName);
    }
    public void addPaperPDF(String linkPDF){
        paperPDF.add(linkPDF);
    }

    public List<String> getPaperName() {
        return paperName;
    }

    public List<String> getPaperID() {
        return paperID;
    }

    public List<String> getPaperPDF() {
        return paperPDF;
    }
}
