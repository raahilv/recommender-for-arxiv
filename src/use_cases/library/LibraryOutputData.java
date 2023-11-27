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

    public String getPaperID(int index) {
        if(index < paperID.size())
            return paperID.get(index);
        else
            return null;
    }

    public String getPaperName(int index) {
        if(index < paperName.size())
            return paperName.get(index);
        else
            return null;
    }
    public String getPaperPDF(int index) {
        if(index < paperPDF.size())
            return paperPDF.get(index);
        else
            return null;
    }


}
