package data_access;

import data_access.*;
import entities.*;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LocalUserDataAccessObjectTest {

    private final static String PAPERS_CSV_FILE_PATH = "test/test_files/papers.csv";
    private final static String UPVOTED_CSV_FILE_PATH = "test/test_files/upvotedPapers.csv";
    private final static String DOWNVOTED_CSV_FILE_PATH = "test/test_files/downvotedPapers.csv";
    private final static String USER_CSV_FILE_PATH = "test/test_files/users.csv";
    private final static String PREFERRED_CATEGORIES_FILE_PATH = "test/test_files/categories.csv";
    private final AuthorFactory af = new AuthorFactory();
    private final CategoryFactory cf = new CategoryFactory();
    private final ResearchPaperFactory rpf = new ResearchPaperFactory();
    private final UserFactory uf = new CommonUserFactory();

    @Test
    public void testGetUserWithExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_FILE_PATH));
            writer.write("username,password");
            writer.newLine();
            writer.write("kevin,kevinpwd");
            writer.newLine();
            writer.write("jerry,jerrypwd");
            writer.newLine();
            writer.write("ozgen,ozgenpwd");
            writer.newLine();
            writer.write("ege,egepwd");
            writer.newLine();
            writer.write("raahil,raahilpwd");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPERS_CSV_FILE_PATH, af, cf, rpf);
            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_CSV_FILE_PATH, rpDAO);
            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_CSV_FILE_PATH, rpDAO);
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);

            String username = "kevin";
            User actualUser = uDAO.getUser(username);
            assert (actualUser.getUsername().equals(username) && actualUser.getPassword().equals("kevinpwd"));
        } catch (IOException ioe) {
            System.out.println("IOException in testGetUserWithExistingUser().");
        }
    }

    @Test
    public void testGetUserWithNonExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_FILE_PATH));
            writer.write("username,password");
            writer.newLine();
            writer.write("kevin,kevinpwd");
            writer.newLine();
            writer.write("jerry,jerrypwd");
            writer.newLine();
            writer.write("ozgen,ozgenpwd");
            writer.newLine();
            writer.write("ege,egepwd");
            writer.newLine();
            writer.write("raahil,raahilpwd");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPERS_CSV_FILE_PATH, af, cf, rpf);
            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_CSV_FILE_PATH, rpDAO);
            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_CSV_FILE_PATH, rpDAO);
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);

            String username = "boris";
            User actualUser = uDAO.getUser(username);
            assert (actualUser == null);
        } catch (IOException ioe) {
            System.out.println("IOException in testGetUserWithNonExistingUser().");
        }
    }

    @Test
    public void testExistsByUsernameWithExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_FILE_PATH));
            writer.write("username,password");
            writer.newLine();
            writer.write("kevin,kevinpwd");
            writer.newLine();
            writer.write("jerry,jerrypwd");
            writer.newLine();
            writer.write("ozgen,ozgenpwd");
            writer.newLine();
            writer.write("ege,egepwd");
            writer.newLine();
            writer.write("raahil,raahilpwd");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPERS_CSV_FILE_PATH, af, cf, rpf);
            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_CSV_FILE_PATH, rpDAO);
            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_CSV_FILE_PATH, rpDAO);
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);

            String username = "ege";
            assert (uDAO.existsByUsername(username));
        } catch (IOException ioe) {
            System.out.println("IOException in testExistsByUsernameWithExistingUser().");
        }
    }

    @Test
    public void testExistsByUsernameWithNonExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_FILE_PATH));
            writer.write("username,password");
            writer.newLine();
            writer.write("kevin,kevinpwd");
            writer.newLine();
            writer.write("jerry,jerrypwd");
            writer.newLine();
            writer.write("ozgen,ozgenpwd");
            writer.newLine();
            writer.write("ege,egepwd");
            writer.newLine();
            writer.write("raahil,raahilpwd");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPERS_CSV_FILE_PATH, af, cf, rpf);
            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_CSV_FILE_PATH, rpDAO);
            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_CSV_FILE_PATH, rpDAO);
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);

            String username = "kaiwen";
            assert (!uDAO.existsByUsername(username));
        } catch (IOException ioe) {
            System.out.println("IOException in testExistsByUsernameWithNonExistingUser().");
        }
    }

    @Test
    public void testSaveToDatabaseWithExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_FILE_PATH));
            writer.write("username,password");
            writer.newLine();
            writer.write("kevin,kevinpwd");
            writer.newLine();
            writer.write("jerry,jerrypwd");
            writer.newLine();
            writer.write("ozgen,ozgenpwd");
            writer.newLine();
            writer.write("ege,egepwd");
            writer.newLine();
            writer.write("raahil,raahilpwd");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPERS_CSV_FILE_PATH, af, cf, rpf);
            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_CSV_FILE_PATH, rpDAO);
            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_CSV_FILE_PATH, rpDAO);
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);

            String username = "kevin";
            String password = "different_pwd";
            User user = uf.create(username, password);
            uDAO.saveToDatabase(user);

            BufferedReader reader = new BufferedReader(new FileReader(USER_CSV_FILE_PATH));
            reader.readLine();  // ignores the header;
            String row;
            boolean pwdChanged = true;
            while ((row = reader.readLine()) != null) {
                String currentUsername = row.split(",")[0];
                String currentPwd = row.split(",")[1];
                if (currentUsername.equals(username) && !currentPwd.equals(password)) {
                    pwdChanged = false;
                    break;
                }
            }
            reader.close();


            assert (!pwdChanged);

        } catch (IOException ioe) {
            System.out.println("IOException in testExistsByUsernameWithNonExistingUser().");
        }
    }

    @Test
    public void testSaveToDatabaseWithNonExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_FILE_PATH));
            writer.write("username,password");
            writer.newLine();
            writer.write("kevin,kevinpwd");
            writer.newLine();
            writer.write("jerry,jerrypwd");
            writer.newLine();
            writer.write("ozgen,ozgenpwd");
            writer.newLine();
            writer.write("ege,egepwd");
            writer.newLine();
            writer.write("raahil,raahilpwd");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPERS_CSV_FILE_PATH, af, cf, rpf);
            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_CSV_FILE_PATH, rpDAO);
            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_CSV_FILE_PATH, rpDAO);
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);

            String username = "kaiwen";
            String password = "kaiwenpwd";
            User user = uf.create(username, password);
            uDAO.saveToDatabase(user);

            assert (Files.readAllLines(Paths.get(USER_CSV_FILE_PATH), StandardCharsets.UTF_8).get(6).equals("kaiwen,kaiwenpwd"));

        } catch (IOException ioe) {
            System.out.println("IOException in testExistsByUsernameWithNonExistingUser().");
        }
    }

    @Test
    public void testWriteToDatabase() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_FILE_PATH));
            writer.write("username,password");
            writer.newLine();
            writer.write("kevin,kevinpwd");
            writer.newLine();
            writer.write("jerry,jerrypwd");
            writer.newLine();
            writer.write("ozgen,ozgenpwd");
            writer.newLine();
            writer.write("ege,egepwd");
            writer.newLine();
            writer.write("raahil,raahilpwd");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPERS_CSV_FILE_PATH, af, cf, rpf);
            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_CSV_FILE_PATH, rpDAO);
            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_CSV_FILE_PATH, rpDAO);
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);

            List<String> previousUserFileLines = Files.readAllLines(Paths.get(USER_CSV_FILE_PATH), StandardCharsets.UTF_8);
            List<String> previousPreferredCategoriesFileLines = Files.readAllLines(Paths.get(PREFERRED_CATEGORIES_FILE_PATH), StandardCharsets.UTF_8);
            List<String> previousUpvotedCategoriesFileLines = Files.readAllLines(Paths.get(UPVOTED_CSV_FILE_PATH), StandardCharsets.UTF_8);
            List<String> previousDownvotedCategoriesFileLines = Files.readAllLines(Paths.get(DOWNVOTED_CSV_FILE_PATH), StandardCharsets.UTF_8);

            uDAO.writeToDatabase(new File(USER_CSV_FILE_PATH));
            List<String> updatedUserFileLines = Files.readAllLines(Paths.get(USER_CSV_FILE_PATH), StandardCharsets.UTF_8);
            List<String> updatedPreferredCategoriesFileLines = Files.readAllLines(Paths.get(PREFERRED_CATEGORIES_FILE_PATH), StandardCharsets.UTF_8);
            List<String> updatedUpvotedCategoriesFileLines = Files.readAllLines(Paths.get(UPVOTED_CSV_FILE_PATH), StandardCharsets.UTF_8);
            List<String> updatedDownvotedCategoriesFileLines = Files.readAllLines(Paths.get(DOWNVOTED_CSV_FILE_PATH), StandardCharsets.UTF_8);

            assert (previousUserFileLines.equals(updatedUserFileLines) &&
                    previousPreferredCategoriesFileLines.equals(updatedPreferredCategoriesFileLines) &&
                    previousUpvotedCategoriesFileLines.equals(updatedUpvotedCategoriesFileLines) &&
                    previousDownvotedCategoriesFileLines.equals(updatedDownvotedCategoriesFileLines));
        } catch (IOException ioe) {
            System.out.println("IOException in testExistsByUsernameWithNonExistingUser().");
        }
    }
}
