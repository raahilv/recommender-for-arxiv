package data_access;

import entities.User;
import use_cases.login.LoginUserDataAccessInterface;

public class LoginUserDataAccessObject implements LoginUserDataAccessInterface {

    private final LocalUserDataAccessObject uDAO;
    public LoginUserDataAccessObject(LocalUserDataAccessObject uDAO) {
        this.uDAO = uDAO;
    }

    @Override
    public boolean existsByUsername(String identifier) {
        return this.uDAO.existsByUsername(identifier);
    }

    @Override
    public void save(User user) {
        this.uDAO.saveToDatabase(user);
    }

    @Override
    public User get(String username) {
        return this.uDAO.get(username);
    }

}
