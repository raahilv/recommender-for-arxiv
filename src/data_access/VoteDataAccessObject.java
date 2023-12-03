package data_access;

import entities.ResearchPaper;
import entities.User;
import use_cases.vote.VoteDataAccessInterface;

public class VoteDataAccessObject implements VoteDataAccessInterface {

    private final LocalUserDataAccessObject uDAO;
    private final ArxivDataAccessObject arxivDAO;

    public VoteDataAccessObject(LocalUserDataAccessObject uDAO, ArxivDataAccessObject arxivDAO) {
        this.uDAO = uDAO;
        this.arxivDAO = arxivDAO;
    }


    @Override
    public User getUser(String username) {
        return this.uDAO.getUser(username);
    }

    @Override
    public ResearchPaper getPaper(String paperName) {
        return this.arxivDAO.getPaperByTitle(paperName);  // TODO: ask zherui: is this get paper by title?
    }
}
