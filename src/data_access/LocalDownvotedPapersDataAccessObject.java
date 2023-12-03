package data_access;

import entities.ResearchPaper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LocalDownvotedPapersDataAccessObject {

    private static final String DOWNVOTED_PAPERS_CSV_FILE_PATH = "test/test_files/downvotedPapers.csv";
    private final File downvotedPapersCSVFile;
    private final Map<String, List<ResearchPaper>> usersDownvotedPapers = new LinkedHashMap<>();
    private final Map<String, Integer> usersDownvotedPapersCSVFileHeader = new LinkedHashMap<>();
    private final LocalResearchPaperDataAccessObject localResearchPaperDAO;

    public LocalDownvotedPapersDataAccessObject(String downvotedPapersFilePath, LocalResearchPaperDataAccessObject localResearchPaperDAO) throws IOException {
        this.downvotedPapersCSVFile = new File(downvotedPapersFilePath);
        this.localResearchPaperDAO = localResearchPaperDAO;

        this.usersDownvotedPapersCSVFileHeader.put("username", 0);
        this.usersDownvotedPapersCSVFileHeader.put("paper_ids", 1);

        if (this.downvotedPapersCSVFile.length() == 0) {
            writeToDatabase(this.downvotedPapersCSVFile);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.downvotedPapersCSVFile))) {
                String header = reader.readLine();
                assert header.equals("username,paper_ids");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[this.usersDownvotedPapersCSVFileHeader.get("username")]);
                    String[] downvotedPaperIDs = col.length == 1 ? new String[0] : String.valueOf(col[this.usersDownvotedPapersCSVFileHeader.get("paper_ids")]).split(" ");

                    List<ResearchPaper> downvotedPapers = new ArrayList<>();
                    for (String paperID : downvotedPaperIDs) {
                        downvotedPapers.add(
                                this.localResearchPaperDAO.getPaperByID(paperID)
                        );
                    }

                    this.usersDownvotedPapers.put(username, downvotedPapers);
                }
            }
        }
    }

    public List<ResearchPaper> getDownvotedPapers(String username) {
        return this.usersDownvotedPapers.containsKey(username) ?
                this.usersDownvotedPapers.get(username) : new ArrayList<>();
    }

    public void saveToDAO(String username, String paperID) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(DOWNVOTED_PAPERS_CSV_FILE_PATH));
            boolean userHandled = false;
            for (int i = 0; i < lines.size() && !userHandled; i++) {
                String[] userDownvotedPapers = lines.get(i).split(",");
                if (userDownvotedPapers[0].equals(username)) {
                    if (userDownvotedPapers.length == 2) {
                        String[] savedDownvotedPaperIDs = userDownvotedPapers[1].split(" ");
                        boolean exists = false;
                        for (String savedDownvotedPaperID : savedDownvotedPaperIDs) {
                            if (savedDownvotedPaperID.equals(paperID)) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            String updatedDownvotedPapers = username + "," + String.join(" ", savedDownvotedPaperIDs) + " " + paperID;
                            lines.set(i, updatedDownvotedPapers);
                        }
                    } else if (userDownvotedPapers.length == 1) {
                        String updatedDownvotedPapers = username + "," + paperID;
                        lines.set(i, updatedDownvotedPapers);
                    }
                    userHandled = true;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(DOWNVOTED_PAPERS_CSV_FILE_PATH));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void writeToDatabase(String username, List<String> downvotedPaperIDs) {
        for (String downvotedPaperID : downvotedPaperIDs) {
            saveToDAO(username, downvotedPaperID);
        }
    }

    public void writeToDatabase(File dest) {
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(dest));
            writer.write(String.join(",", this.usersDownvotedPapersCSVFileHeader.keySet()));
            writer.newLine();

            for (String username : this.usersDownvotedPapers.keySet()) {
                StringBuilder mutableDownvotedPapersStringRep = new StringBuilder();
                for (ResearchPaper downvotedPaper : this.usersDownvotedPapers.get(username)) {
                    mutableDownvotedPapersStringRep.append(downvotedPaper.getID()).append(" ");
                }
                if (mutableDownvotedPapersStringRep.length() > 0) {
                    mutableDownvotedPapersStringRep.deleteCharAt(mutableDownvotedPapersStringRep.length() - 1);
                }

                String downvotedPapersStringRep = mutableDownvotedPapersStringRep.toString();
                String line = String.format("%s,%s", username, downvotedPapersStringRep);
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
