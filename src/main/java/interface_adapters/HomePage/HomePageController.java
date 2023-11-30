package interface_adapters.HomePage;

import use_case.HomePageInputBoundary;

public class HomePageController {
    public final HomePageInputBoundary homepageUseCaseInteractor;

    public HomePageController(HomePageInputBoundary homepageUseCaseInteractor){
        this.homepageUseCaseInteractor = homepageUseCaseInteractor;
    }


    public void execute(){
        homepageUseCaseInteractor.execute();
    }

}
