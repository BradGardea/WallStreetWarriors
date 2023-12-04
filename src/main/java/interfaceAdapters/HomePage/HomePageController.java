package interfaceAdapters.HomePage;

import useCase.HomePage.HomePageInputBoundary;

public class HomePageController {
    public final HomePageInputBoundary homepageUseCaseInteractor;

    public HomePageController(HomePageInputBoundary homepageUseCaseInteractor){
        this.homepageUseCaseInteractor = homepageUseCaseInteractor;
    }

    public void execute(){
        homepageUseCaseInteractor.execute();
    }
    public void executeSignOut(){
        homepageUseCaseInteractor.executeSignOut();
    }
}