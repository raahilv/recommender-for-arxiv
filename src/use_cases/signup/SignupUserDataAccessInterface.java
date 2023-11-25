package use_cases.signup;

import entities.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
