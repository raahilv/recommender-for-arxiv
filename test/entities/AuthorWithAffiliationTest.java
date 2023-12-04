package entities;

public class AuthorWithAffiliationTest {
    @org.junit.Test
    public void testGetAffiliation(){
        Author a1 = new Author("Zhipp Canuff", "Kingdom of Zhipp Canuff");
        assert (a1.getAffiliation().equals("Kingdom of Zhipp Canuff"));
    }
    @org.junit.Test
    public void testSetAffiliation(){
        Author a1 = new Author("Zhipp Canuff", "Kingdom of Zhipp Canuff");
        a1.setAffiliation("United States");
        assert (a1.getAffiliation().equals("United States"));

    }


}
