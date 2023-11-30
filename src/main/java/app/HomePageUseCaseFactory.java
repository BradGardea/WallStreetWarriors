package app;

import interface_adapters.HomePage.HomePageViewModel;
import view.HomePage.HomePageView;

public class HomePageUseCaseFactory {

    // Prevents Instantiation
    private HomePageUseCaseFactory(){};

    public static HomePageView create(HomePageViewModel homePageViewModel){
        HomePageView homePageView = new HomePageView(homePageViewModel);
        return homePageView;
    }

}
