package data_access_test;

import data_access.LocalPreferredCategoriesDataAccessObject;
import entities.Category;
import entities.CategoryFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LocalPreferredCategoriesDataAccessObjectTest {

    private static final String preferredCategoriesCSVFilePath = "test/test_files/categories.csv";
    private final CategoryFactory cf = new CategoryFactory();

    @Test
    public void testGetPreferredCategoriesWithExistingUser() {
        try {
            LocalPreferredCategoriesDataAccessObject lrcDAO = new LocalPreferredCategoriesDataAccessObject(preferredCategoriesCSVFilePath, cf);
            String username = "ege";
            List<Category> preferredCategories = lrcDAO.getPreferredCategories(username);
            assert (preferredCategories.size() == 4);
        } catch (IOException ioe) {
            System.out.println("IOException in testGetPreferredCategoriesWithExistingUser().");
        }

    }
}
