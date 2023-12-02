package data_access;

import entities.Category;
import entities.CategoryFactory;
import entities.User;

import java.io.*;
import java.util.*;

public class LocalPreferredCategoriesDataAccessObject {

    private final File preferredCategoriesCSVFile;
    private final Map<String, Integer> preferredCategoriesCSVFileHeader = new LinkedHashMap<>();
    private final Map<String, List<Category>> usersPreferredCategories = new LinkedHashMap<>();
    private final CategoryFactory categoryFactory;

    public LocalPreferredCategoriesDataAccessObject(String preferredCategoriesCSVFilePath,
                                                    CategoryFactory categoryFactory) throws IOException {
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

    public boolean savePreferredCategory(String username, Category category) {
        if (this.usersPreferredCategories.containsKey(username)) {
            this.usersPreferredCategories.get(username).add(category);
            return true;
        } else {
            return false;
        }
    }

    public void writeToDatabase(String username, List<Category> preferredCategories) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.preferredCategoriesCSVFile));

            StringBuilder mutablePreferredCategoriesStringRep = new StringBuilder();
            for (Category category : preferredCategories) {
                mutablePreferredCategoriesStringRep.append(category.toString()).append(" ");
            }

            String line = String.format("%s,%s", username, mutablePreferredCategoriesStringRep);
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToDatabase(File dest) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(dest));
            writer.write(String.join(",", this.preferredCategoriesCSVFileHeader.keySet()));
            writer.newLine();

            for (String username : this.usersPreferredCategories.keySet()) {
                StringBuilder mutablePreferredCategoriesStringRep = new StringBuilder();
                for (Category category : this.usersPreferredCategories.get(username)) {
                    mutablePreferredCategoriesStringRep.append(category.toString()).append(" ");
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
