import interface_adapters.RecommendHome.RecommendHomeController;
import interface_adapters.RecommendHome.RecommendHomePresenter;
import interface_adapters.RecommendHome.RecommendHomeViewModel;
import interface_adapters.ViewManagerModel;
import interface_adapters.library.LibraryPresenter;
import interface_adapters.login.LoginViewModel;
import use_cases.library.LibraryInputBoundary;
import use_cases.recommend.RecommendInputBoundary;
import view.RecommendHomeView;

import javax.swing.*;

public class RecommendHomeViewTest {
    @org.junit.Test
    public void TestRecommendHomeViewTest(){
        // create the UI; note, we don't make a real InputBoundary,
        // since we don't need it for this test.
        RecommendInputBoundary rib = null;
        LibraryInputBoundary lib = null;
        RecommendHomeController controller = new RecommendHomeController(rib,lib);
        RecommendHomeViewModel viewModel = new RecommendHomeViewModel();
        RecommendHomePresenter presenter = new RecommendHomePresenter(new ViewManagerModel(),viewModel, new LoginViewModel());
        JPanel recommendHomeView = new RecommendHomeView(viewModel,controller);
        JFrame jframe = new JFrame();

        jframe.setContentPane(recommendHomeView);
        jframe.pack();
        jframe.setVisible(true);

    }

}
