package data_access;

import data_access.LocalPreferredCategoriesDataAccessObject;
import entities.Category;
import entities.CategoryFactory;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LocalPreferredCategoriesDataAccessObjectTest {

    private static final String preferredCategoriesCSVFilePath = "test/test_files/categories.csv";
    private static final String emptyCategoriesFilePath = "test/test_files/emptyCategories.csv";
    private final CategoryFactory cf = new CategoryFactory();

    @Test
    public void testGetPreferredCategoriesWithExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(preferredCategoriesCSVFilePath));
            writer.write("username,preferred_categories");
            writer.newLine();
            writer.write("ege,r00|s00|null^r01|s01|null^r02|s02|null^r03|s03|null");
            writer.newLine();
            writer.write("kevin,r10|s10|null");
            writer.newLine();
            writer.write("jerry,r20|s20|null^r21|s21|null");
            writer.newLine();
            writer.write("ozgen,r30|s30|null^r31|s31|null^r32|s32|null");
            writer.newLine();
            writer.write("raahil,");
            writer.newLine();
            writer.close();

            LocalPreferredCategoriesDataAccessObject lrcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            String username = "ege";
            List<Category> preferredCategories = lrcDAO.getPreferredCategories(username);
            assert (preferredCategories.size() == 4);
        } catch (IOException ioe) {
            System.out.println("IOException in testGetPreferredCategoriesWithExistingUser().");
        }
    }

    @Test
    public void testGetPreferredCategoriesWithNonExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(preferredCategoriesCSVFilePath));
            writer.write("username,preferred_categories");
            writer.newLine();
            writer.write("ege,r00|s00|null^r01|s01|null^r02|s02|null^r03|s03|null");
            writer.newLine();
            writer.write("kevin,r10|s10|null");
            writer.newLine();
            writer.write("jerry,r20|s20|null^r21|s21|null");
            writer.newLine();
            writer.write("ozgen,r30|s30|null^r31|s31|null^r32|s32|null");
            writer.newLine();
            writer.write("raahil,");
            writer.newLine();
            writer.close();

            LocalPreferredCategoriesDataAccessObject lrcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            String username = "boris";
            List<Category> preferredCategories = lrcDAO.getPreferredCategories(username);
            assert (preferredCategories.isEmpty());
        } catch (IOException ioe) {
            System.out.println("IOException in testGetPreferredCategoriesWithNonExistingUser().");
        }
    }

    @Test
    public void testSaveToDatabaseWithExistingUserAndExistingCategory() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(preferredCategoriesCSVFilePath));
            writer.write("username,preferred_categories");
            writer.newLine();
            writer.write("ege,r00|s00|null^r01|s01|null^r02|s02|null^r03|s03|null");
            writer.newLine();
            writer.write("kevin,r10|s10|null");
            writer.newLine();
            writer.write("jerry,r20|s20|null^r21|s21|null");
            writer.newLine();
            writer.write("ozgen,r30|s30|null^r31|s31|null^r32|s32|null");
            writer.newLine();
            writer.write("raahil,");
            writer.newLine();
            writer.close();

            LocalPreferredCategoriesDataAccessObject lrcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            String username = "ege";
            Path path = Paths.get(preferredCategoriesCSVFilePath);
            String previousPreferredCategories = Files.readAllLines(path, StandardCharsets.UTF_8).get(1);

            List<String> categoryInfo = new ArrayList<>();
            categoryInfo.add("r03");
            categoryInfo.add("s03");
            categoryInfo.add("null");
            Category category = cf.create(categoryInfo);
            lrcDAO.saveToDatabase(username, category);

            String updatedPreferredCategories = Files.readAllLines(path, StandardCharsets.UTF_8).get(1);

            assert (updatedPreferredCategories.equals(previousPreferredCategories));
        } catch (IOException ioe) {
            System.out.println("IOException in testSavePreferredCategory().");
        }
    }


    @Test
    public void testSaveToDatabaseWithExistingUserAndNonExistingCategory() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(preferredCategoriesCSVFilePath));
            writer.write("username,preferred_categories");
            writer.newLine();
            writer.write("ege,r00|s00|null^r01|s01|null^r02|s02|null^r03|s03|null");
            writer.newLine();
            writer.write("kevin,r10|s10|null");
            writer.newLine();
            writer.write("jerry,r20|s20|null^r21|s21|null");
            writer.newLine();
            writer.write("ozgen,r30|s30|null^r31|s31|null^r32|s32|null");
            writer.newLine();
            writer.write("raahil,");
            writer.newLine();
            writer.close();

            LocalPreferredCategoriesDataAccessObject lrcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            String username = "raahil";
            String previousPreferredCategories = Files.readAllLines(Paths.get(preferredCategoriesCSVFilePath), StandardCharsets.UTF_8).get(5);

            List<String> categoryInfo = new ArrayList<>();
            categoryInfo.add("r03");
            categoryInfo.add("s03");
            categoryInfo.add("d03");
            Category category = cf.create(categoryInfo);
            lrcDAO.saveToDatabase(username, category);

            String updatedPreferredCategories = Files.readAllLines(Paths.get(preferredCategoriesCSVFilePath), StandardCharsets.UTF_8).get(5);
            System.out.println(updatedPreferredCategories);
            assert ((previousPreferredCategories + "r03|s03|null").equals(updatedPreferredCategories));
        } catch (IOException ioe) {
            System.out.println("IOException in testSavePreferredCategory().");
        }
    }

    @Test
    public void testWriteToDatabaseWithDestFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(preferredCategoriesCSVFilePath));
            writer.write("username,preferred_categories");
            writer.newLine();
            writer.write("ege,r00|s00|null^r01|s01|null^r02|s02|null^r03|s03|null");
            writer.newLine();
            writer.write("kevin,r10|s10|null");
            writer.newLine();
            writer.write("jerry,r20|s20|null^r21|s21|null");
            writer.newLine();
            writer.write("ozgen,r30|s30|null^r31|s31|null^r32|s32|null");
            writer.newLine();
            writer.write("raahil,");
            writer.newLine();
            writer.close();

            File dest = new File(emptyCategoriesFilePath);
            List<String> categoriesContents = Files.readAllLines(Paths.get(preferredCategoriesCSVFilePath), StandardCharsets.UTF_8);
            LocalPreferredCategoriesDataAccessObject lrcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            lrcDAO.writeToDatabase(dest);
            List<String> emptyCategoriesContents = Files.readAllLines(Paths.get(emptyCategoriesFilePath), StandardCharsets.UTF_8);


            assert (categoriesContents.equals(emptyCategoriesContents));

        } catch (IOException ioe) {
            System.out.println("IOExceptioin in testWriteToDatabaseWithDestFile().");
        }
    }

    @Test
    public void testWriteToDatabaseWithExistingUsername() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(preferredCategoriesCSVFilePath));
            writer.write("username,preferred_categories");
            writer.newLine();
            writer.write("ege,r00|s00|null^r01|s01|null^r02|s02|null^r03|s03|null");
            writer.newLine();
            writer.write("kevin,r10|s10|null");
            writer.newLine();
            writer.write("jerry,r20|s20|null^r21|s21|null");
            writer.newLine();
            writer.write("ozgen,r30|s30|null^r31|s31|null^r32|s32|null");
            writer.newLine();
            writer.write("raahil,");
            writer.newLine();
            writer.close();

            String[] categoryInfo = {"r30", "s30", "d30"};
            List<Category> categories = new ArrayList<>();
            categories.add(cf.create(Arrays.asList(categoryInfo)));

            Path path = Paths.get(preferredCategoriesCSVFilePath);
            List<String> previousFileContents = Files.readAllLines(path, StandardCharsets.UTF_8);
            LocalPreferredCategoriesDataAccessObject lrcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            lrcDAO.writeToDatabase("ozgen", categories);
            List<String> updatedFileContents = Files.readAllLines(path, StandardCharsets.UTF_8);

            assert (previousFileContents.equals(updatedFileContents));
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabaseWithExistingUsername().");
        }
    }

    @Test
    public void testWriteToDatabaseWithNonExistingUsername() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(preferredCategoriesCSVFilePath));
            writer.write("username,preferred_categories");
            writer.newLine();
            writer.write("ege,r00|s00|null^r01|s01|null^r02|s02|null^r03|s03|null");
            writer.newLine();
            writer.write("kevin,r10|s10|null");
            writer.newLine();
            writer.write("jerry,r20|s20|null^r21|s21|null");
            writer.newLine();
            writer.write("ozgen,r30|s30|null^r31|s31|null^r32|s32|null");
            writer.newLine();
            writer.write("raahil,");
            writer.newLine();
            writer.close();

            String[] category40Info = {"r40", "s40", "d40"};
            String[] category41Info = {"r41", "s41", "d41"};
            List<Category> categories = new ArrayList<>();
            categories.add(cf.create(Arrays.asList(category40Info)));
            categories.add(cf.create(Arrays.asList(category41Info)));

            String username = "boris";
            Path path = Paths.get(preferredCategoriesCSVFilePath);
            List<String> previousFileContents = Files.readAllLines(path, StandardCharsets.UTF_8);
            LocalPreferredCategoriesDataAccessObject lrcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            lrcDAO.writeToDatabase(username, categories);
            List<String> updatedFileContents = Files.readAllLines(path, StandardCharsets.UTF_8);

            boolean foundBoris = false;
            for (String line : updatedFileContents) {
                if (line.split(",")[0].equals("boris")) {
                    foundBoris = true;
                    break;
                }
            }

            assert (previousFileContents.equals(updatedFileContents) && !foundBoris);
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabaseWithNonExistingUsername().");
        }
    }

}
