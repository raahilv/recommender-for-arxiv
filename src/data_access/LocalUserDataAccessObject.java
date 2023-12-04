package data_access;

import entities.*;
import use_cases.recommend.RecommendDataAccessInterface;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LocalUserDataAccessObject {

    private static final String USER_CSV_FILE_PATH = "test/test_files/users.csv";
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
                                     UserFactory userFactory) throws IOException {
        this.localLibraryDAO = localLibraryDAO;
        this.localUpvotedPapersDAO = localUpvotedPapersDAO;
        this.localDownvotedPapersDAO = localDownvotedPapersDAO;
        this.localPreferredCategoriesDAO = localPreferredCategoriesDAO;
        this.usersCSVFile = new File(USER_CSV_FILE_PATH);
        this.userFactory = userFactory;

        this.usersCSVFileHeader.put("username", 0);
        this.usersCSVFileHeader.put("password", 1);

        if (this.usersCSVFile.length() == 0) {
            writeToDatabase(this.usersCSVFile);
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
                    List<ResearchPaper> upvotedPapers = this.localUpvotedPapersDAO.getDownvotedPapers(username);
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

    public void saveToDatabase(User user) {
        try {
            if (!existsByUsername(user.getUsername())) {
                List<String> users = Files.readAllLines(Paths.get(USER_CSV_FILE_PATH), StandardCharsets.UTF_8);
                String newUserInfo = user.getUsername() + "," + user.getPassword();
                users.add(newUserInfo);

                BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_FILE_PATH));
                writer.write("username,password");
                writer.newLine();
                for (int i = 1; i < users.size(); i++) {
                    writer.write(users.get(i));
                    writer.newLine();
                }
                writer.close();
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public User get(String username) {
        return existsByUsername(username) ? this.users.get(username) : null;
    }

    public User getUser(String username) {
        return existsByUsername(username) ? this.users.get(username) : null;
    }

    public boolean existsByUsername(String username) {
        return this.users.containsKey(username);
    }

    public void writeToDatabase(File dest) {
        /* When using this method, there are no users in the CSV file, so we can safely assume
        * that each username written to the CSV file by this method is unique. */
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(dest));
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
