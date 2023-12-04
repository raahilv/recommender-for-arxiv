package use_cases.signupAndLogin;

import data_access.DataAccessFacade;
import entities.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupPresenter;
import interface_adapters.signup.SignupViewModel;
import org.junit.Test;
import use_cases.signup.SignupInputData;
import use_cases.signup.SignupInteractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestSignUp {

        DataAccessFacade signupDataAccessObject = FacadeConstructorForTesting.create();
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        SignupInteractor signupInteractor = new SignupInteractor(signupDataAccessObject, signupPresenter, commonUserFactory);

        SignupInputData signupInputData = new SignupInputData("random", "123", "123");
        SignupController signupController = new SignupController(signupInteractor);
    @Test
    public void SignupWritesToCsv(){
        signupController.execute("random", "123", "123");
        try (BufferedReader reader = new BufferedReader(new FileReader("test/test_files/users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the CSV line by comma
                String[] values = line.split(",");

                // Check if the target value exists in the array
                for (int i = 0; i < values.length; i++) {
                    if (values[i].trim().equals("random")) {
                        // Remove the target value from the array
                        values[i] = "";
                        assert true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert false;
    }
}
