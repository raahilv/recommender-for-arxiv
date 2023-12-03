package data_access_test;

import data_access.LocalLibraryDataAccessObject;
import data_access.LocalResearchPaperDataAccessObject;
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

public class LocalLibraryDataAccessObjectTest {

    private static final String librariesFilepath = "test/test_files/libraries.csv";
    private static final String papersFilepath = "test/test_files/papers.csv";
    private final AuthorFactory af = new AuthorFactory();
    private final CategoryFactory cf = new CategoryFactory();
    private final ResearchPaperFactory rpf = new ResearchPaperFactory();

    @Test
    public void testGetLibraryExistingUserEmptyLibrary() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(lrpDAO);

            String username = "jerry";
            List<ResearchPaper> actualLib = llDAO.getLibrary(username);
            assert (actualLib.isEmpty());
        } catch (IOException ioe) {
            System.out.println("IOException in testGetLibraryExistingUser()");
        }
    }

    @Test
    public void testGetLibraryExistingUserNonEmptyLibrary() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(lrpDAO);

            String username = "raahil";
            List<ResearchPaper> actualLib = llDAO.getLibrary(username);
            assert (actualLib.size() == 1 && actualLib.get(0).getID().equals("0"));
        } catch (IOException ioe) {
            System.out.println("IOException in testGetLibraryExistingUserNonEmptyLibrary().");
        }
    }

    @Test
    public void testSaveToDatabaseWithExistingUserAndNonExistingPaper() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(lrpDAO);
            String username = "raahil";
            String paperID = "1";

            int previousLibrarySize = 0;
            int updatedLibrarySize = 0;

            BufferedReader reader = new BufferedReader(new FileReader(librariesFilepath));
            reader.readLine();  // ignores the header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] userLibraryInfo = row.split(",");
                if (userLibraryInfo[0].equals(username)) {
                    previousLibrarySize = userLibraryInfo[1].split(" ").length;
                    break;
                }
            }

            llDAO.saveToDatabase(username, lrpDAO.getPaperByID(paperID));

            reader = new BufferedReader(new FileReader(librariesFilepath));  // ignores the header
            reader.readLine();
            while ((row = reader.readLine()) != null) {
                String[] userLibraryInfo = row.split(",");
                if (userLibraryInfo[0].equals(username)) {
                    updatedLibrarySize = userLibraryInfo[1].split(" ").length;
                    break;
                }
            }
            reader.close();

            assert (previousLibrarySize + 1 == updatedLibrarySize);

        } catch (IOException ioe) {
            System.out.println("IOException in testSaveToDatabaseWithExistingUser().");
        }
    }

    @Test
    public void testSaveToDatabaseWithExistingUserAndExistingPaper() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(lrpDAO);
            String username = "kevin";
            String paperID = "0";

            int previousLibrarySize = 0;
            int updatedLibrarySize = 0;

            BufferedReader reader = new BufferedReader(new FileReader(librariesFilepath));
            reader.readLine();  // ignores the header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] userLibraryInfo = row.split(",");
                if (userLibraryInfo[0].equals(username)) {
                    previousLibrarySize = userLibraryInfo[1].split(" ").length;
                    break;
                }
            }

            llDAO.saveToDatabase(username, lrpDAO.getPaperByID(paperID));

            reader = new BufferedReader(new FileReader(librariesFilepath));  // ignores the header
            reader.readLine();
            while ((row = reader.readLine()) != null) {
                String[] userLibraryInfo = row.split(",");
                if (userLibraryInfo[0].equals(username)) {
                    updatedLibrarySize = userLibraryInfo[1].split(" ").length;
                    break;
                }
            }
            reader.close();

            assert (previousLibrarySize == updatedLibrarySize);

        } catch (IOException ioe) {
            System.out.println("IOException in testSaveToDatabaseWithExistingUser().");
        }
    }

    @Test
    public void testSaveToDatabaseWithNonExistingUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            Path path = Paths.get(librariesFilepath);
            List<String> previousFileContents = Files.readAllLines(path, StandardCharsets.UTF_8);
            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(lrpDAO);

            String username = "sayin";
            String paperID = "whateverid";

            boolean nullPaper = lrpDAO.getPaperByID(paperID) == null;
            List<String> updatedFileContents = Files.readAllLines(path, StandardCharsets.UTF_8);
            assert (nullPaper && previousFileContents.equals(updatedFileContents));

        } catch (IOException ioe) {
            System.out.println("IOException in testSaveToDatabaseWithNonExistingUser().");
        }
    }

    @Test
    public void testWriteToDatabaseWithListOfPaperIDs() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(lrpDAO);
            List<String> paperIDs = new ArrayList<>();
            paperIDs.add("1");
            paperIDs.add("0");
            String username = "jerry";
            llDAO.writeToDatabase(username, paperIDs);

            BufferedReader reader = new BufferedReader(new FileReader(librariesFilepath));
            reader.readLine();  // ignores header;
            String row;
            while ((row = reader.readLine()) != null) {
                if (row.split(",")[0].equals(username)) {
                    break;
                }
            }
            reader.close();
            assert (row != null && row.split(",")[1].equals("1 0"));

        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabaseWithListOfPaperIDs().");
        }
    }

    @Test
    public void testWriteToDatabaseWithFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            String emptyLibrariesPath = "test/test_files/emptyLibraries.csv";
            File emptyLibrariesFile = new File(emptyLibrariesPath);
            List<String> librariesContents = Files.readAllLines(Paths.get(librariesFilepath), StandardCharsets.UTF_8);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(
                    new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf)
            );
            llDAO.writeToDatabase(emptyLibrariesFile);
            List<String> emptyLibrariesContents = Files.readAllLines(Paths.get(emptyLibrariesPath), StandardCharsets.UTF_8);

            assert (librariesContents.equals(emptyLibrariesContents));
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabaseWithFile().");
        }
    }

    @Test
    public void testDeleteWithExistingUserAndNonExistingPaperID() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(lrpDAO);
            String username = "jerry";
            String paperID = "3";
            List<ResearchPaper> previousUserLibrary = llDAO.getLibrary(username);
            llDAO.delete(username, paperID);
            List<ResearchPaper> updatedUserLibrary = llDAO.getLibrary(username);

            assert (previousUserLibrary.equals(updatedUserLibrary));

        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabaseWithFile().");
        }
    }

    @Test
    public void testDeleteWithExistingUserAndExistingPaperID() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(librariesFilepath));
            writer.write("username,paper_ids");
            writer.newLine();
            writer.write("kevin,0 1");
            writer.newLine();
            writer.write("raahil,0");
            writer.newLine();
            writer.write("jerry,");
            writer.newLine();
            writer.write("ege,1");
            writer.newLine();
            writer.write("ozgen,1 0");
            writer.newLine();
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(papersFilepath, af, cf, rpf);
            LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject(lrpDAO);
            String username = "ozgen";
            String paperID = "1";

            int previousUserLibrarySize = llDAO.getLibrary(username).size();
            llDAO.delete(username, paperID);
            int updatedUserLibrarySize = llDAO.getLibrary(username).size();

            assert (previousUserLibrarySize == updatedUserLibrarySize + 1);
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabaseWithFile().");
        }
    }

}
