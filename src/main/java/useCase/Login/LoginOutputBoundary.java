package useCase.Login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);

    void prepareSuccessViewButton();
}