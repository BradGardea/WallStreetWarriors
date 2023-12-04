package app;

import firebaseDataAccess.FirebaseDataAccess;
import useCase.HomePage.HomePageInputBoundary;
import useCase.HomePage.HomePageInputData;
import useCase.HomePage.HomePageInteractor;
import useCase.HomePage.HomePageOutputBoundary;
import useCase.Login.LoginInputBoundary;
import useCase.Login.LoginInteractor;
import useCase.Login.LoginOutputBoundary;
import useCase.SignUp.SignupInputBoundary;
import useCase.SignUp.SignupInteractor;
import useCase.SignUp.SignupOutputBoundary;
import entity.UserFactory;
import interfaceAdapters.HomePage.HomePageController;
import interfaceAdapters.HomePage.HomePagePresenter;
import interfaceAdapters.HomePage.HomePageViewModel;
import interfaceAdapters.SignUpLogIn.*;
import interfaceAdapters.ViewModelManager;
import view.HomePage.HomePageView;
import view.LogInSignUp.LoginView;
import view.LogInSignUp.SignupView;

import javax.swing.*;
import java.io.IOException;

public class MainNavigationFactory {

    // prevent init
    private MainNavigationFactory() {};

    /**
     * Parameterized factory that requires a view name to create a specified view with CA components embedded.
     * @param viewName name of view for class to be constructed
     * @return returns a MainNavigationView, empty interface used to generalize main components.
     */
    public static MainNavigationView createMainView(String viewName, ViewModelManager viewManagerModel, HomePageViewModel homepageViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, FirebaseDataAccess userDataAccessObject, String username){
        if (viewName == "sign up"){
            try {
                SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
                return new SignupView(signupController, signupViewModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }
        else if (viewName == "log in"){
            try {
                LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, homepageViewModel, userDataAccessObject, signupViewModel);
                return new LoginView(loginViewModel, loginController, viewManagerModel, homepageViewModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }
        else if (viewName == "homepage view"){
            try {
                HomePageController homepageController = createHomePageUseCase(viewManagerModel, homepageViewModel, userDataAccessObject, username);
                return new HomePageView(homepageViewModel, homepageController, viewManagerModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }
        return null;
    }

    private static SignupController createUserSignupUseCase(ViewModelManager viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, FirebaseDataAccess userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new UserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject,
                signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static LoginController createLoginUseCase(
            ViewModelManager viewManagerModel,
            LoginViewModel loginViewModel,
            HomePageViewModel homepageViewModel,
            FirebaseDataAccess userDataAccessObject,
            SignupViewModel signupViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homepageViewModel, loginViewModel, signupViewModel);

        UserFactory userFactory = new UserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(loginOutputBoundary);

        return new LoginController(loginInteractor);
    }

    private static HomePageController createHomePageUseCase(ViewModelManager viewManagerModel, HomePageViewModel homepageViewModel, FirebaseDataAccess userDataAccessObject, String username) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        HomePageOutputBoundary homePageOutputBoundary = new HomePagePresenter(homepageViewModel, viewManagerModel, new LoginViewModel());

        HomePageInputBoundary userHomePageInteractor = new HomePageInteractor(userDataAccessObject, homePageOutputBoundary,  new HomePageInputData(username));

        return new HomePageController(userHomePageInteractor);
    }
}
