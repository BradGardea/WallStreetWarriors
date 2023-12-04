package homePageTests;

import firebaseDataAccess.FirebaseDataAccess;
import interfaceAdapters.HomePage.HomePagePresenter;
import interfaceAdapters.HomePage.HomePageViewModel;
import interfaceAdapters.SignUpLogIn.LoginViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.HomePage.HomePageInteractor;
import app.Main;

import java.io.IOException;

public class HomePageInteractorTest {
    public FirebaseDataAccess firebaseDataAccess;
    public HomePagePresenter homepagePresenter;
    public HomePageViewModel homepageViewModel;

    public LoginViewModel loginViewModel;

    public ViewModelManager viewModelManager;
    public HomePageInteractor homePageInteractor;

    public HomePageInteractorTest() throws IOException {
        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.homepageViewModel = new HomePageViewModel();
        this.viewModelManager = new ViewModelManager();
        this.loginViewModel = new LoginViewModel();
        this.homepagePresenter = new HomePagePresenter(this.homepageViewModel, this.viewModelManager, this.loginViewModel);
        // hardcoded values for test purposes
        this.homePageInteractor = new HomePageInteractor(this.firebaseDataAccess, this.homepagePresenter, "a");
    }
    @org.junit.Test
    public void executeTest(){
        this.homePageInteractor.execute();
        assert(this.homepageViewModel.getState() != null);

    }

    @org.junit.Test
    public void executeSignOutTest(){
        this.homePageInteractor.executeSignOut();
        assert(this.viewModelManager.getActiveView() == "log in");
    }
}
