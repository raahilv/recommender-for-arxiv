package data_access;

import entities.ResearchPaper;

import java.io.*;
import java.util.*;

public class LocalLibraryDataAccessObject {

    private final File userLibrariesCSVFile;
    private final LocalResearchPaperDataAccessObject localResearchPaperDAO;
    private final Map<String, Integer> userLibrariesCSVFileHeader = new LinkedHashMap<>();
    private final Map<String, List<ResearchPaper>> userLibraries = new LinkedHashMap<>();

    public LocalLibraryDataAccessObject(String userLibrariesCSVFilePath, LocalResearchPaperDataAccessObject localResearchPaperDAO)
            throws IOException {
        this.userLibrariesCSVFile = new File(userLibrariesCSVFilePath);
        this.localResearchPaperDAO = localResearchPaperDAO;

        this.userLibrariesCSVFileHeader.put("username", 0);
        this.userLibrariesCSVFileHeader.put("paper_ids", 1);

        if (this.userLibrariesCSVFile.length() == 0) {
            writeToDatabase(this.userLibrariesCSVFile);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.userLibrariesCSVFile))) {
                String header = reader.readLine();
                assert header.equals("username,paper_ids");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[this.userLibrariesCSVFileHeader.get("username")]);
                    String paperIDsStringRep = String.valueOf(col[this.userLibrariesCSVFileHeader.get("paper_ids")]);
                    String[] paperIDs = paperIDsStringRep.split(" ");  // <paper_0> <paper_1> <paper_2>
                    List<ResearchPaper> currentUserLibrary = new ArrayList<>();
                    for (String paperID : paperIDs) {
                        ResearchPaper paper = this.localResearchPaperDAO.getPaperByID(paperID);
                        currentUserLibrary.add(paper);
                    }
                    this.userLibraries.put(username, currentUserLibrary);
                }
            }
        }
    }

    public void saveToDAO(String username, ResearchPaper paper) {
        if (this.userLibraries.containsKey(username)) {
            this.userLibraries.get(username).add(paper);
        } else {
            this.userLibraries.put(username, asList(paper));
        }
    }

    public void saveToDatabase(String username, String paperID) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.userLibrariesCSVFile));
            reader.readLine();  // passes the header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] userLibrary = row.split(",");
                if (userLibrary[0].equals(username)) {
                    String[] paperIDs = userLibrary[1].split(" ");
                    for (String savedPaperID : paperIDs) {
                        if (savedPaperID.equals(paperID)) {

                        }
                    }
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void writeToDatabase(String username, List<String> paperIDs) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.userLibrariesCSVFile));
            StringBuilder mutablePaperIDsStringRep = new StringBuilder();
            for (String paperID : paperIDs) {
                mutablePaperIDsStringRep.append(paperID).append(" ");
            }

            String line = String.format("%s,%s", username, mutablePaperIDsStringRep);
            writer.write(line);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToDatabase(File userLibrariesCSVFile) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(userLibrariesCSVFile));
            writer.write(String.join(",", this.userLibrariesCSVFileHeader.keySet()));
            writer.newLine();

            if (this.userLibraries.size() > 0) {
                for (String username : this.userLibraries.keySet()) {
                    StringBuilder mutableUserLibraryStringRep = new StringBuilder();
                    for (ResearchPaper paper : this.userLibraries.get(username)) {
                        mutableUserLibraryStringRep.append(paper.getID()).append(" ");
                    }
                    mutableUserLibraryStringRep.deleteCharAt(mutableUserLibraryStringRep.length() - 1);
                    String userLibraryStringRep = mutableUserLibraryStringRep.toString();

                    String line = String.format("%s,%s", username, userLibraryStringRep);
                    writer.write(line);
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Assume *username* can be found in this.users for now... */
    public List<ResearchPaper> getLibrary(String username) {
        return this.userLibraries.get(username);
    }

    public void delete(String username, String targetedPaperID) {
        if (this.userLibraries.containsKey(username)) {
            for (int i = this.userLibraries.get(username).size() - 1; i >= 0; i--) {
                ResearchPaper paper = this.userLibraries.get(username).get(i);
                if (paper.getID().equals(targetedPaperID)) {
                    this.userLibraries.get(username).remove(i);
                }
            }
        }
    }

    private List<ResearchPaper> asList(ResearchPaper paper) {
        List<ResearchPaper> paperListRep = new ArrayList<>();
        paperListRep.add(paper);
        return paperListRep;
    }

}
