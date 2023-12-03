package data_access;

import entities.Category;
import entities.CategoryFactory;
import entities.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LocalPreferredCategoriesDataAccessObject {

    private static final String preferredCategoriesCSVFilePath = "test/test_files/categories.csv";
    private final File preferredCategoriesCSVFile;
    private final Map<String, Integer> preferredCategoriesCSVFileHeader = new LinkedHashMap<>();
    private final Map<String, List<Category>> usersPreferredCategories = new LinkedHashMap<>();
    private final CategoryFactory categoryFactory;

    public LocalPreferredCategoriesDataAccessObject(CategoryFactory categoryFactory) throws IOException {
        this.preferredCategoriesCSVFile = new File(preferredCategoriesCSVFilePath);
        this.preferredCategoriesCSVFileHeader.put("username", 0);
        this.preferredCategoriesCSVFileHeader.put("preferred_categories", 1);
        this.categoryFactory = categoryFactory;

        if (this.preferredCategoriesCSVFile.length() == 0) {
            writeToDatabase(this.preferredCategoriesCSVFile);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.preferredCategoriesCSVFile))) {
                String header = reader.readLine();
                assert header.equals("username,preferred_categories");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[this.preferredCategoriesCSVFileHeader.get("username")]);
                    String preferredCategoriesStringRep = col.length == 1 ?
                            "" : String.valueOf(col[this.preferredCategoriesCSVFileHeader.get("preferred_categories")]);

                    List<Category> preferredCategories = new ArrayList<>();
                    String[] preferredCategoriesBreakDown = preferredCategoriesStringRep.split("\\^");
                    for (String categoryStringRep : preferredCategoriesBreakDown) {
                        String[] categoryBreakDown = categoryStringRep.split("\\|");
                        if (!categoryBreakDown[0].equals("")) {
                            preferredCategories.add(this.categoryFactory.create(Arrays.asList(categoryBreakDown)));
                        }
                    }
                    this.usersPreferredCategories.put(username, preferredCategories);
                }
            }
        }
    }

    public List<Category> getPreferredCategories(String username) {
        return this.usersPreferredCategories.containsKey(username) ?
                this.usersPreferredCategories.get(username) : new ArrayList<>();
    }

    public void saveToDatabase(String username, Category category) {
        try {
            StringBuilder mutableCategoryStringRep = new StringBuilder(category.toString());
            mutableCategoryStringRep.deleteCharAt(0);
            mutableCategoryStringRep.deleteCharAt(mutableCategoryStringRep.length() - 1);
            String categoryStringRep = mutableCategoryStringRep.toString();

            List<String> rows = Files.readAllLines(Paths.get(preferredCategoriesCSVFilePath), StandardCharsets.UTF_8);
            boolean userHandled = false;
            for (int i = 0; i < rows.size() && !userHandled; i++) {
                String[] userCategories = rows.get(i).split(",");
                if (userCategories[0].equals(username)) {
                    if (userCategories.length == 2) {
                        String[] savedCategories = userCategories[1].split("\\^");
                        boolean exists = false;
                        for (String savedCategory : savedCategories) {
                            if (savedCategory.equals(categoryStringRep)) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            String updatedUserCategories = username + "," + String.join("^", savedCategories) + "^" + categoryStringRep;
                            rows.set(i, updatedUserCategories);
                        }
                    } else if (userCategories.length == 1) {
                        String updatedUserLibrary = username + "," + categoryStringRep;
                        rows.set(i, updatedUserLibrary);
                    }
                    userHandled = true;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(preferredCategoriesCSVFile));
            for (String row : rows) {
                writer.write(row);
                writer.newLine();
            }
            writer.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void writeToDatabase(String username, List<Category> preferredCategories) {
        for (Category category : preferredCategories) {
            saveToDatabase(username, category);
        }
    }

    public void writeToDatabase(File dest) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(dest));
            writer.write(String.join(",", this.preferredCategoriesCSVFileHeader.keySet()));
            writer.newLine();

            for (String username : this.usersPreferredCategories.keySet()) {
                StringBuilder mutablePreferredCategoriesStringRep = new StringBuilder();
                for (Category category : this.usersPreferredCategories.get(username)) {
                    StringBuilder mutableCategoryStringRep = new StringBuilder(category.toString());
                    mutableCategoryStringRep.deleteCharAt(0);
                    mutableCategoryStringRep.deleteCharAt(mutableCategoryStringRep.length() - 1);
                    mutablePreferredCategoriesStringRep.append(mutableCategoryStringRep).append("^");
                }

                if (mutablePreferredCategoriesStringRep.length() > 0) {
                    mutablePreferredCategoriesStringRep.deleteCharAt(mutablePreferredCategoriesStringRep.length() - 1);
                }

                String preferredCategories = mutablePreferredCategoriesStringRep.toString();

                String line = String.format("%s,%s", username, preferredCategories);
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
