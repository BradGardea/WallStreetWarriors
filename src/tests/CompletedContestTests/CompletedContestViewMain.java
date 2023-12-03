package CompletedContestTests;

import FirebaseDataAccess.FirebaseDataAccess;
import app.ContestUseCaseFactory;
import app.Main;
import com.google.cloud.firestore.Firestore;
import InterfaceAdapters.CompletedContests.CompletedContestViewModel;
import InterfaceAdapters.ViewModelManager;
import view.CompletedContests.CompletedContestView;

import javax.swing.*;
import java.awt.*;

public class CompletedContestViewMain {


    public static void main(String[] args) {
        // initialization for firebase.
        Firestore db;

        try{

            Main.FirebaseInit();
            var firebaseDataAccess = FirebaseDataAccess.getInstance();
            //  catching exceptions for GoogleCredentials.fromStream and FileInputStream
            String contestId = "1";
            // firebaseDataAccess.getEntity(Contest.class, "Contests", contestId);

            // creating JFrame for app
            JFrame app = new JFrame("Wall Street Warriors");
            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            app.setSize(new Dimension(900, 600));


            ViewModelManager viewModelManager = new ViewModelManager();

        // creating CompletedContestViewModel and CompletedContestController
            CompletedContestViewModel completedContestViewModel = new CompletedContestViewModel();
            CompletedContestView completedContestView = ContestUseCaseFactory.createCompletedContestView(completedContestViewModel, firebaseDataAccess, viewModelManager, contestId, "a");
            CompletedContestView.launch(completedContestView);
//            app.add(completedContestView);
//            app.setVisible(true);

        } catch (Exception ex){
            System.out.println("Unable to load firebase data, app will have limited functionality");
        }

    }
}
