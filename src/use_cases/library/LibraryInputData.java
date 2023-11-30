package use_cases.library;

public class LibraryInputData {
    private String userName;

    public LibraryInputData(String userName){
        this.userName = userName;
    }
    public String GetUserName(){
        return userName;
    }
    public void SetUserName(String userName){
        this.userName = userName;
    }
}
