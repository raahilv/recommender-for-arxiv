package data_access;

import entities.Author;
import entities.Category;
import entities.ResearchPaper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArxivDataAccessObject {
    static String apiBegin = "http://export.arxiv.org/api/";
    List<Category> catList;
    List<Author> authorList;


//    private ResearchPaper PaperBuilder(String id, String title, List<String> categories, List<String> authors, String publishDate, String paperAbstract, String reference, String url) {
//    }
    private ResearchPaper PaperBuilder(HashMap<String, List<String>> map) {
        List<Category> categories = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        LocalDate date;

        String rawDate = map.get("publishDate").get(0);
        date = LocalDate.parse(rawDate);

        for (String cat: map.get("categories")) {
            for (Category CAT: catList) {
                if ((CAT.getRootCategory().equals(cat.split("\\.")[0])) && (CAT.getSubcategory().equals(cat.split("\\.")[1]))) {
                    categories.add(CAT);
                }
            }
        }

        for (String author: map.get("authors")) {
            for(Author AUTHOR: authorList) {
                if (AUTHOR.getName().equals(author)) {
                    authors.add(AUTHOR);
                }
            }
        }
        if (!map.get("reference").get(0).isEmpty()) { // journal_ref exists for this paper
            return new ResearchPaper(map.get("id").get(0), map.get("title").get(0), categories, authors, date, map.get("paperAbstract").get(0), map.get("reference").get(0), map.get("url").get(0), 0, 0);
        }
        return new ResearchPaper(map.get("id").get(0), map.get("title").get(0), categories, authors, date, map.get("paperAbstract").get(0), map.get("url").get(0), 0, 0);
    }
    private HashMap<String, List<String>> parse(String input) {
        HashMap<String, List<String>> map = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc;
        try {
            doc = builder.parse(new InputSource(new StringReader(input)));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        Element root = doc.getDocumentElement();
        map.put("id", List.of(getID(input)));
        map.put("title", List.of(getTitle(root)));
        map.put("categories", getCategories(root));
        map.put("authors", getAuthors(root));
        map.put("publishDate", List.of(getPublishDate(input)));
        map.put("paperAbstract", List.of(getPaperAbstract(root)));
        map.put("reference", List.of(getReference(root)));
        map.put("url", List.of(getURL(input)));

        return map;
    }
    private String getURL(String input){
        return "http://arxiv.org/abs/" + getID(input);}
    private String getReference(Element root){
        NodeList nodeList = root.getElementsByTagName("arxiv:journal_ref");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            return node.getTextContent();
            }
        return "";
        }
    private String getPaperAbstract(Element root) {

        NodeList nodeList = root.getElementsByTagName("summary");
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            return node.getTextContent();
        }
        return "";
    }
    private String getPublishDate(String input) {
        return input.split("published>")[1].split("</")[0];
    }
    private List<String> getAuthors(Element root) {
        NodeList nodeList = root.getElementsByTagName("name");
        List<String> authors = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getParentNode().getNodeName().equals("author")) {
                authors.add(node.getTextContent());
            }

        }
        return authors;

    }
    private List<String> getCategories(Element root) {
        NodeList nodeList = root.getElementsByTagName("category");
        List<String> cats = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            for (int j = 0; j < node.getAttributes().getLength(); j ++) {
                if (node.getAttributes().item(j).getNodeName().equals("term")){
                    cats.add(node.getAttributes().item(j).getNodeValue());
                }

            }
        }
        return cats;
    }
    private String getTitle(Element root) {
        NodeList nodeList = root.getElementsByTagName("title");
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if (node.getParentNode().getNodeName().equals("entry")) {
                Element element = (Element) node;
                return element.getTextContent();
            }
        }
        return "";
    }
    private String getID(String input) {
        String[] str = input.split("id>");
        for (String string : str) {
            if (string.startsWith("http://arxiv.org/abs")) {
                return string.split("http://arxiv.org/abs/")[1].split("</")[0];
            }
        }
        return "";
    }
    //next method might be duplicate that would be covered by facade
//    public ResearchPaper getPaper(String paperName) {
//        HashMap<String, List<String>> map = parse(paperFromApi("ti", paperName));
//        return PaperBuilder(map);
//    }

    public ResearchPaper getPaperByID(String id) {
        HashMap<String, List<String>> map = parse(paperFromAPI("id", id));
        return PaperBuilder(map);
    }

    public ResearchPaper getPaperByTitle(String title) {
        HashMap<String, List<String>> map = parse(paperFromAPI("ti", title));
        return PaperBuilder(map);
    }

    public ResearchPaper getPaperByJournalReference(String journalReference) {
        HashMap<String, List<String>> map = parse(paperFromAPI("jr", journalReference));
        return PaperBuilder(map);
    }

    public List<String> filterPapersByRootCategory(String parentCategory) {
        //assumes 50 papers for now
        List<String> ids = new ArrayList<>();
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder().uri(new URI(apiBegin + "query?search_query=" + "cat" +":" + parentCategory.replaceAll(" ", "%20") + "&start=0&max_results=50")).GET().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpResponse<String> resp;
        try {
            resp = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc;
        try {
            doc = builder.parse(new InputSource(new StringReader(resp.body())));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("id");
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            ids.add(element.getTextContent());
        }
        return ids;


    }

    public List<String> getIDsByAuthor(Author author) {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder().uri(new URI(apiBegin + "query?search_query=" + "au" +":" + author.getName().replaceAll(" ", "%20"))).GET().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpResponse<String> resp;
        try {
            resp = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc;
        try {
            doc = builder.parse(new InputSource(new StringReader(resp.body())));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element root = doc.getDocumentElement();

        List<String> ids = new ArrayList<>();
        NodeList nodeList = root.getElementsByTagName("id");
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            ids.add(element.getTextContent());
        }
        return ids;


    }
    public List<ResearchPaper> getPapersByAuthor(Author author) {
        List<String>  ids = getIDsByAuthor(author);
        List<ResearchPaper> papers = new ArrayList<>();
        for (String id: ids) {
            papers.add(getPaperByID(id));
        }
        return papers;

    }

    private String paperFromAPI(String searchType, String query) {
        query = query.replaceAll(" ", "%20");
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder().uri(new URI(apiBegin + "query?search_query=" + searchType +":" + query + "&start=0&max_results=1")).GET().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpResponse<String> resp;
        try {
            resp = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return resp.body().replace("\n", ""); //not sure, but this might be needed
    }
}
