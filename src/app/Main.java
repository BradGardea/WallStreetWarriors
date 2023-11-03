package app;

import interface_adapters.Contests.ContestViewModel;
import interface_adapters.ViewModelManager;
import view.ContestView;

import javax.swing.*;
import java.awt.*;

class Main{
    public static void main(String[] args){
        JFrame app = new JFrame("Wall Street Warriors");
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        app.add(views);

        ViewModelManager viewModelManager = new ViewModelManager();

        ContestViewModel contestViewModel = new ContestViewModel();
        ContestView contestView = ContestUseCaseFactory.create(contestViewModel);

        views.add(contestView, contestView.viewName);

        viewModelManager.setActiveView(contestView.viewName);
        viewModelManager.firePropertyChanged();

        app.pack();
        app.setVisible(true);

    }
}