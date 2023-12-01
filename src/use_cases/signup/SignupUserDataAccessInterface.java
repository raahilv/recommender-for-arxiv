package use_cases.signup;

import entities.User;

public interface SignupUserDataAccessInterface {
    boolean existsByUsername(String username);
    void save(User user);
}
