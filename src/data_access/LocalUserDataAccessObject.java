package data_access;

import entities.*;

import java.io.*;
import java.util.*;

public class LocalUserDataAccessObject {

    private final LocalLibraryDataAccessObject localLibraryDAO;
    private final LocalUpvotedPapersDataAccessObject localUpvotedPapersDAO;
    private final LocalDownvotedPapersDataAccessObject localDownvotedPapersDAO;
    private final LocalPreferredCategoriesDataAccessObject localPreferredCategoriesDAO;
    private final File usersCSVFile;
    private final Map<String, User> users = new LinkedHashMap<>();  // username : username object
    private final Map<String, Integer> usersCSVFileHeader = new LinkedHashMap<>();
    private final UserFactory userFactory;

    public LocalUserDataAccessObject(LocalLibraryDataAccessObject localLibraryDAO,
                                     LocalUpvotedPapersDataAccessObject localUpvotedPapersDAO,
                                     LocalDownvotedPapersDataAccessObject localDownvotedPapersDAO,
                                     LocalPreferredCategoriesDataAccessObject localPreferredCategoriesDAO,
                                     String usersCSVFilePath, UserFactory userFactory) throws IOException {
        this.localLibraryDAO = localLibraryDAO;
        this.localUpvotedPapersDAO = localUpvotedPapersDAO;
        this.localDownvotedPapersDAO = localDownvotedPapersDAO;
        this.localPreferredCategoriesDAO = localPreferredCategoriesDAO;
        this.usersCSVFile = new File(usersCSVFilePath);
        this.userFactory = userFactory;

        this.usersCSVFileHeader.put("username", 0);
        this.usersCSVFileHeader.put("password", 1);

        if (this.usersCSVFile.length() == 0) {
            writeToDatabase();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.usersCSVFile))) {
                String header = reader.readLine();
                assert header.equals("username,password");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[this.usersCSVFileHeader.get("username")]);
                    String password = String.valueOf(col[this.usersCSVFileHeader.get("password")]);
                    List<Category> preferredCategories =
                            this.localPreferredCategoriesDAO.getPreferredCategories(username);
                    List<ResearchPaper> userLibrary = this.localLibraryDAO.getLibrary(username);
                    List<ResearchPaper> upvotedPapers = this.localUpvotedPapersDAO.getUpvotedPapers(username);
                    List<ResearchPaper> downvotedPapers = this.localDownvotedPapersDAO.getDownvotedPapers(username);

                    User user = this.userFactory.create(username, password);
                    for (Category preferredCategory : preferredCategories) {
                        user.addPreferredCategories(preferredCategory);
                    }
                    for (ResearchPaper savedPaper : userLibrary) {
                        user.savePaperIntoLibrary(savedPaper);
                    }
                    for (ResearchPaper upvotedPaper : upvotedPapers) {
                        user.addUpvotedPapers(upvotedPaper);
                    }
                    for (ResearchPaper downvotedPaper : downvotedPapers) {
                        user.addDownvotedPapers(downvotedPaper);
                    }
                    this.users.put(username, user);
                }
            }
        }

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

    private void writeToDatabase() {
        /* When using this method, there are no users in the CSV file, so we can safely assume
        * that each username written to the CSV file by this method is unique. */
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.usersCSVFile));
            writer.write(String.join(",", this.usersCSVFileHeader.keySet()));
            writer.newLine();

            for (User user : this.users.values()) {
                String username = user.getUsername();
                String password = user.getPassword();
                Map<String, ResearchPaper> userLibrary = user.getLibrary();
                List<Category> preferredCategories = user.getPreferredCategories();
                Map<String, ResearchPaper> upvotedPapers = user.getUpvotedPapers();
                Map<String, ResearchPaper> downvotedPapers = user.getDownvotedPapers();

                String line = String.format("%s,%s", username, password);
                writer.write(line);
                writer.newLine();

                this.localLibraryDAO.writeToDatabase(
                        username, new ArrayList<>(userLibrary.keySet())
                );
                this.localPreferredCategoriesDAO.writeToDatabase(
                        username, preferredCategories
                );
                this.localUpvotedPapersDAO.writeToDatabase(
                        username, new ArrayList<>(upvotedPapers.keySet())
                );
                this.localDownvotedPapersDAO.writeToDatabase(
                        username, new ArrayList<>(downvotedPapers.keySet())
                );
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
