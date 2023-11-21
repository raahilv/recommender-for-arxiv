package interface_adapters.showAuthor;

import app.ResearchPaperTransport;
import entities.ResearchPaper;

import java.util.List;

public class showAuthorState {



    private String authorName;
    private List<ResearchPaperTransport> authorPapers;
    private Integer TotalUpvotes;
    private Integer TotalDownvotes;
    private double AverageUpvotes;

    public showAuthorState(String authorName, List<ResearchPaperTransport> authorPapers, Integer totalUpvotes, Integer totalDownvotes, double averageUpvotes) {
        this.authorName = authorName;
        this.authorPapers = authorPapers;
        TotalUpvotes = totalUpvotes;
        TotalDownvotes = totalDownvotes;
        AverageUpvotes = averageUpvotes;
    }
    public showAuthorState() {
    }
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<ResearchPaperTransport> getAuthorPapers() {
        return authorPapers;
    }

    public void setAuthorPapers(List<ResearchPaperTransport> authorPapers) {
        this.authorPapers = authorPapers;
    }

    public Integer getTotalUpvotes() {
        return TotalUpvotes;
    }

    public void setTotalUpvotes(Integer totalUpvotes) {
        TotalUpvotes = totalUpvotes;
    }

    public Integer getTotalDownvotes() {
        return TotalDownvotes;
    }

    public void setTotalDownvotes(Integer totalDownvotes) {
        TotalDownvotes = totalDownvotes;
    }

    public double getAverageUpvotes() {
        return AverageUpvotes;
    }

    public void setAverageUpvotes(double averageUpvotes) {
        AverageUpvotes = averageUpvotes;
    }




}
