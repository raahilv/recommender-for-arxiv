package data_access;

import entities.ResearchPaper;
import entities.ResearchPaperFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalDownvotedPapersDataAccessObject {

    private final File downvotedPapersCSVFile;
    private final Map<String, List<ResearchPaper>> usersDownvotedPapers = new HashMap<>();
    private final Map<String, Integer> usersDownvotedPapersCSVFileHeader = new HashMap<>();
    private final LocalResearchPaperDataAccessObject localResearchPaperDAO;

    public LocalDownvotedPapersDataAccessObject(String downvotedPapersFilePath, LocalResearchPaperDataAccessObject localResearchPaperDAO) throws IOException {
        this.downvotedPapersCSVFile = new File(downvotedPapersFilePath);
        this.localResearchPaperDAO = localResearchPaperDAO;

        this.usersDownvotedPapersCSVFileHeader.put("username", 0);
        this.usersDownvotedPapersCSVFileHeader.put("paper_ids", 1);

        if (this.downvotedPapersCSVFile.length() == 0) {
            writeToDatabase();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.downvotedPapersCSVFile))) {
                String header = reader.readLine();
                assert header.equals("username,paper_ids");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[this.usersDownvotedPapersCSVFileHeader.get("username")]);
                    String[] upvotedPaperIDs =
                            String.valueOf(col[this.usersDownvotedPapersCSVFileHeader.get("paper_ids")]).split(" ");

                    List<ResearchPaper> upvotedPapers = new ArrayList<>();
                    for (String paperID : upvotedPaperIDs) {
                        upvotedPapers.add(
                                this.localResearchPaperDAO.getPaperByID(paperID)
                        );
                    }

                    this.usersDownvotedPapers.put(username, upvotedPapers);
                }
            }
        }
    }

    public List<ResearchPaper> getDownvotedPapers(String username) {
        return this.usersDownvotedPapers.containsKey(username) ?
                this.usersDownvotedPapers.get(username) : new ArrayList<>();
    }

    public boolean saveDownvotedPaper(String username, ResearchPaper paper) {
        if (this.usersDownvotedPapers.containsKey(username)) {
            this.usersDownvotedPapers.get(username).add(paper);
            return true;
        } else {
            return false;
        }
    }

    public void writeToDatabase(String username, List<String> downvotedPaperIDs) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.downvotedPapersCSVFile));

            StringBuilder mutableDownvotedPaperIDsStringRep = new StringBuilder();
            for (String paperID : downvotedPaperIDs) {
                mutableDownvotedPaperIDsStringRep.append(paperID).append(" ");
            }

            String line = String.format("%s,%s", username, mutableDownvotedPaperIDsStringRep);
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
            writer = new BufferedWriter(new FileWriter(this.downvotedPapersCSVFile));
            writer.write(String.join(",", this.usersDownvotedPapersCSVFileHeader.keySet()));
            writer.newLine();

            for (String username : this.usersDownvotedPapers.keySet()) {
                StringBuilder mutableDownvotedPapersStringRep = new StringBuilder();
                for (ResearchPaper upvotedPaper : this.usersDownvotedPapers.get(username)) {
                    mutableDownvotedPapersStringRep.append(upvotedPaper.getID()).append(" ");
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
