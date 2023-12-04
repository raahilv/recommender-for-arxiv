package interface_adapters.RecommendHome;

import interface_adapters.signup.SignupState;

public class RecommendHomeState {
    private String username = "";
    public RecommendHomeState(RecommendHomeState copy) {
        username = copy.username;
    }
    public RecommendHomeState() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
