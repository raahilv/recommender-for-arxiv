package use_cases.showAuthor;

import entities.Author;
import entities.ResearchPaper;

import java.util.List;

public interface showAuthorDataAccessInterface {
    List<ResearchPaper> getPapersbyAuthor(Author author);

}
