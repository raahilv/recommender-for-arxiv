package data_access;

import entities.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class LocalDataAccessObject {
    private final File papersCsvFile;
    private final Map<String, Integer> papersCsvFileHeader = new HashMap<>();
    private final Map<String, ResearchPaper> papers = new HashMap<>();
    private final AuthorFactory authorFactory;
    private final CategoryFactory categoryFactory;
    private final ResearchPaperFactory researchPaperFactory;

    public LocalDataAccessObject(String filepath,
                                 AuthorFactory authorFactory,
                                 CategoryFactory categoryFactory,
                                 ResearchPaperFactory researchPaperFactory) throws IOException {
        this.authorFactory = authorFactory;
        this.categoryFactory = categoryFactory;
        this.researchPaperFactory = researchPaperFactory;

        this.papersCsvFile = new File(filepath);
        this.papersCsvFileHeader.put("id", 0);
        this.papersCsvFileHeader.put("title", 1);
        this.papersCsvFileHeader.put("categories", 2);
        this.papersCsvFileHeader.put("authors", 3);
        this.papersCsvFileHeader.put("publish_date", 4);
        this.papersCsvFileHeader.put("abstract", 5);
        this.papersCsvFileHeader.put("journal_reference", 6);
        this.papersCsvFileHeader.put("url", 7);
        this.papersCsvFileHeader.put("upvote_count", 8);
        this.papersCsvFileHeader.put("downvote_count", 9);

        if (this.papersCsvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.papersCsvFile))) {
                String header = reader.readLine();

                assert header.equals("id,title,categories,authors,publish_date,abstract," +
                        "journal_reference,url,upvote_count,downvote_count");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String id = String.valueOf(col[papersCsvFileHeader.get("id")]);
                    String title = String.valueOf(col[papersCsvFileHeader.get("title")]);
                    String categoriesStringRep =
                            String.valueOf(col[papersCsvFileHeader.get("categories")]);
                    String authorsStringRep =
                            String.valueOf(col[papersCsvFileHeader.get("authors")]);
                    LocalDate publishDate =
                            LocalDate.parse(String.valueOf(col[papersCsvFileHeader.get("publish_date")]));
                    String paperAbstract = String.valueOf(col[papersCsvFileHeader.get("abstract")]);
                    String journalReference = String.valueOf(col[papersCsvFileHeader.get("journal_reference")]);
                    String url = String.valueOf(col[papersCsvFileHeader.get("url")]);
                    long upvoteCount = Long.parseLong(String.valueOf(col[papersCsvFileHeader.get("upvote_count")]));
                    long downvoteCount = Long.parseLong(String.valueOf(col[papersCsvFileHeader.get("downvote_count")]));

                    List<Category> categoriesObjectList = new ArrayList<>();
                    String[] categoriesBreakDown = categoriesStringRep.split(" ");
                    for (String categoryStringRep : categoriesBreakDown) {
                        StringBuilder mutableCategoryStringRep = new StringBuilder(categoryStringRep);
                        mutableCategoryStringRep.deleteCharAt(0);
                        mutableCategoryStringRep.deleteCharAt(categoryStringRep.length() - 1);
                        String mutatedCategoryStringRep = mutableCategoryStringRep.toString();
                        String[] categoryContents = mutatedCategoryStringRep.split("\\|");
                        categoriesObjectList.add(
                                this.categoryFactory.create(Arrays.asList(categoryContents))
                        );
                    }

                    List<Author> authorsObjectList = new ArrayList<>();
                    String[] authorsBreakDown = authorsStringRep.split(" ");
                    for (String authorStringRep : authorsBreakDown) {
                        StringBuilder mutableAuthorStringRep = new StringBuilder(authorStringRep);
                        mutableAuthorStringRep.deleteCharAt(0);
                        mutableAuthorStringRep.deleteCharAt(authorStringRep.length() - 1);
                        String mutatedAuthorStringRep = mutableAuthorStringRep.toString();
                        String[] authorContents = mutatedAuthorStringRep.split("\\|");
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
                    this.papers.put(
                            id, this.researchPaperFactory.create(
                                    id, title, categoriesObjectList, authorsObjectList, publishDate,
                                    paperAbstract, url, upvoteCount, downvoteCount
                            )
                    );
                }
            }
        }
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.papersCsvFile));
            writer.write(String.join(",", this.papersCsvFileHeader.keySet()));
            writer.newLine();

            for (ResearchPaper paper : this.papers.values()) {
                String id = paper.getId();
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
                    categoriesStringRep.append(category.toString());
                }
                StringBuilder authorsStringRep = new StringBuilder();
                for (Author author : authors) {
                    authorsStringRep.append(author.toString());
                }

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

}
