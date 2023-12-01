package use_cases.localsave;

public class LocalSaveInputData { //
    public String paperUrl;
    public String paperName;

    public LocalSaveInputData(String paperUrl, String paperName){
        this.paperUrl = paperUrl;
        this.paperName = paperName;
    }
    public String getPaperUrl(){
        return this.paperUrl;
    }
    public String getPaperName(){
        return this.paperName;
    }
}
