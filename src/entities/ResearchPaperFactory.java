package entities;

import java.time.LocalDate;
import java.util.List;

public class ResearchPaperFactory {

    public ResearchPaper create(String id, String title, List<Category> categories, List<Author> authors,
                         LocalDate publishDate, String paperAbstract, String url, long upvoteCount, long downvoteCount) {
        return new ResearchPaper(
                id, title, categories, authors, publishDate, paperAbstract, url, upvoteCount, downvoteCount
        );
    }

}
