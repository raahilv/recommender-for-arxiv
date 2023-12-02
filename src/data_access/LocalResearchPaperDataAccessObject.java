package data_access;

import entities.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class LocalResearchPaperDataAccessObject {

    private final File papersCSVFile;
    private final Map<String, Integer> papersCSVFileHeader = new LinkedHashMap<>();
    private final Map<String, ResearchPaper> papers = new HashMap<>();
    private final AuthorFactory authorFactory;
    private final CategoryFactory categoryFactory;
    private final ResearchPaperFactory researchPaperFactory;


    public LocalResearchPaperDataAccessObject(String filepath,
                                              AuthorFactory authorFactory,
                                              CategoryFactory categoryFactory,
                                              ResearchPaperFactory researchPaperFactory) throws IOException {
        this.authorFactory = authorFactory;
        this.categoryFactory = categoryFactory;
        this.researchPaperFactory = researchPaperFactory;

        this.papersCSVFile = new File(filepath);
        this.papersCSVFileHeader.put("id", 0);
        this.papersCSVFileHeader.put("title", 1);
        this.papersCSVFileHeader.put("categories", 2);
        this.papersCSVFileHeader.put("authors", 3);
        this.papersCSVFileHeader.put("publish_date", 4);
        this.papersCSVFileHeader.put("abstract", 5);
        this.papersCSVFileHeader.put("journal_reference", 6);
        this.papersCSVFileHeader.put("url", 7);
        this.papersCSVFileHeader.put("upvote_count", 8);
        this.papersCSVFileHeader.put("downvote_count", 9);

        if (this.papersCSVFile.length() == 0) {
            writeToDatabase();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.papersCSVFile))) {
                String header = reader.readLine();

                assert header.equals("id,title,categories,authors,publish_date,abstract,journal_reference,url,upvote_count,downvote_count");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String id = String.valueOf(col[papersCSVFileHeader.get("id")]);
                    String title = String.valueOf(col[papersCSVFileHeader.get("title")]);
                    String categoriesStringRep =
                            String.valueOf(col[papersCSVFileHeader.get("categories")]);
                    String authorsStringRep =
                            String.valueOf(col[papersCSVFileHeader.get("authors")]);
                    LocalDate publishDate =
                            LocalDate.parse(String.valueOf(col[papersCSVFileHeader.get("publish_date")]));
                    String paperAbstract = String.valueOf(col[papersCSVFileHeader.get("abstract")]);
                    String journalReference = String.valueOf(col[papersCSVFileHeader.get("journal_reference")]);
                    String url = String.valueOf(col[papersCSVFileHeader.get("url")]);
                    long upvoteCount = Long.parseLong(String.valueOf(col[papersCSVFileHeader.get("upvote_count")]));
                    long downvoteCount = Long.parseLong(String.valueOf(col[papersCSVFileHeader.get("downvote_count")]));

                    List<Category> categoriesObjectList = new ArrayList<>();
                    String[] categoriesBreakDown = categoriesStringRep.split(" ");
                    for (String categoryStringRep : categoriesBreakDown) {
                        String[] categoryContents = categoryStringRep.split("\\|");
                        categoriesObjectList.add(
                                this.categoryFactory.create(Arrays.asList(categoryContents))
                        );
                    }

                    List<Author> authorsObjectList = new ArrayList<>();
                    String[] authorsBreakDown = authorsStringRep.split(" ");
                    for (String authorStringRep : authorsBreakDown) {
                        String[] authorContents = authorStringRep.split("\\|");
                        if (authorContents[1].equals("None")) {
                            authorsObjectList.add(
                                    this.authorFactory.createWithAffiliation(authorContents[0], authorContents[1])
                            );
                        } else {
                            authorsObjectList.add(
                                    this.authorFactory.createWithoutAffiliation(authorContents[0])
                            );
                        }
                    }
                    if (journalReference.equalsIgnoreCase("!NO_JOUR_REF!")) {
                        this.papers.put(
                                id, this.researchPaperFactory.createWithoutJournalReference(
                                        id, title, categoriesObjectList, authorsObjectList, publishDate,
                                        paperAbstract, url, upvoteCount, downvoteCount
                                )
                        );
                    } else {
                        this.papers.put(
                                id, this.researchPaperFactory.createWithJournalReference(
                                        id, title, categoriesObjectList, authorsObjectList, publishDate,
                                        paperAbstract, journalReference, url, upvoteCount, downvoteCount
                                )
                        );
                    }
                }
            }
        }
    }

    public void writeToDatabase() {
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(this.papersCSVFile));
            writer.write(String.join(",", this.papersCSVFileHeader.keySet()));
            writer.newLine();

            for (ResearchPaper paper : this.papers.values()) {
                String id = paper.getID();
                String title = paper.getTitle();
                List<Category> categories = paper.getCategories();
                List<Author> authors = paper.getAuthors();
                LocalDate publishDate = paper.getPublishDate();
                String paperAbstract = paper.getPaperAbstract();
                String journalReference = paper.getJournalReference();
                String url = paper.getUrl();
                long upvoteCount = paper.getUpvoteCount();
                long downvoteCount = paper.getDownvoteCount();

                StringBuilder categoriesStringRep = new StringBuilder();
                for (Category category : categories) {
                    categoriesStringRep.append(category.toString()).append(" ");
                }
                categoriesStringRep.deleteCharAt(categoriesStringRep.length() - 1);

                StringBuilder authorsStringRep = new StringBuilder();
                for (Author author : authors) {
                    authorsStringRep.append(author.toString()).append(" ");
                }
                authorsStringRep.deleteCharAt(authorsStringRep.length() - 1);

                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        id, title, categories, categoriesStringRep, authorsStringRep,
                        publishDate.toString(), paperAbstract, journalReference, url,
                        upvoteCount, downvoteCount);
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResearchPaper getPaperByID(String paperID) {
        for (String potentialPaperID : this.papers.keySet()) {
            if (paperID.equals(potentialPaperID)) {
                return this.papers.get(potentialPaperID);
            }
        }
        return null;
    }

    /* For testing... */
    public Map<String, Integer> getPapersCSVFileHeader() {
        return this.papersCSVFileHeader;
    }

//    public Map<String, ResearchPaper> getPapers() {
//        return this.papers;
//    }
//
//    public void setPapers(Map<String, ResearchPaper> papers) {
//        for (String paperID : papers.keySet()) {
//            this.papers.put(paperID, papers.get(paperID));
//        }
//    }
//
//    public void setPapersCSVFile(String filepath) {
//        this.papersCSVFile = new File(filepath);
//    }
}
