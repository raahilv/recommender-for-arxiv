package use_cases.showAuthor;

import entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowAuthorInteractorTest{
    Author a1 = new Author("Zhipp Canuff");
    Author a2= new Author("Mnji Aito");
    showAuthorInputData inputData = new showAuthorInputData(a1);
    showAuthorInputBoundary interactor = new showAuthorInteractor(new Presenter(), new testDAO());
    showAuthorOutputData testData;
//    String errorString;
    CategoryFactory CF = new CategoryFactory();
    Category GN = CF.create(List.of("econ", "GN", "General Economics"));
    Category AP = CF.create(List.of("stat", "AP", "Applications"));
    Category SP = CF.create(List.of("math", "SP", "Spectral Theory"));
    List<Category> categories = new ArrayList<>(Arrays.asList(new Category[]{GN, AP, SP}));
    List<Author> lista1 = new ArrayList<>(Arrays.asList(new Author[]{a1}));

    @org.junit.Test
    public void testSuccessView(){
        interactor.execute(inputData);
        assert(testData.getAuthorName().equals("Zhipp Canuff"));
        assert(testData.getAverageUpvotes() == 10.0);
        assert(testData.getTotalDownvotes() == 23);
        assert(testData.getTotalUpvotes() == 43);
        assert(testData.numPapersInCategory().get(AP) == 2);
        assert(testData.getPapers().get(0).getId().equals("1234"));
    }
//    @org.junit.Test
//    public void testFailureView(){
//        interactor.execute(new showAuthorInputData(a2));
//
//    }

    private class Presenter implements showAuthorOutputBoundary{

        @Override
        public void prepareSuccessView(showAuthorOutputData response) {
            testData = response;
        }

//        @Override
//        public void prepareFailurView(String error) {
//            errorString = error;
//        }
    }
    private class testDAO implements ShowAuthorDataAccessInterface{

        @Override
        public List<ResearchPaper> getPapersByAuthor(Author author) {
            ResearchPaperFactory RPFactory = new ResearchPaperFactory();
            List<Category> cats = new ArrayList<>(Arrays.asList(new Category[]{GN, AP}));
            ResearchPaper paper = RPFactory.createWithoutJournalReference("1234", "Investigating the economic system of the Kingdom of Zhipp Canuff", cats , lista1, LocalDate.parse("2023-11-28"), "look at title", "http://arxiv.org/abs/1234", 43, 23);
            ResearchPaper paper2 = RPFactory.createWithoutJournalReference("5678", "Census of of the Kingdom of Zhipp Canuff", cats , lista1, LocalDate.parse("2023-11-28"), "look at title", "http://arxiv.org/abs/5678", 0, 0);
            List<ResearchPaper> testPapers = new ArrayList<>();
            if (author.getName().equals("Zhipp Canuff")){
                testPapers.add(paper);
                testPapers.add(paper2);
            }
            return testPapers;
        }
    }

}