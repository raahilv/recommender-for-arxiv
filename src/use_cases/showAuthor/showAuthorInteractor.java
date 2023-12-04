package use_cases.showAuthor;

import app.ResearchPaperTransport;
import entities.Author;
import entities.ResearchPaper;

import java.util.ArrayList;
import java.util.List;

public class showAuthorInteractor implements showAuthorInputBoundary{

    final ShowAuthorDataAccessInterface dao;
    final showAuthorOutputBoundary showAuthorPresenter;

    public showAuthorInteractor(showAuthorOutputBoundary showAuthorPresenter, ShowAuthorDataAccessInterface dai) {
        this.showAuthorPresenter = showAuthorPresenter;
        this.dao = dai;
    }

    public void execute(showAuthorInputData inputData) {
        Author author = inputData.getAuthor();
        List<ResearchPaper> papers = dao.getPapersByAuthor(author);
//        if (papers.isEmpty()) {
//            showAuthorPresenter.prepareFailurView("Author has no papers");
//        }
        List<ResearchPaperTransport> transportPapers = new ArrayList<>();
        for (ResearchPaper paper: papers){
            transportPapers.add(
                    new ResearchPaperTransport(paper.getID(), paper.getTitle(), paper.getCategories(), paper.getAuthors(), paper.getPublishDate(), paper.getPaperAbstract(), paper.getJournalReference(), paper.getUrl(), paper.getUpvoteCount(), paper.getDownvoteCount() ));
        }
//        if (transportPapers.size() > 0) {
            showAuthorOutputData output = new showAuthorOutputData(author, transportPapers, false);
            showAuthorPresenter.prepareSuccessView(output);

    }
}
