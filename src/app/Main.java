package app;

import data_access.*;
import entities.Category;
import entities.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupViewModel;
import org.w3c.dom.css.CSSValue;
import view.HomePageView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
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
    }
}