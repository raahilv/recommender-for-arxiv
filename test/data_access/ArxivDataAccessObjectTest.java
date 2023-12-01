package test.data_access;
import data_access.ArxivDataAccessObject;
import entities.AuthorFactory;
import entities.Category;
import entities.CategoryFactory;
import entities.ResearchPaper;
import entities.Author;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
public class ArxivDataAccessObjectTest {
    CategoryFactory CF = new CategoryFactory();
    List<Category> categories = new ArrayList<>(Arrays.asList(new Category[]{CF.create(List.of("cs", "CR", "Cryptography and Security")), CF.create(List.of("cs", "FL", "Formal Languages and Automata Theory"))}));
//    Category CR = CF.create(List.of("cs", "CR", "Cryptography and Security"));
//    Category FL = CF.create(List.of("cs", "FL", "Formal Languages and Automata Theory"));
    AuthorFactory AF = new AuthorFactory();
    List<Author> authors = new ArrayList<>(Arrays.asList(new Author[]{AF.createWithoutAffiliation("Roberto Metere"),AF.createWithoutAffiliation("Changyu Dong")}));
//    Author Roberto = AF.createWithoutAffiliation("Roberto Metere");
//    Author Changyu = AF.createWithoutAffiliation("Changyu Dong");
    String summary = "Programs using random values can either make all choices in advance (eagerly) or sample as needed (lazily). In formal proofs, we focus on indistinguishability between two lazy programs, a common requirement in the random oracle model (ROM). While rearranging sampling instructions often solves this, it gets complex when sampling is spread across procedures. The traditional approach, introduced by Bellare and Rogaway in 2004, converts programs to eager sampling, but requires assuming finite memory, a polynomial bound, and artificial resampling functions. We introduce a novel approach in probabilistic Relational Hoare Logic (pRHL) that directly proves indistinguishability, eliminating the need for conversions and the mentioned assumptions. We also implement this approach in the EasyCrypt theorem prover, showing that it can be a convenient alternative to the traditional method. ";
    ResearchPaper paper = new ResearchPaper("2311.16844v1", "A Direct Lazy Sampling Proof Technique in Probabilistic Relational Hoare  Logic", categories, authors, LocalDate.parse("2023-11-28"), summary, "https://arxiv.org/abs/2311.16844", 0, 0);
    ArxivDataAccessObject arxdao = new ArxivDataAccessObject(categories, authors);
    @org.junit.Test
    public void testGetPaperById(){
        ResearchPaper paper2 = arxdao.getPaperByID("2311.16844");
        assert(arePapersSame(paper2, paper));
    }
    private boolean arePapersSame(ResearchPaper paper1, ResearchPaper paper2){
        boolean same = true;
        if (!paper1.getID().equals(paper2.getID()) || !paper1.getTitle().equals(paper2.getTitle())) {
            same = false;

        }
        return same;
    }
}
