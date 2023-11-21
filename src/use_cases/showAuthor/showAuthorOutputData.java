package use_cases.showAuthor;

import app.ResearchPaperTransport;
import entities.Author;
import entities.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class showAuthorOutputData {
    private final Author author;
    private final List<ResearchPaperTransport> papers;
    private boolean useCaseFailed;


    public showAuthorOutputData(Author author, List<ResearchPaperTransport> papers, boolean useCaseFailed) {
        this.author = author;
        this.papers = papers;
        this.useCaseFailed = useCaseFailed;
    }
    public String getAuthorName() {return author.getName();}
    public List<ResearchPaperTransport> getPapers() {return papers;}
    public Integer getTotalUpvotes() {
        int sum = 0;
        for(ResearchPaperTransport paper : papers) {
            sum += paper.getUpvoteCount();
        }
        return sum;
    }
    public Integer getTotalDownvotes() {
        int sum = 0;
        for(ResearchPaperTransport paper : papers) {
            sum += paper.getDownvoteCount();
        }
        return sum;
    }
    public double getAverageUpvotes() {
        return (double) (getTotalUpvotes() - getTotalDownvotes()) / papers.size();
    }
    public Map<Category, Integer> numPapersInCategory() {
        HashMap<Category, Integer> map = new HashMap<>();
        for(ResearchPaperTransport paper: papers) {
            for (Category category: paper.getCategories()){
                if (! map.containsKey(category)) {
                    map.put(category, 1);
                }
                else {
                    map.put(category, map.get(category) + 1);
                }
            }
        }
        return map;
    }

}
