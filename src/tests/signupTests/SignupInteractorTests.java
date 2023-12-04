package signupTests;

import firebaseDataAccess.FirebaseDataAccess;
import interfaceAdapters.SignUpLogIn.LoginViewModel;
import interfaceAdapters.SignUpLogIn.SignupPresenter;
import interfaceAdapters.SignUpLogIn.SignupViewModel;
import interfaceAdapters.HomePage.HomePageViewModel;
import interfaceAdapters.SignUpLogIn.*;
import interfaceAdapters.ViewModelManager;
import useCase.SignUp.SignupInputData;
import useCase.SignUp.SignupInteractor;
import app.Main;
import entity.UserFactory;
import view.LogInSignUp.LoginView;
import view.LogInSignUp.SignupView;

import java.io.IOException;

public class SignupInteractorTests {
    public FirebaseDataAccess firebaseDataAccess;
    public SignupInteractor signupInteractor;
    public LoginViewModel loginViewModel;

    public SignupViewModel signupViewModel;

    public ViewModelManager viewModelManager;
    public SignupPresenter signupPresenter;

    public SignupController signupController;

    public SignupInteractorTests() throws IOException {
        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.loginViewModel = new LoginViewModel();
        this.viewModelManager = new ViewModelManager();
        this.signupViewModel = new SignupViewModel();
        this.signupPresenter = new SignupPresenter(this.viewModelManager, this.signupViewModel, this.loginViewModel);
        // hardcoded values for test purposes
        this.signupInteractor = new SignupInteractor(this.firebaseDataAccess, this.signupPresenter, new UserFactory());
        this.signupController = new SignupController(signupInteractor);
    }

    @org.junit.Test
    public void executeTest(){
        this.signupController.execute("test6374753431000000", "test", "test");
        assert(this.viewModelManager.getActiveView() == "log in");
    }

    @org.junit.Test
    public void existentTest(){
        this.signupController.execute("a", "z", "z");
        assert(this.viewModelManager.getActiveView() == null);
    }

    @org.junit.Test
    public void nonmatchingTest(){
        this.signupController.execute("qwertyu", "a", "b");
        assert(this.viewModelManager.getActiveView() == null);
    }
    @org.junit.Test
    public void executeSwitchScreenTest(){
        this.signupController.executeSwitchScreen();
        assert(this.viewModelManager.getActiveView() == "log in");
    }

    @org.junit.Test
    public void construct(){
        var view = new SignupView(this.signupController, this.signupViewModel);
        assert(view != null);
    }
}
