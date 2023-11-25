package use_cases.showAuthor;

import entities.Author;

import java.util.List;

public interface showAuthorOutputBoundary {
    void prepareSuccessView(showAuthorOutputData response);
    void prepareFailurView(String error);

}
