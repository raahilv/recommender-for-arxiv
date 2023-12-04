package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Author {
    private final String name;  // assume this is in lower case
    private String affiliation;  // assume this is in lower case

    public Author(String name) {
        this.name = name;
        this.affiliation = "";
    }

    public Author(String name, String affiliation) {
        this.name = name;
        this.affiliation = affiliation;
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

    public List<String> toList() {
        List<String> authorInfo = new ArrayList<>();
        authorInfo.add(this.name);
        authorInfo.add(this.affiliation);
        return authorInfo;
    }

    public String toString() {
        return !this.affiliation.isEmpty() ?
               "(" + this.name + "|" + this.affiliation + ")" :
               "(" + this.name + "|None)";
    }

}