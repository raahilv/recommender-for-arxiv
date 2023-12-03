package data_access_test;

import data_access.LocalResearchPaperDataAccessObject;
import data_access.LocalDownvotedPapersDataAccessObject;
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

public class LocalDownvotedPapersDataAccessObjectTest {
    private static final String PAPERS_FILEPATH = "test/test_files/papers.csv";
    private static final String DOWNVOTED_FILEPATH = "test/test_files/downvotedPapers.csv";
    private static final String EMPTY_DOWNVOTED_FILEPATH = "test/test_files/emptyDownvotedPapers.csv";
    private final AuthorFactory af = new AuthorFactory();
    private final CategoryFactory cf = new CategoryFactory();
    private final ResearchPaperFactory rpf = new ResearchPaperFactory();

    @Test
    public void testGetDownvotedPapersForExistingUserThatHasDownvotedPapers() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DOWNVOTED_FILEPATH));
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
            LocalDownvotedPapersDataAccessObject ldpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_FILEPATH, lrpDAO);
            String username = "ege";

            List<ResearchPaper> downvotedPapers = ldpDAO.getDownvotedPapers(username);
            StringBuilder mutableDownvotedPapersStringRep = new StringBuilder();
            for (ResearchPaper paper : downvotedPapers) {
                mutableDownvotedPapersStringRep.append(paper.getID()).append(" ");
            }
            mutableDownvotedPapersStringRep.deleteCharAt(mutableDownvotedPapersStringRep.length() - 1);
            String downvotedPapersStringRep = mutableDownvotedPapersStringRep.toString();

            assert (downvotedPapersStringRep.equals("0 1 2"));

        } catch (IOException ioe) {
            System.out.println("IOException in testGetDownvotedPapersForExistingUserThatHasDownvotedPapers().");
        }
    }

    @Test
    public void testGetDownvotedPapersForExistingUserThatHasNoDownvotedPapers() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DOWNVOTED_FILEPATH));
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
            LocalDownvotedPapersDataAccessObject ldpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_FILEPATH, lrpDAO);
            String username = "jerry";
            List<ResearchPaper> downvotedPapers = ldpDAO.getDownvotedPapers(username);
            assert (downvotedPapers.size() == 0);
        } catch (IOException ioe) {
            System.out.println("IOException in testGetDownvotedPapersForExistingUserThatHasDownvotedPapers().");
        }
    }

    @Test
    public void testGetDownvotedPapersForNonExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DOWNVOTED_FILEPATH));
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
            LocalDownvotedPapersDataAccessObject ldpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_FILEPATH, lrpDAO);
            String username = "federico";
            List<ResearchPaper> downvotedPapers = ldpDAO.getDownvotedPapers(username);
            assert (downvotedPapers.size() == 0);
        } catch (IOException ioe) {
            System.out.println("IOException in testGetDownvotedPapersForNonExistingUser().");
        }
    }

    @Test
    public void testSaveToDAOForExistingUserWithNonExistingPaper() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DOWNVOTED_FILEPATH));
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
            LocalDownvotedPapersDataAccessObject ldpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_FILEPATH, lrpDAO);
            String username = "jerry";
            String paperID = "0";
            ldpDAO.saveToDAO(username, paperID);

            BufferedReader reader = new BufferedReader(new FileReader(DOWNVOTED_FILEPATH));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(DOWNVOTED_FILEPATH));
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
            LocalDownvotedPapersDataAccessObject ldpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_FILEPATH, lrpDAO);
            List<String> previousFileContents = Files.readAllLines(Paths.get(DOWNVOTED_FILEPATH), StandardCharsets.UTF_8);
            ldpDAO.writeToDatabase(new File(EMPTY_DOWNVOTED_FILEPATH));
            List<String> updatedEmptyFileContents = Files.readAllLines(Paths.get(EMPTY_DOWNVOTED_FILEPATH), StandardCharsets.UTF_8);

            assert  (previousFileContents.equals(updatedEmptyFileContents));
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabase().");
        }
    }

    @Test
    public void testWriteToDatabaseForExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DOWNVOTED_FILEPATH));
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

            Path path = Paths.get(DOWNVOTED_FILEPATH);
            String username = "raahil";
            List<String> paperIDs = new ArrayList<>();
            paperIDs.add("0");
            paperIDs.add("1");

            String previousTargetLine = Files.readAllLines(path, StandardCharsets.UTF_8).get(5);
            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(PAPERS_FILEPATH, af, cf, rpf);
            LocalDownvotedPapersDataAccessObject ldpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_FILEPATH, lrpDAO);
            ldpDAO.writeToDatabase(username, paperIDs);
            String updatedTargetLine = Files.readAllLines(path, StandardCharsets.UTF_8).get(5);

            assert (previousTargetLine.equals("raahil,0 2") && updatedTargetLine.equals("raahil,0 2 1"));
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabaseForExistingUser().");
        }
    }
}
