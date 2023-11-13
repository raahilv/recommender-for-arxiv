package use_cases.showAuthor;

import entities.Author;
import entities.ResearchPaper;

import java.util.List;

public class showAuthorInteractor implements showAuthorInputBoundary{
    final List<ResearchPaper> papers;
    final showAuthorDataAccessInterface dao;
    final Author author;

    public showAuthorInteractor(showAuthorInputData inputData, showAuthorDataAccessInterface dai) {
        this.author = inputData.getAuthor();
        this.dao = dai;
    }

    @Override
    public void showAuthor(showAuthorInputData inputData) {

    }
}
