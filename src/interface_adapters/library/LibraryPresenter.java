package interface_adapters.library;

import interface_adapters.ViewManagerModel;
import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import use_cases.library.LibraryOutputBoundary;
import use_cases.library.LibraryOutputData;

public class LibraryPresenter implements LibraryOutputBoundary {
    private LibraryViewModel libraryViewModel;
    private ViewManagerModel viewManagerModel;

    @Override
    public void prepareLibrary(LibraryOutputData outputData) {
        LibraryState libraryState = libraryViewModel.getState();
        libraryState.setIds(outputData.getPaperID());
        libraryState.setUrls(outputData.getPaperPDF());
        libraryState.setTitles(outputData.getPaperName());
        libraryViewModel.setState(libraryState);
        libraryViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(libraryViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    public LibraryPresenter(ViewManagerModel viewManagerModel,
                          LibraryViewModel libraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.libraryViewModel = libraryViewModel;

    }
}
