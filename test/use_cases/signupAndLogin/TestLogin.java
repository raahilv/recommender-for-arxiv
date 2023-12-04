package use_cases.signupAndLogin;

import data_access.DataAccessFacade;
import interface_adapters.RecommendHome.RecommendHomeViewModel;
import interface_adapters.ViewManagerModel;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginPresenter;
import interface_adapters.login.LoginViewModel;
import org.junit.Test;
import use_cases.login.LoginInteractor;

import javax.swing.text.View;

public class TestLogin {

    LoginViewModel loginViewModel = new LoginViewModel();

    ViewManagerModel viewManagerModel = new ViewManagerModel();

    RecommendHomeViewModel recommendHomeViewModel = new RecommendHomeViewModel();

    LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, recommendHomeViewModel, loginViewModel);

    DataAccessFacade loginDataAccessObject = FacadeConstructorForTesting.create();

    LoginInteractor loginInteractor = new LoginInteractor(loginDataAccessObject, loginPresenter);

    LoginController loginController = new LoginController(loginInteractor);

    @Test
    public void checkIfLoginSuccessful() {
        loginController.execute("ozgen", "ozgenpwd");
        assert viewManagerModel.getActiveView().equals("recommend home");
    }
}