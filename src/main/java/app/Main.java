package app;

import interface_adapters.CompletedContests.CompletedContestController;
import interface_adapters.CompletedContests.CompletedContestViewModel;
import interface_adapters.Contests.ContestViewModel;
import interface_adapters.ViewModelManager;
import view.CompletedContests.CompletedContestView;
import view.ContestView;

import javax.swing.*;
import java.awt.*;

class Main{
    public static void main(String[] args){
        JFrame app = new JFrame("Wall Street Warriors");
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        app.setSize(800, 600);

//        CardLayout cardLayout = new CardLayout();

//        JPanel views = new JPanel(cardLayout);
//        app.add(views);

        ViewModelManager viewModelManager = new ViewModelManager();

        ContestViewModel contestViewModel = new ContestViewModel();

        CompletedContestViewModel completedContestViewModel = new CompletedContestViewModel();

        CompletedContestController completedContestController = new CompletedContestController();
        ContestView contestView = ContestUseCaseFactory.create(contestViewModel);
        CompletedContestView completedContestView = ContestUseCaseFactory.createCompletedContestView(completedContestViewModel, completedContestController);

        //views.add(contestView, contestView.viewName);
//        views.add(completedContestView, completedContestView.viewName);
//
//        viewModelManager.setActiveView(completedContestView.viewName);
//        viewModelManager.firePropertyChanged();

        app.add(completedContestView);
//        app.pack();
        app.setVisible(true);


    }

}