package data_access_objects;

import data_access.LocalResearchPaperDataAccessObject;
import entities.AuthorFactory;
import entities.CategoryFactory;
import entities.ResearchPaper;
import entities.ResearchPaperFactory;
import org.junit.Test;

import java.io.*;

public class LocalResearchPaperDataAccessObjectTest {
    private static final String filepath = "test/test_files/papers.csv";
    private static final String emptyFilepath = "test/test_files/emptyPapers.csv";
    private final AuthorFactory af = new AuthorFactory();
    private final CategoryFactory cf = new CategoryFactory();
    private final ResearchPaperFactory rpf = new ResearchPaperFactory();

    @Test
    public void testGetPaperByIDThatExists() {
        try {
            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(filepath, af, cf, rpf);
            String paperID = "0";
            ResearchPaper paper = lrpDAO.getPaperByID(paperID);
            assert (paper != null && paper.getID().equals(paperID));
        } catch (IOException ioe) {
            System.out.println("IOException in testGetPaperByIDThatExists");
        }
    }

    @Test
    public void testGetPapersByIDThatDoesNotExist() {
        try {
            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(filepath, af, cf, rpf);
            String paperID = "2";
            ResearchPaper paper = lrpDAO.getPaperByID(paperID);
            assert (paper == null);
        } catch (IOException ioe) {
            System.out.println("IOException in testGetPaperByIDThatDoesNotExist");
        }
    }

    @Test
    public void testWriteToDatabaseWithEmptyPaperCollection() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(emptyFilepath)));
            writer.write("");
            writer.close();

            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(emptyFilepath, af, cf, rpf);
            BufferedReader reader = new BufferedReader(new FileReader(new File(emptyFilepath)));

            StringBuilder mutableInObjectHeader = new StringBuilder();
            for (String headerField : lrpDAO.getPapersCSVFileHeader().keySet()) {
                mutableInObjectHeader.append(headerField).append(",");
            }
            mutableInObjectHeader.deleteCharAt(mutableInObjectHeader.length() - 1);

            String inObjectHeader = mutableInObjectHeader.toString();
            String inFileHeader = reader.readLine();

            assert (inObjectHeader.equals(inFileHeader));
        } catch (IOException ioe) {
            System.out.println("IOException in testWriteToDatabase");
        }
    }

//    @Test
//    public void testWriterToDatabaseWithNonEmptyPaperCollection() {
//        try {
//            File src = new File(emptyFilepath);
//            BufferedWriter writer = new BufferedWriter(new FileWriter(src));
//            writer.write("");
//            writer.close();
//
//            LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject(filepath, af, cf, rpf);
//            lrpDAO.setPapersCSVFile(emptyFilepath);
//            lrpDAO.writeToDatabase();
//
//            BufferedReader reader = new BufferedReader(new FileReader(src));
//            int counter = 0;
//            while (reader.readLine() != null) { counter++; }
//
//            assert (counter == 3);
//
//        } catch (IOException ioe) {
//            System.out.println("IOException in testWriterToDatabaseWithNonEmptyPaperCollection");
//        }
//    }


}
