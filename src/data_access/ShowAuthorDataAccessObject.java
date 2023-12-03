package data_access;

import entities.Author;
import entities.ResearchPaper;
import use_cases.showAuthor.ShowAuthorDataAccessInterface;

import java.util.List;

public class ShowAuthorDataAccessObject implements ShowAuthorDataAccessInterface {

    private final ArxivDataAccessObject arxivDAO;
    public ShowAuthorDataAccessObject(ArxivDataAccessObject arxivDAO) {
        this.arxivDAO = arxivDAO;
    }

    @Override
    public List<ResearchPaper> getPapersByAuthor(Author author) {
        return this.arxivDAO.getPapersByAuthor(author);
    }
}
