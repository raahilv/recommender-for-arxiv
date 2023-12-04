package use_cases.showAuthor;

import entities.Author;
import entities.ResearchPaper;

import java.util.List;

public interface ShowAuthorDataAccessInterface {
    List<ResearchPaper> getPapersByAuthor(Author author);

}
