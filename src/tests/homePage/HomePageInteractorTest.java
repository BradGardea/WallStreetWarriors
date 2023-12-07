package homePage;

import firebaseDataAccess.FirebaseDataAccess;
import interfaceAdapters.HomePage.HomePageController;
import interfaceAdapters.HomePage.HomePagePresenter;
import interfaceAdapters.HomePage.HomePageViewModel;
import interfaceAdapters.SignUpLogIn.LoginViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.HomePage.HomePageInputData;
import useCase.HomePage.HomePageInteractor;
import app.Main;
import view.HomePage.HomePageView;

import java.io.IOException;

public class HomePageInteractorTest {
    public FirebaseDataAccess firebaseDataAccess;
    public HomePagePresenter homepagePresenter;
    public HomePageViewModel homepageViewModel;

    public LoginViewModel loginViewModel;

    public ViewModelManager viewModelManager;
    public HomePageInteractor homePageInteractor;

    public HomePageController homePageController;

    public HomePageInteractorTest() throws IOException {
        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.homepageViewModel = new HomePageViewModel();
        this.viewModelManager = new ViewModelManager();
        this.loginViewModel = new LoginViewModel();
        this.homepagePresenter = new HomePagePresenter(this.homepageViewModel, this.viewModelManager, this.loginViewModel);
        // hardcoded values for test purposes
        this.homePageInteractor = new HomePageInteractor(this.firebaseDataAccess, this.homepagePresenter,  new HomePageInputData("a"));
        this.homePageController = new HomePageController(this.homePageInteractor);
    }
    @org.junit.Test
    public void executeTest(){
        this.homePageController.execute();
        assert(this.homepageViewModel.getState() != null);

    }

    @org.junit.Test
    public void executeSignOutTest(){
        this.homePageController.executeSignOut();
        assert(this.viewModelManager.getActiveView() == "log in");
    }

    @org.junit.Test
    public void construct(){
        var view = new HomePageView(this.homepageViewModel, this.homePageController, this.viewModelManager);
        assert(view != null);
    }



}
