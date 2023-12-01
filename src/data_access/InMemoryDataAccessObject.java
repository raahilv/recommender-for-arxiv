package data_access;

import entities.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataAccessObject {

    private final Map<String, User> users = new HashMap<>();


    public void save(User user) {
        this.users.put(user.getUsername(), user);
    }

    public User get(String username) {
        return this.users.get(username);
    }

    public User getUser(String username) {
        return this.users.get(username);
    }

    public boolean existsByUsername(String username) {
        return this.users.containsKey(username);
    }

}
