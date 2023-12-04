package use_cases.signupAndLogin;

import data_access.DataAccessFacade;
import entities.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacadeConstructorForTesting {
    public static DataAccessFacade create(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("cs", "cs.AI"));
        categories.add(new Category("cs", "cs.CL"));
        categories.add(new Category("cs", "cs.CC"));
        categories.add(new Category("cs", "cs.CE"));
        categories.add(new Category("cs", "cs.CG"));
        categories.add(new Category("cs", "cs.GT"));
        categories.add(new Category("cs", "cs.CV"));
        categories.add(new Category("cs", "cs.CY"));
        categories.add(new Category("cs", "cs.CR"));
        categories.add(new Category("cs", "cs.DS"));
        categories.add(new Category("cs", "cs.DB"));
        categories.add(new Category("cs", "cs.DL"));
        categories.add(new Category("cs", "cs.DM"));
        categories.add(new Category("cs", "cs.DC"));
        categories.add(new Category("cs", "cs.ET"));
        categories.add(new Category("cs", "cs.FL"));
        categories.add(new Category("cs", "cs.GL"));
        categories.add(new Category("cs", "cs.CR"));
        categories.add(new Category("cs", "cs.AR"));
        categories.add(new Category("cs", "cs.HC"));
        categories.add(new Category("cs", "cs.IR"));
        categories.add(new Category("cs", "cs.IT"));
        categories.add(new Category("cs", "cs.LO"));
        categories.add(new Category("cs", "cs.LG"));
        categories.add(new Category("cs", "cs.MS"));
        categories.add(new Category("cs", "cs.MA"));
        categories.add(new Category("cs", "cs.MM"));
        categories.add(new Category("cs", "cs.NI"));
        categories.add(new Category("cs", "cs.NE"));
        categories.add(new Category("cs", "cs.NA"));
        categories.add(new Category("cs", "cs.OS"));
        categories.add(new Category("cs", "cs.OH"));
        categories.add(new Category("cs", "cs.PF"));
        categories.add(new Category("cs", "cs.PL"));
        categories.add(new Category("cs", "cs.RO"));
        categories.add(new Category("cs", "cs.SI"));
        categories.add(new Category("cs", "cs.SE"));
        categories.add(new Category("cs", "cs.SD"));
        categories.add(new Category("cs", "cs.SC"));
        categories.add(new Category("cs", "cs.SY"));

        DataAccessFacade DAO;
        try {
            DAO = new DataAccessFacade(categories);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DAO;

    }
}
