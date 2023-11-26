package entities;

public class AuthorFactory {
    public Author createWithAffiliation(String name, String affiliation) {
        return new Author(name, affiliation);
    }

    public Author createWithoutAffiliation(String name) {
        return new Author(name);
    }

}
