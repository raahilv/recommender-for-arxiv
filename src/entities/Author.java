package entities;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private final String authorId;
    private final String name;  // assume this is in lower case
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

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public List<Object> toList() {
        List<Object> authorMetadata = new ArrayList<>();
        authorMetadata.add(this.authorId);
        authorMetadata.add(this.name);
        authorMetadata.add(this.affiliation);
        return authorMetadata;
    }

}
