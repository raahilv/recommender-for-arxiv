package data_access;
import data_access.ArxivDataAccessObject;
import entities.AuthorFactory;
import entities.Category;
import entities.CategoryFactory;
import entities.ResearchPaper;
import entities.Author;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
public class ArxivDataAccessObjectTest {
    CategoryFactory CF = new CategoryFactory();
    List<Category> categories = new ArrayList<>(Arrays.asList(new Category[]{CF.create(List.of("cs", "CR", "Cryptography and Security")), CF.create(List.of("cs", "FL", "Formal Languages and Automata Theory"))}));
    Category CR = CF.create(List.of("cs", "CR", "Cryptography and Security"));
//    Category FL = CF.create(List.of("cs", "FL", "Formal Languages and Automata Theory"));
    AuthorFactory AF = new AuthorFactory();
    List<Author> authors = new ArrayList<>(Arrays.asList(new Author[]{AF.createWithoutAffiliation("Roberto Metere"),AF.createWithoutAffiliation("Changyu Dong")}));
    Author Roberto = AF.createWithoutAffiliation("Roberto Metere");
    Author Changyu = AF.createWithoutAffiliation("Changyu Dong");
    String summary = "  Programs using random values can either make all choices in advance (eagerly) or sample as needed (lazily). In formal proofs, we focus on indistinguishability between two lazy programs, a common requirement in the random oracle model (ROM). While rearranging sampling instructions often solves this, it gets complex when sampling is spread across procedures. The traditional approach, introduced by Bellare and Rogaway in 2004, converts programs to eager sampling, but requires assuming finite memory, a polynomial bound, and artificial resampling functions. We introduce a novel approach in probabilistic Relational Hoare Logic (pRHL) that directly proves indistinguishability, eliminating the need for conversions and the mentioned assumptions. We also implement this approach in the EasyCrypt theorem prover, showing that it can be a convenient alternative to the traditional method. ";
    ResearchPaper paper = new ResearchPaper("2311.16844", "A Direct Lazy Sampling Proof Technique in Probabilistic Relational Hoare  Logic", categories, authors, LocalDate.parse("2023-11-28"), summary, "http://arxiv.org/abs/2311.16844", 0, 0);
    ArxivDataAccessObject arxdao = new ArxivDataAccessObject(categories, authors);
    @org.junit.Test
    public void testGetPaperById(){
        ResearchPaper paper2 = arxdao.getPaperByID("2311.16844");
        assert(paper.toString().equals(paper2.toString()));
//        assert(arePapersSame(paper, paper2));
    }
    @org.junit.Test
    public void testGetPaperByTitle(){
        ResearchPaper paper2 = arxdao.getPaperByTitle("A Direct Lazy Sampling Proof Technique in Probabilistic Relational Hoare  Logic");
        assert(paper.toString().equals(paper2.toString()));
    }
    @org.junit.Test
    public void testNumOfGetIDsbyAuthor(){
        List<String> ids = arxdao.getIDsByAuthor(Changyu);
        assert(ids.size() == 6);
    }

    @org.junit.Test
    public void testNumOfGetPapersbyAuthor(){
        List<ResearchPaper> papers = arxdao.getPapersByAuthor(Changyu);
        assert(papers.size() == 6);
    }

    @org.junit.Test
    public void testDoesFilterPapersByRootFilter(){
        List<String> papers = arxdao.filterPapersByRootCategory("cs.fl");
        int start = (int) (Math.random() * 45);
        for (int i = 0; i < 5; i++) {
            boolean cat_in = false;
            List<Category> paper_cats = arxdao.getPaperByID(papers.get(start + i)).getCategories();
            for (Category category: paper_cats) {
                if (category.getRootCategory().equals("cs")){cat_in = true;}
            }
            assert(cat_in);
        }
    }
//    @org.junit.Test
//    public void testNameOfPaperByJournalReference(){
//        List<ResearchPaper> papers = arxdao.getPaperByJournalReference("Logical Methods in Computer Science, Volume 9, Issue 4 (December 11, 2013)");
//        for (ResearchPaper paper : papers) {
//            assert(paper.getTitle().equals("Nominal Coalgebraic Data Types with Applications to Lambda Calculus") || paper.getTitle().equals("Compact manifolds with computable boundaries "));
//        }
//    }


    private boolean arePapersSame(ResearchPaper paper1, ResearchPaper paper2){
        if (!paper1.getID().equals(paper2.getID())) {
            return false;
        }
        if (!paper1.getTitle().equals(paper2.getTitle())) {
            return false;
        }
        if (!paper1.getPaperAbstract().equals(paper2.getPaperAbstract())){
            return false;
        }
        if (!paper1.getUrl().equals(paper2.getUrl())){
            return false;
        }
        if (!paper1.getPublishDate().equals(paper2.getPublishDate())){
            return false;
        }

        for (Category category1 : paper1.getCategories()){
            boolean found = false;
            for (Category category2 : paper2.getCategories()){
                if(category2.isSame(category1)) {
                    found = true;
                }
            }
            if (!found) {return false;}

        }
        for (Category category2 : paper2.getCategories()){
            boolean found = false;
            for (Category category1 : paper1.getCategories()){
                if(category2.isSame(category1)) {
                    found = true;
                }
            }
            if (!found) {return false;}
        }

        return true;
    }
}
