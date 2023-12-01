package interface_adapters.RecommendHome;

import interface_adapters.ViewManagerModel;
import interface_adapters.login.LoginState;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupState;
import interface_adapters.signup.SignupViewModel;
import use_cases.login.LoginOutputBoundary;
import use_cases.login.LoginOutputData;
import use_cases.signup.SignupOutputBoundary;
import use_cases.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecommendHomePresenter implements LoginOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RecommendHomeViewModel recommendHomeViewModel;
    private final LoginViewModel loginViewModel;

    public RecommendHomePresenter(ViewManagerModel viewManagerModel,
                           RecommendHomeViewModel recommendHomeViewModel,
                                  LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recommendHomeViewModel = recommendHomeViewModel;
        this.loginViewModel = loginViewModel;
    }
    @Override
    public void prepareSuccessView(LoginOutputData user) {
        /*
            Prepares recommend home view after a successful login
         */

        RecommendHomeState recommendHomeState = recommendHomeViewModel.getState();
        recommendHomeState.setUsername(user.getUsername());
        this.recommendHomeViewModel.setState(recommendHomeState);
        recommendHomeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(recommendHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        /*
        Sends a warning in case the login fails
         */
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
