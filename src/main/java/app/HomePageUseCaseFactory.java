package app;

import FirebaseDataAccess.FirebaseDataAccess;
import interface_adapters.HomePage.HomePageController;
import interface_adapters.HomePage.HomePagePresenter;
import interface_adapters.HomePage.HomePageViewModel;
import interface_adapters.SignUpLogIn.LoginViewModel;
import interface_adapters.ViewModelManager;
import UseCase.HomePage.HomePageInputBoundary;
import UseCase.HomePage.HomePageInteractor;
import UseCase.HomePage.HomePageOutputBoundary;
import view.HomePage.HomePageView;

import javax.swing.*;
import java.io.IOException;

public class HomePageUseCaseFactory {

    // Prevents Instantiation
    private HomePageUseCaseFactory(){};

    public static HomePageView create(ViewModelManager viewManagerModel, HomePageViewModel homepageViewModel, FirebaseDataAccess userDataAccessObject, String username){
        try {
            HomePageController homepageController = createHomePageUseCase(viewManagerModel, homepageViewModel, userDataAccessObject, username);
            return new HomePageView(homepageViewModel, homepageController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static HomePageController createHomePageUseCase(ViewModelManager viewManagerModel, HomePageViewModel homepageViewModel, FirebaseDataAccess userDataAccessObject, String username) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        HomePageOutputBoundary homePageOutputBoundary = new HomePagePresenter(homepageViewModel, viewManagerModel, new LoginViewModel());

        HomePageInputBoundary userHomePageInteractor = new HomePageInteractor(userDataAccessObject, homePageOutputBoundary, username);

        return new HomePageController(userHomePageInteractor);
    }

}
