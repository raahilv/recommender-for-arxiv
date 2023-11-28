package data_access;

import entities.User;
import entities.UserFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LocalUserDataAccessObject {

    private final File usersCSVFile;
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Integer> usersCSVFileHeader = new HashMap<>();
    private final UserFactory userFactory;

    public LocalUserDataAccessObject(String usersCSVFilePath, UserFactory userFactory) {
        this.userFactory = userFactory;

        this.usersCSVFile = new File(usersCSVFilePath);
        this.usersCSVFileHeader.put("username", 0);
        this.usersCSVFileHeader.put("password", 1);
        this.usersCSVFileHeader.put("preferred_categories", 2);  // TODO: add toString to User.java
        // TODO: save library in another CSV file

    }

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
