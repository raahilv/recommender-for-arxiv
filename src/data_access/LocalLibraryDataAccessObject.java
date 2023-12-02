package data_access;

import entities.ResearchPaper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LocalLibraryDataAccessObject {

    private static final String userLibrariesCSVFilePath = "test/test_files/libraries.csv";
    private final File userLibrariesCSVFile;
    private final LocalResearchPaperDataAccessObject localResearchPaperDAO;
    private final Map<String, Integer> userLibrariesCSVFileHeader = new LinkedHashMap<>();
    private final Map<String, List<ResearchPaper>> userLibraries = new LinkedHashMap<>();

    public LocalLibraryDataAccessObject(LocalResearchPaperDataAccessObject localResearchPaperDAO)
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
                    String paperIDsStringRep = col.length == 1 ? " " : String.valueOf(col[this.userLibrariesCSVFileHeader.get("paper_ids")]);
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

//    public void saveToDAO(String username, ResearchPaper paper) {
//        if (this.userLibraries.containsKey(username)) {
//            this.userLibraries.get(username).add(paper);
//        } else {
//            this.userLibraries.put(username, asList(paper));
//        }
//    }

    public void saveToDatabase(String username, String paperID) {
        try {
            List<String> rows = Files.readAllLines(Paths.get(userLibrariesCSVFilePath), StandardCharsets.UTF_8);
            boolean userHandled = false;
            for (int i = 0; i < rows.size() && !userHandled; i++) {
                String[] userLibrary = rows.get(i).split(",");
                if (userLibrary[0].equals(username)) {
                    if (userLibrary.length == 2) {
                        String[] savedPaperIDs = userLibrary[1].split(" ");
                        boolean exists = false;
                        for (String savedPaperID : savedPaperIDs) {
                            if (savedPaperID.equals(paperID)) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            String updatedUserLibrary = username + "," + String.join(" ", savedPaperIDs) + " " + paperID;
                            rows.set(i, updatedUserLibrary);
                        }
                    } else if (userLibrary.length == 1) {
                        String updatedUserLibrary = username + "," + paperID;
                        rows.set(i, updatedUserLibrary);
                    }
                    userHandled = true;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(userLibrariesCSVFilePath));
            for (String row : rows) {
                writer.write(row);
                writer.newLine();
            }
            writer.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void writeToDatabase(String username, List<String> paperIDs) {
        for (String paperID : paperIDs) {
            saveToDatabase(username, paperID);
        }
    }

    public void writeToDatabase(File dest) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(dest));
            writer.write(String.join(",", this.userLibrariesCSVFileHeader.keySet()));
            writer.newLine();

            if (this.userLibraries.size() > 0) {
                for (String username : this.userLibraries.keySet()) {
                    StringBuilder mutableUserLibraryStringRep = new StringBuilder();
                    for (ResearchPaper paper : this.userLibraries.get(username)) {
                        mutableUserLibraryStringRep.append(paper.getID()).append(" ");
                    }
                    if (mutableUserLibraryStringRep.length() > 0) {
                        mutableUserLibraryStringRep.deleteCharAt(mutableUserLibraryStringRep.length() - 1);
                    }
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

    public List<ResearchPaper> getLibrary(String username) {
        return this.userLibraries.containsKey(username) ?
                this.userLibraries.get(username) : new ArrayList<>();
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

}
