package LoginTests;

import firebaseDataAccess.FirebaseDataAccess;
import interfaceAdapters.HomePage.HomePageViewModel;
import interfaceAdapters.SignUpLogIn.LoginPresenter;
import interfaceAdapters.SignUpLogIn.LoginViewModel;
import interfaceAdapters.SignUpLogIn.SignupViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.Login.LoginInputData;
import useCase.Login.LoginInteractor;
import app.Main;

import java.io.IOException;

public class LoginInteractorTests {
    public FirebaseDataAccess firebaseDataAccess;
    public LoginPresenter loginPresenter;
    public LoginViewModel loginViewModel;

    public HomePageViewModel homePageViewModel;

    public SignupViewModel signupViewModel;

    public ViewModelManager viewModelManager;
    public LoginInteractor loginInteractor;

    public LoginInteractorTests() throws IOException {
        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.loginViewModel = new LoginViewModel();
        this.viewModelManager = new ViewModelManager();
        this.homePageViewModel = new HomePageViewModel();
        this.signupViewModel = new SignupViewModel();
        this.loginPresenter = new LoginPresenter(this.viewModelManager, this.homePageViewModel, this.loginViewModel, this.signupViewModel);
        // hardcoded values for test purposes
        this.loginInteractor = new LoginInteractor(this.loginPresenter);
    }

    @org.junit.Test
    public void executeTest(){
        this.loginInteractor.execute(new LoginInputData("a", "1"));
        assert(this.viewModelManager.getActiveView() == "home page view");
    }

    @org.junit.Test
    public void nonexistentTest(){
        Boolean executeFailed = this.loginInteractor.execute(new LoginInputData("zzzzzz", "zzzzzz"));
        assert(executeFailed.equals(false));
    }

    @org.junit.Test
    public void incorrectTest(){
        Boolean executeFailed = this.loginInteractor.execute(new LoginInputData("a", "zzzzzz"));
        assert(executeFailed.equals(false));
    }
    @org.junit.Test
    public void executeSwitchScreenTest(){
        this.loginInteractor.executeSwitchScreen();
        assert(this.viewModelManager.getActiveView() == "sign up");
    }
}

