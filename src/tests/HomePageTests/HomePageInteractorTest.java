package HomePageTests;

import FirebaseDataAccess.FirebaseDataAccess;
import InterfaceAdapters.AvailableContests.AvailableContestPresenter;
import InterfaceAdapters.AvailableContests.AvailableContestsViewModel;
import InterfaceAdapters.HomePage.HomePagePresenter;
import InterfaceAdapters.HomePage.HomePageViewModel;
import InterfaceAdapters.SignUpLogIn.LoginViewModel;
import InterfaceAdapters.ViewModelManager;
import UseCase.AvailableContest.AvailableContestInteractor;
import UseCase.HomePage.HomePageInteractor;
import app.Main;
import view.HomePage.HomePageView;
import view.LoggedInView;

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
        assert(this.loginViewModel.getState() == null);
    }
}
