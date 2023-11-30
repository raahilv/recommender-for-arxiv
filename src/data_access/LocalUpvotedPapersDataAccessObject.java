package data_access;

import entities.ResearchPaper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalUpvotedPapersDataAccessObject {

    private final File upvotedPapersCSVFile;
    private final Map<String, List<ResearchPaper>> usersUpvotedPapers = new HashMap<>();
    private final Map<String, Integer> usersUpvotedPapersCSVFileHeader = new HashMap<>();
    private final LocalResearchPaperDataAccessObject localResearchPaperDAO;

    public LocalUpvotedPapersDataAccessObject(String upvotedPapersFilePath, LocalResearchPaperDataAccessObject localResearchPaperDAO) throws IOException {
        this.upvotedPapersCSVFile = new File(upvotedPapersFilePath);
        this.localResearchPaperDAO = localResearchPaperDAO;

        this.usersUpvotedPapersCSVFileHeader.put("username", 0);
        this.usersUpvotedPapersCSVFileHeader.put("paper_ids", 1);

        if (this.upvotedPapersCSVFile.length() == 0) {
            writeToDatabase();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.upvotedPapersCSVFile))) {
                String header = reader.readLine();
                assert header.equals("username,paper_ids");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[this.usersUpvotedPapersCSVFileHeader.get("username")]);
                    String[] upvotedPaperIDs =
                            String.valueOf(col[this.usersUpvotedPapersCSVFileHeader.get("paper_ids")]).split(" ");

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

    public List<ResearchPaper> getUpvotedPapers(String username) {
        return this.usersUpvotedPapers.containsKey(username) ?
               this.usersUpvotedPapers.get(username) : new ArrayList<>();
    }

    public boolean saveUpvotedPaper(String username, ResearchPaper paper) {
        if (this.usersUpvotedPapers.containsKey(username)) {
            this.usersUpvotedPapers.get(username).add(paper);
            return true;
        } else {
            return false;
        }
    }

    public void writeToDatabase(String username, List<String> upvotedPaperIDs) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.upvotedPapersCSVFile));

            StringBuilder mutableUpvotedPaperIDsStringRep = new StringBuilder();
            for (String paperID : upvotedPaperIDs) {
                mutableUpvotedPaperIDsStringRep.append(paperID).append(" ");
            }

            String line = String.format("%s,%s", username, mutableUpvotedPaperIDsStringRep);
            writer.write(line);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToDatabase() {
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(this.upvotedPapersCSVFile));
            writer.write(String.join(",", this.usersUpvotedPapersCSVFileHeader.keySet()));
            writer.newLine();

            for (String username : this.usersUpvotedPapers.keySet()) {
                StringBuilder mutableUpvotedPapersStringRep = new StringBuilder();
                for (ResearchPaper upvotedPaper : this.usersUpvotedPapers.get(username)) {
                    mutableUpvotedPapersStringRep.append(upvotedPaper.getID()).append(" ");
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
