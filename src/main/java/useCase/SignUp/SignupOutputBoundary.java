package useCase.SignUp;

public interface SignupOutputBoundary {
    void prepareSuccessViewLogin(SignupOutputData user);

    void prepareFailView(String error);

    void prepareSuccessViewButton();
}