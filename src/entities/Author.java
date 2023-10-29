package entities;

public class Author {
    private String authorId;
    private String name;  // assume this is in lower case
    private String affiliation;  // assume this is in lower case

    public Author(String authorId, String name, String affiliation) {
        this.authorId = authorId;
        this.name = name;
        this.affiliation = affiliation;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getName() {
        return this.name;
    }

    public String getAffiliation() {
        return this.affiliation;
    }

}
