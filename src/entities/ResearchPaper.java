package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResearchPaper {
    private final String id;  // string of digits (possibly with punctuations)
    private final String title;
    private final List<Category> categories = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();
    private final LocalDate publishDate;
    private final String paperAbstract;
    private final String journalReference;  // optional
    private final String url;
    private long upvoteCount;
    private long downvoteCount;

    public ResearchPaper(String id, String title, List<Category> categories, List<Author> authors,
                         LocalDate publishDate, String paperAbstract, String url, long upvoteCount, long downvoteCount) {
        this.id = id;
        this.title = title;
        this.categories.addAll(categories);
        this.authors.addAll(authors);
        this.publishDate = publishDate;
        this.paperAbstract = paperAbstract;
        this.journalReference = null;
        this.url = url;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;
    }

    public ResearchPaper(String id, String title, List<Category> categories, List<Author> authors,
                         LocalDate publishDate, String paperAbstract, String journalReference,
                         String url, long upvoteCount, long downvoteCount) {
        this.id = id;
        this.title = title;
        this.categories.addAll(categories);
        this.authors.addAll(authors);
        this.publishDate = publishDate;
        this.paperAbstract = paperAbstract;
        this.journalReference = journalReference;
        this.url = url;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;
    }

    public String getId() { return this.id; }

    public String getTitle() { return this.title; }

    public boolean belongsToRootCategory(String rootCategory) {
        for (Category category : this.categories) {
            if (category.getRootCategory().equalsIgnoreCase(rootCategory)) {
                return true;
            }
        }
        return false;
    }

    public List<Category> getCategories() {
        return this.categories;
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
        paperMetadata.add(this.categories);
        paperMetadata.add(this.publishDate);
        paperMetadata.add(this.paperAbstract);
        paperMetadata.add(this.journalReference);
        paperMetadata.add(this.url);
        paperMetadata.add(this.upvoteCount);
        paperMetadata.add(this.downvoteCount);;
        paperMetadata.add(this.authors);

        return paperMetadata;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public LocalDate getPublishDate() { return this.publishDate; }

    public String getJournalReference() { return this.journalReference;}
}