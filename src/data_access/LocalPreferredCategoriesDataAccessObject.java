package data_access;

import entities.Category;
import entities.User;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalPreferredCategoriesDataAccessObject {

    private final File preferredCategoriesCSVFile;
    private final Map<String, User> usersPreferredCategories = new HashMap<>();
    private final Map<String, Integer> preferredCategoriesCSVFileHeader = new HashMap<>();

    public LocalUserDataAccessObject(String preferredCategoriesCSVFilePath) {

    }

    public List<Category> getPreferredCategories(String username) {

    }

    public void savePreferredCategory(String username, Category category) {
        // TODO: to be implemented...
    }

}
