package interface_adapters.showAuthor;

import entities.Author;
import use_cases.showAuthor.showAuthorInputBoundary;
import use_cases.showAuthor.showAuthorInputData;

public class showAuthorController {
    final showAuthorInputBoundary showAuthorUseCaseInteractor;
    public showAuthorController(showAuthorInputBoundary showAuthorUseCaseInteractor) {

        this.showAuthorUseCaseInteractor = showAuthorUseCaseInteractor;
    }
    public void execute(Author author){
        showAuthorInputData input = new showAuthorInputData(author);
        showAuthorUseCaseInteractor.execute(input);
    }
}
