package interface_adapters.library;

import java.util.List;

public class LibraryState {
    private List<String> ids;
    private List<String> titles;
    private List<String> urls;
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getIds() {
        return ids;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getUrls() {
        return urls;
    }
}
