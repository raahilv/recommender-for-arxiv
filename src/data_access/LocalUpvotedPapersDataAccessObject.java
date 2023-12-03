package data_access;

import entities.ResearchPaper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LocalUpvotedPapersDataAccessObject {

    private static final String UPVOTED_PAPERS_CSV_FILE_PATH = "test/test_files/upvotedPapers.csv";
    private final File upvotedPapersCSVFile;
    private final Map<String, List<ResearchPaper>> usersUpvotedPapers = new LinkedHashMap<>();
    private final Map<String, Integer> usersUpvotedPapersCSVFileHeader = new LinkedHashMap<>();
    private final LocalResearchPaperDataAccessObject localResearchPaperDAO;

    public LocalUpvotedPapersDataAccessObject(String upvotedPapersFilePath, LocalResearchPaperDataAccessObject localResearchPaperDAO) throws IOException {
        this.upvotedPapersCSVFile = new File(upvotedPapersFilePath);
        this.localResearchPaperDAO = localResearchPaperDAO;

        this.usersUpvotedPapersCSVFileHeader.put("username", 0);
        this.usersUpvotedPapersCSVFileHeader.put("paper_ids", 1);

        if (this.upvotedPapersCSVFile.length() == 0) {
            writeToDatabase(this.upvotedPapersCSVFile);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.upvotedPapersCSVFile))) {
                String header = reader.readLine();
                assert header.equals("username,paper_ids");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[this.usersUpvotedPapersCSVFileHeader.get("username")]);
                    String[] upvotedPaperIDs = col.length == 1 ? new String[0] : String.valueOf(col[this.usersUpvotedPapersCSVFileHeader.get("paper_ids")]).split(" ");

                    List<ResearchPaper> upvotedPapers = new ArrayList<>();
                    for (String paperID : upvotedPaperIDs) {
                        upvotedPapers.add(
                                this.localResearchPaperDAO.getPaperByID(paperID)
                        );
                    }

                    this.usersUpvotedPapers.put(username, upvotedPapers);
                }
            }
        }
    }

    public List<ResearchPaper> getDownvotedPapers(String username) {
        return this.usersUpvotedPapers.containsKey(username) ?
               this.usersUpvotedPapers.get(username) : new ArrayList<>();
    }

    public void saveToDAO(String username, String paperID) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(UPVOTED_PAPERS_CSV_FILE_PATH));
            boolean userHandles = false;
            for (int i = 0; i < lines.size() && !userHandles; i++) {
                String[] userUpvotedPapers = lines.get(i).split(",");
                if (userUpvotedPapers[0].equals(username)) {
                    if (userUpvotedPapers.length == 2) {
                        String[] savedUpvotedPaperIDs = userUpvotedPapers[1].split(" ");
                        boolean exists = false;
                        for (String savedUpvotedPaperID : savedUpvotedPaperIDs) {
                            if (savedUpvotedPaperID.equals(paperID)) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            String updatedUpvotedPapers = username + "," + String.join(" ", savedUpvotedPaperIDs) + " " + paperID;
                            lines.set(i, updatedUpvotedPapers);
                        }
                    } else if (userUpvotedPapers.length == 1) {
                        String updatedUpvotedPapers = username + "," + paperID;
                        lines.set(i, updatedUpvotedPapers);
                    }
                    userHandles = true;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(UPVOTED_PAPERS_CSV_FILE_PATH));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void writeToDatabase(String username, List<String> upvotedPaperIDs) {
        for (String upvotedPaperID : upvotedPaperIDs) {
            saveToDAO(username, upvotedPaperID);
        }
    }

    public void writeToDatabase(File dest) {
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(dest));
            writer.write(String.join(",", this.usersUpvotedPapersCSVFileHeader.keySet()));
            writer.newLine();

            for (String username : this.usersUpvotedPapers.keySet()) {
                StringBuilder mutableUpvotedPapersStringRep = new StringBuilder();
                for (ResearchPaper upvotedPaper : this.usersUpvotedPapers.get(username)) {
                    mutableUpvotedPapersStringRep.append(upvotedPaper.getID()).append(" ");
                }
                if (mutableUpvotedPapersStringRep.length() > 0) {
                    mutableUpvotedPapersStringRep.deleteCharAt(mutableUpvotedPapersStringRep.length() - 1);
                }

                String upvotedPapersStringRep = mutableUpvotedPapersStringRep.toString();
                String line = String.format("%s,%s", username, upvotedPapersStringRep);
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
