package entities;

public abstract class Category {

    private final String parentCategory;

    public Category(String parentCategory){
        this.parentCategory = parentCategory;
    }

    public String getParentCategory(){
        return this.parentCategory;
    }

    public abstract boolean hasChildCategory(String target);

}
