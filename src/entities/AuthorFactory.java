package entities;

public class AuthorFactory {
    Author createWithAffiliation(String name, String affiliation) {
        return new Author(name, affiliation);
    }

    Author createWithoutAffiliation(String name) {
        return new Author(name);
    }

}
