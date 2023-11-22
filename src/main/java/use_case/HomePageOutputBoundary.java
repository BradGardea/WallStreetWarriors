package use_case;

public interface HomePageOutputBoundary {
    void prepareSuccessView(HomePageOutputData user);

    void prepareFailView(String error);
}