package entities;

public class Category {
    private final String id;
    private final String description;

    public Category(String id, String description){
        this.id = id;
        this.description = description;
    }
    public String getId(){
        return this.id;
    }
    public String getDescription(){
        return this.description;
    }
}
