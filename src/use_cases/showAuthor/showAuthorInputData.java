package use_cases.showAuthor;

import entities.Author;

public class showAuthorInputData {
    final private Author author;
    public showAuthorInputData(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }
}
