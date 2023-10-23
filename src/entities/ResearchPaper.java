package entities;

import java.time.LocalDate;
import java.util.HashMap;

public class ResearchPaper {
    private final String id;
    private final HashMap<String, Author> authors = new HashMap<String, Author>();
    private final LocalDate publishDate;
    private final String paperAbstract;
    private final String url;
    private long upvoteCount;
    private long downvoteCount;

    public ResearchPaper(String id, HashMap<String, Author> authors,
                         LocalDate publishDate, String paperAbstract,
                         String url, long upvoteCount, long downvoteCount) {
        this.id = id;
        this.publishDate = publishDate;
        this.paperAbstract = paperAbstract;
        this.url = url;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;

        for (String authorId : authors.keySet()) {
            this.authors.put(authorId, authors.get(authorId));
        }
    }

    public String getId() { return this.id; }

    public boolean hasAuthor(String authorId) {
        return this.authors.containsKey(authorId);
    }

    public String getPaperAbstract() { return this.paperAbstract; }

    public String getUrl() { return this.url; }

    public long getUpvoteCount() { return this.upvoteCount; }

    public void setUpvoteCount(int upvoteCount) { this.upvoteCount = upvoteCount; }

    public long getDownvoteCount() { return this.downvoteCount; }

    public void setDownvoteCount(int downvoteCount) { this.downvoteCount = downvoteCount; }
}