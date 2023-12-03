package interface_adapters.login;

import interface_adapters.RecommendHome.RecommendHomeState;
import interface_adapters.RecommendHome.RecommendHomeViewModel;
import interface_adapters.ViewManagerModel;
import use_cases.login.LoginOutputBoundary;
import use_cases.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final RecommendHomeViewModel recommendHomeViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          RecommendHomeViewModel recommendHomeViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recommendHomeViewModel = recommendHomeViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the recommend home view.

        RecommendHomeState recommendHomeState = recommendHomeViewModel.getState();
        recommendHomeState.setUsername(response.getUsername());
        this.recommendHomeViewModel.setState(recommendHomeState);
        this.recommendHomeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(recommendHomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}