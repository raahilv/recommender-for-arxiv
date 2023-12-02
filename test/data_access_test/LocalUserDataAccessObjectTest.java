package data_access_test;

import data_access.*;
import entities.*;
import org.junit.Test;

import java.io.IOException;

public class LocalUserDataAccessObjectTest {

    private final static String userCSVFilePath = "test/users.csv";
    private final AuthorFactory af = new AuthorFactory();
    private final CategoryFactory cf = new CategoryFactory();
    private final ResearchPaperFactory rpf = new ResearchPaperFactory();
    private final UserFactory uf = new CommonUserFactory();
    private final LocalResearchPaperDataAccessObject lrpDAO = new LocalResearchPaperDataAccessObject("test_files/papers.csv", af, cf, rpf);
    private final LocalLibraryDataAccessObject llDAO = new LocalLibraryDataAccessObject("test_files/library.csv", lrpDAO);
    private final LocalUpvotedPapersDataAccessObject lupDAO = new LocalUpvotedPapersDataAccessObject("test_files/upvotedPapers.csv", lrpDAO);
    private final LocalDownvotedPapersDataAccessObject ldpDAO = new LocalDownvotedPapersDataAccessObject("test_files/downvotedPapers.csv", lrpDAO);
    private final LocalPreferredCategoriesDataAccessObject lpcDAO = new LocalPreferredCategoriesDataAccessObject("test_files/categories.csv", cf);

    private final LocalUserDataAccessObject luDAO = new LocalUserDataAccessObject(llDAO, lupDAO, ldpDAO, lpcDAO, userCSVFilePath, uf);

    public LocalUserDataAccessObjectTest() throws IOException {

    }
    @Test
    public void testGetUser() {
        String username = "kevin";
        User user = luDAO.getUser(username);
        System.out.println(user.getUsername());
        assert(sameUser(user.getUsername(), username));
    }

    private boolean sameUser(String username1, String username2) {
        return username1.equals(username2);
    }

}
