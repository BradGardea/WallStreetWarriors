package app;

import interface_adapters.Contests.ContestViewModel;
import view.ContestView;

public class ContestUseCaseFactory {

    // Prevents Instantiation
    private ContestUseCaseFactory(){};

    public static ContestView create(ContestViewModel contestViewModel){
        ContestView contestView = new ContestView(contestViewModel);
        return contestView;
    }

}
