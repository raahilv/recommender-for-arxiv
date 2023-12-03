package data_access;

import entities.User;
import use_cases.signup.SignupUserDataAccessInterface;

public class SignupDataAccessObject implements SignupUserDataAccessInterface {

    private final LocalUserDataAccessObject uDAO;

    public SignupDataAccessObject(LocalUserDataAccessObject uDAO) {
        this.uDAO = uDAO;
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.uDAO.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        this.uDAO.saveToDatabase(user);
    }

}
