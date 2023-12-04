package use_cases.vote;

import entities.Author;
import entities.Category;
import entities.ResearchPaper;
import entities.User;
import use_cases.save.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class VoteUseCaseTest {
    User testUser1;
    User testUser2;
    ResearchPaper testPaper1;
    ResearchPaper testPaper2;

    @org.junit.Test
    public void VoteUseCaseTest() {
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
        testPaper2 = new ResearchPaper("2", "title1", categories, authors,
                LocalDate.now(), "", "", 23, 9);

        VoteInteractor interactor = new VoteInteractor(new testDAO(), new testPresenter());

        interactor.execute(new VoteInputData("username1", "1", true));
        interactor.execute(new VoteInputData("username2", "2", false));
        assert(testUser1.getUpvotedPapers().containsKey("1"));
        assert(testPaper1.getUpvoteCount() == 11);
        assert(testUser2.getDownvotedPapers().containsKey("2"));
        assert(testPaper2.getDownvoteCount() == 10);
    }

    private class testPresenter implements VoteOutputBoundary {
        @Override
        public void prepareSuccessView(VoteOutputData voteOutputData) {

        }

        @Override
        public void prepareFailView(String error) {

        }
    }

    private class testDAO implements VoteDataAccessInterface {

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
    }
}
