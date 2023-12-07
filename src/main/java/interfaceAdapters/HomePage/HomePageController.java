package interfaceAdapters.HomePage;

import useCase.HomePage.HomePageInputBoundary;

public class HomePageController {
    public final HomePageInputBoundary homepageUseCaseInteractor;

    public HomePageController(HomePageInputBoundary homepageUseCaseInteractor){
        this.homepageUseCaseInteractor = homepageUseCaseInteractor;
    }

    /**
     * Executes the homepage use case.
     */
    public void execute(){
        homepageUseCaseInteractor.execute();
    }

    /**
     * Executes the sign out process.
     */
    public void executeSignOut(){
        homepageUseCaseInteractor.executeSignOut();
    }
}
