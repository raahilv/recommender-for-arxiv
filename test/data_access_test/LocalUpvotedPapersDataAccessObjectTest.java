package data_access_test;

import data_access.LocalResearchPaperDataAccessObject;
import data_access.LocalUpvotedPapersDataAccessObject;
import entities.AuthorFactory;
import entities.CategoryFactory;
import entities.ResearchPaper;
import entities.ResearchPaperFactory;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalUpvotedPapersDataAccessObjectTest {
    private static final String PAPERS_FILEPATH = "test/test_files/papers.csv";
    private static final String UPVOTED_FILEPATH = "test/test_files/upvotedPapers.csv";
    private static final String EMPTY_UPVOTED_FILEPATH = "test/test_files/emptyUpvotedPapers.csv";
    private final AuthorFactory af = new AuthorFactory();
    private final CategoryFactory cf = new CategoryFactory();
    private final ResearchPaperFactory rpf = new ResearchPaperFactory();

    @Test
    public void testGetUpvotedPapersForExistingUserThatHasUpvotedPapers() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(UPVOTED_FILEPATH));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("ege,0 1 2");
            writer.newLine();
            writer.write("kevin,2");
            writer.newLine();
            writer.write("ozgen,1");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("raahil,0 2");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(PAPERS_FILEPATH, af, cf, rpf);
            LocalUpvotedPapersDataAccessObject lupDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_FILEPATH, lrpDAO);
            String username = "ege";

            List<ResearchPaper> upvotedPapers = lupDAO.getDownvotedPapers(username);
            StringBuilder mutableUpvotedPapersStringRep = new StringBuilder();
            for (ResearchPaper paper : upvotedPapers) {
                mutableUpvotedPapersStringRep.append(paper.getID()).append(" ");
            }
            mutableUpvotedPapersStringRep.deleteCharAt(mutableUpvotedPapersStringRep.length() - 1);
            String upvotedPapersStringRep = mutableUpvotedPapersStringRep.toString();

            assert (upvotedPapersStringRep.equals("0 1 2"));

        } catch (IOException ioe) {
            System.out.println("IOException in testGetUpvotedPapersForExistingUserThatHasUpvotedPapers().");
        }
    }

    @Test
    public void testGetUpvotedPapersForExistingUserThatHasNoUpvotedPapers() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(UPVOTED_FILEPATH));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("ege,0 1 2");
            writer.newLine();
            writer.write("kevin,2");
            writer.newLine();
            writer.write("ozgen,1");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("raahil,0 2");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(PAPERS_FILEPATH, af, cf, rpf);
            LocalUpvotedPapersDataAccessObject lupDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_FILEPATH, lrpDAO);
            String username = "jerry";
            List<ResearchPaper> upvotedPapers = lupDAO.getDownvotedPapers(username);
            assert (upvotedPapers.size() == 0);
        } catch (IOException ioe) {
            System.out.println("IOException in testGetUpvotedPapersForExistingUserThatHasUpvotedPapers().");
        }
    }

    @Test
    public void testGetUpvotedPapersForNonExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(UPVOTED_FILEPATH));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("ege,0 1 2");
            writer.newLine();
            writer.write("kevin,2");
            writer.newLine();
            writer.write("ozgen,1");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("raahil,0 2");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(PAPERS_FILEPATH, af, cf, rpf);
            LocalUpvotedPapersDataAccessObject lupDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_FILEPATH, lrpDAO);
            String username = "federico";
            List<ResearchPaper> upvotedPapers = lupDAO.getDownvotedPapers(username);
            assert (upvotedPapers.size() == 0);
        } catch (IOException ioe) {
            System.out.println("IOException in testGetUpvotedPapersForNonExistingUser().");
        }
    }

    @Test
    public void testSaveToDAOForExistingUserWithNonExistingPaper() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(UPVOTED_FILEPATH));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("ege,0 1 2");
            writer.newLine();
            writer.write("kevin,2");
            writer.newLine();
            writer.write("ozgen,1");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("raahil,0 2");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(PAPERS_FILEPATH, af, cf, rpf);
            LocalUpvotedPapersDataAccessObject lupDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_FILEPATH, lrpDAO);
            String username = "jerry";
            String paperID = "0";
            lupDAO.saveToDAO(username, paperID);

            BufferedReader reader = new BufferedReader(new FileReader(UPVOTED_FILEPATH));
            reader.readLine();  // ignores the header
            boolean matches = false;
            String row;
            while ((row = reader.readLine()) != null) {
                String[] rowSplit = row.split(",");
                String currentUsername = rowSplit[0];
                if (currentUsername.equals(username) && rowSplit.length > 1) {
                    System.out.println(rowSplit[1]);
                    matches = rowSplit[1].equals("0");
                    break;
                }
            }
            reader.close();

            assert (matches);

        } catch (IOException ioe) {
            System.out.println("IOException in testSaveToDAOForExistingUserWithNonExistingPaper().");
        }
    }

    @Test
    public void testWriteToDatabase() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(UPVOTED_FILEPATH));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("ege,0 1 2");
            writer.newLine();
            writer.write("kevin,2");
            writer.newLine();
            writer.write("ozgen,1");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("raahil,0 2");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(PAPERS_FILEPATH, af, cf, rpf);
            LocalUpvotedPapersDataAccessObject lupDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_FILEPATH, lrpDAO);
            List<String> previousFileContents = Files.readAllLines(Paths.get(UPVOTED_FILEPATH), StandardCharsets.UTF_8);
            lupDAO.writeToDatabase(new File(EMPTY_UPVOTED_FILEPATH));
            List<String> updatedEmptyFileContents = Files.readAllLines(Paths.get(EMPTY_UPVOTED_FILEPATH), StandardCharsets.UTF_8);

            assert  (previousFileContents.equals(updatedEmptyFileContents));
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabase().");
        }
    }

    @Test
    public void testWriteToDatabaseForExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(UPVOTED_FILEPATH));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("ege,0 1 2");
            writer.newLine();
            writer.write("kevin,2");
            writer.newLine();
            writer.write("ozgen,1");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("raahil,0 2");
            writer.newLine();
            writer.close();

            Path path = Paths.get(UPVOTED_FILEPATH);
            String username = "raahil";
            List<String> paperIDs = new ArrayList<>();
            paperIDs.add("0");
            paperIDs.add("1");

            String previousTargetLine = Files.readAllLines(path, StandardCharsets.UTF_8).get(5);
            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(PAPERS_FILEPATH, af, cf, rpf);
            LocalUpvotedPapersDataAccessObject lupDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_FILEPATH, lrpDAO);
            lupDAO.writeToDatabase(username, paperIDs);
            String updatedTargetLine = Files.readAllLines(path, StandardCharsets.UTF_8).get(5);

            assert (previousTargetLine.equals("raahil,0 2") && updatedTargetLine.equals("raahil,0 2 1"));
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabaseForExistingUser().");
        }
    }
}
