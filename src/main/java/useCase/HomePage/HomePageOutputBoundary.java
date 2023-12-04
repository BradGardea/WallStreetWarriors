package useCase.HomePage;

public interface HomePageOutputBoundary {
    void prepareSuccessView(HomePageOutputData data);

    // TODO: Add Functionality to Show a message or something if there is an error.
    void prepareFailView();

    void prepareSuccessViewSignOut();
}