package use_cases.showAuthor;

import entities.Author;
import entities.ResearchPaper;

import java.util.ArrayList;
import java.util.List;

public class showAuthorInteractor implements showAuthorInputBoundary{

    final showAuthorDataAccessInterface dao;
    final showAuthorOutputBoundary showAuthorPresenter;

    public showAuthorInteractor(showAuthorOutputBoundary showAuthorPresenter, showAuthorDataAccessInterface dai) {
        this.showAuthorPresenter = showAuthorPresenter;
        this.dao = dai;
    }

    public void execute(showAuthorInputData inputData) {
        Author author = inputData.getAuthor();
        List<ResearchPaper> papers = dao.getPapersbyAuthor(author);
        if (papers.isEmpty()) {
            showAuthorPresenter.prepareFailurView("Author has no papers");
        }
        showAuthorOutputData output = new showAuthorOutputData(author, papers, false);
        showAuthorPresenter.prepareSuccessView(output);

    }
}
