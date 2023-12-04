package use_cases.save;

import entities.Author;
import entities.Category;
import entities.ResearchPaper;
import entities.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class SaveUseCaseTest {
    User testUser1;
    User testUser2;
    ResearchPaper testPaper1;
    ResearchPaper testPaper2;

    @org.junit.Test
    public void SaveUseCaseTest() {
        testUser1 = new User("username1", "password");
        testUser2 = new User("username2", "password");
        Category testCategory = new Category("cs", "cs", "cs");
        Author testAuthor = new Author("Zhang");
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Author> authors = new ArrayList<>();
        categories.add(testCategory);
        authors.add(testAuthor);
        testPaper1 = new ResearchPaper("1", "title1", categories, authors,
                LocalDate.now(), "", "", 10, 5);
        testPaper2 = new ResearchPaper("1", "title1", categories, authors,
                LocalDate.now(), "", "", 23, 9);

        SaveInteractor interactor = new SaveInteractor(new testDAO(), new testPresenter());
        assert(!testUser1.getLibrary().containsKey("1"));
        interactor.execute(new SaveInputData("username1", "1"));
        assert(testUser1.getLibrary().containsKey("1"));
    }

    private class testPresenter implements SaveOutputBoundary {

        @Override
        public void prepareSuccessView(SaveOutputData saveOutputData) {

        }

        @Override
        public void prepareFailView(String error) {

        }
    }

    private class testDAO implements SaveDataAccessInterface {

        @Override
        public User getUser(String username) {
            if (username.equals("username1")) {
                return testUser1;
            } else {
                return testUser2;
            }
        }

        @Override
        public ResearchPaper getPaper(String paperID) {
            if (paperID.equals("1")) {
                return testPaper1;
            } else {
                return testPaper2;
            }
        }

        @Override
        public void save(String username, ResearchPaper paper) {
            User user = this.getUser(username);
            user.savePaperIntoLibrary(paper);
        }
    }
}
