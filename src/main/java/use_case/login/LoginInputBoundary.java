package use_case.login;

import UseCase.login.LoginInputData;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);

    void executeSwitchScreen();
}
