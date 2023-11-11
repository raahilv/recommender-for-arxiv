package use_cases.login;

import entities.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
