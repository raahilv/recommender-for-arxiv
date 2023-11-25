package entities;


public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String password);
}
