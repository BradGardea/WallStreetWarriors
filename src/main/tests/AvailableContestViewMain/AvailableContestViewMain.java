package main.tests.AvailableContestViewMain;

import app.ContestUseCaseFactory;
import app.Main;
import interface_adapters.AvailableContests.AvailableContestsViewModel;
import interface_adapters.ViewModelManager;
import main.tests.Common.CreateContest;
import view.AvailableContests.AvailableContestDetailView;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AvailableContestViewMain {
    public static void main(String[] args) {
        try{
            Main.FirebaseInit();
            // catching exceptions for GoogleCredentials.fromStream and FileInputStream
            String contestId = "1";
            CreateContest.createContest();
//            var contest = FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", contestId);
            ViewModelManager viewModelManager = new ViewModelManager();
            AvailableContestsViewModel availableContestsViewModel = new AvailableContestsViewModel();
            AvailableContestDetailView availableContestDetailView = ContestUseCaseFactory.createAvailableContestDetailView(availableContestsViewModel, viewModelManager, contestId, "brad");
//            views.add(availableContestDetailView, availableContestDetailView.viewName);
            AvailableContestDetailView.launch(availableContestDetailView);
            viewModelManager.setActiveView(availableContestDetailView.viewName);
            viewModelManager.firePropertyChanged();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } {
            System.out.println("Unable to load firebase data, app will have limited functionality");
        }

    }
}
