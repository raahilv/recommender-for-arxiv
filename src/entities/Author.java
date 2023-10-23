package entities;

public class Author {
    private String authorId;
    private String name;
    private String institution;

    public Author(String authorId, String name, String institution) {
        this.authorId = authorId;
        this.name = name;
        this.institution = institution;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getName() {
        return this.name;
    }

    public String getInstitution() {
        return this.institution;
    }

}
