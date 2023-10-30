package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResearchPaper {
    private final String id;  // string of digits (possibly with punctuations)
    private final String title;  // assume it is in lower case
    private final Category category;
    private final HashMap<String, Author> authors = new HashMap<>();
    private final LocalDate publishDate;
    private final String paperAbstract;
    private final String journalReference;  // assume it is in lower case
    private final String url;
    private long upvoteCount;
    private long downvoteCount;

    public ResearchPaper(String id, String title, Category category, HashMap<String, Author> authors,
                         LocalDate publishDate, String paperAbstract, String journalReference,
                         String url, long upvoteCount, long downvoteCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.publishDate = publishDate;
        this.paperAbstract = paperAbstract;
        this.journalReference = journalReference;
        this.url = url;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;
        this.authors.putAll(authors);
    }

    public String getId() { return this.id; }

    public String getTitle() { return this.title; }

    public boolean hasAuthor(String authorId) {
        return this.authors.containsKey(authorId);
    }

    public String getPaperAbstract() { return this.paperAbstract; }

    public String getUrl() { return this.url; }

    public long getUpvoteCount() { return this.upvoteCount; }

    public void setUpvoteCount(int upvoteCount) { this.upvoteCount = upvoteCount; }

    public long getDownvoteCount() { return this.downvoteCount; }

    public void setDownvoteCount(int downvoteCount) { this.downvoteCount = downvoteCount; }

    public List<Object> toList() {
        List<Object> paperMetadata = new ArrayList<>();
        paperMetadata.add(this.id);
        paperMetadata.add(this.title);
        paperMetadata.add(this.category);
        paperMetadata.add(this.publishDate);
        paperMetadata.add(this.paperAbstract);
        paperMetadata.add(this.journalReference);
        paperMetadata.add(this.url);
        paperMetadata.add(this.upvoteCount);
        paperMetadata.add(this.downvoteCount);

        List<Object> authors = new ArrayList<>();
        for (String authorId : this.authors.keySet()) {
            authors.add(this.authors.get(authorId).toList());
        }
        paperMetadata.add(authors);

        return paperMetadata;
//        this.id = id;
//        this.title = title;
//        this.category = category;
//        this.publishDate = publishDate;
//        this.paperAbstract = paperAbstract;
//        this.journalReference = journalReference;
//        this.url = url;
//        this.upvoteCount = upvoteCount;
//        this.downvoteCount = downvoteCount;
//        this.authors.putAll(authors);

    }
}