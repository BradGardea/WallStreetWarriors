package interfaceAdapters.SignUpLogIn;

import useCase.Login.LoginInputBoundary;
import useCase.Login.LoginInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public boolean execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);
        if (loginUseCaseInteractor.execute(loginInputData)){
            return true;
        }
        else{
            return false;
        }
    }

    public void executeSwitchScreen() {loginUseCaseInteractor.executeSwitchScreen();
    }
}
