package use_cases.login;

import entities.User;

public interface LoginUserDataAccessInterface {
    boolean existsByUsername(String identifier);

    void save(User user);

    User get(String username);
}
