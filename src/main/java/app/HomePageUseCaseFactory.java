package app;

import FirebaseDataAccess.FirebaseDataAccess;
import entity.UserFactory;
import interface_adapters.HomePage.HomePageController;
import interface_adapters.HomePage.HomePagePresenter;
import interface_adapters.HomePage.HomePageViewModel;
import interface_adapters.SignUpLogIn.LoginViewModel;
import interface_adapters.SignUpLogIn.SignupController;
import interface_adapters.SignUpLogIn.SignupPresenter;
import interface_adapters.SignUpLogIn.SignupViewModel;
import interface_adapters.ViewModelManager;
import use_case.HomePageInputBoundary;
import use_case.HomePageInteractor;
import use_case.HomePageOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.HomePage.HomePageView;
import view.LogInSignUp.SignupView;

import javax.swing.*;
import java.io.IOException;

public class HomePageUseCaseFactory {

    // Prevents Instantiation
    private HomePageUseCaseFactory(){};

    public static HomePageView create(ViewModelManager viewManagerModel, HomePageViewModel homepageViewModel, FirebaseDataAccess userDataAccessObject, String username){
        try {
            HomePageController homepageController = createHomePageUseCase(viewManagerModel, homepageViewModel, userDataAccessObject, username);
            return new HomePageView(homepageViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static HomePageController createHomePageUseCase(ViewModelManager viewManagerModel, HomePageViewModel homepageViewModel, FirebaseDataAccess userDataAccessObject, String username) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        HomePageOutputBoundary homePageOutputBoundary = new HomePagePresenter(homepageViewModel, viewManagerModel);

        HomePageInputBoundary userHomePageInteractor = new HomePageInteractor(userDataAccessObject, homePageOutputBoundary, username);

        return new HomePageController(userHomePageInteractor);
    }

}
