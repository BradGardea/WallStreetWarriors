package main.tests.CompletedContestTests;

import FirebaseDataAccess.FirebaseDataAccess;
import app.ContestUseCaseFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.Contest;
import interface_adapters.CompletedContests.CompletedContestController;
import interface_adapters.CompletedContests.CompletedContestViewModel;
import interface_adapters.Contests.ContestViewModel;
import interface_adapters.ViewModelManager;
import view.CompletedContests.CompletedContestView;
import view.ContestView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class CompletedContestViewMain {


    public static void main(String[] args) {
        // initialization for firebase.
        Firestore db;

        try{

            URL url =  CompletedContestViewMain.class.getResource("/wallstreetwarriors-firebase-adminsdk-8g503-a1c2e7ac43.json");
            File file = new File(url.getPath());

            FileInputStream serviceAccount =
                    new FileInputStream(file);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();

            //"Initialize" singleton entity level data access factory
            var firebaseDataAccess = FirebaseDataAccess.getInstance();
            firebaseDataAccess.setFirestore(db);
        // catching exceptions for GoogleCredentials.fromStream and FileInputStream
            String contestId = "1";
            firebaseDataAccess.getEntity(Contest.class, "Contests", contestId);

            // creating JFrame for app
            JFrame app = new JFrame("Wall Street Warriors");
            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            app.setSize(new Dimension(800, 600));

            CardLayout cardLayout = new CardLayout();
            JPanel views = new JPanel(cardLayout);
            app.add(views);
            ViewModelManager viewModelManager = new ViewModelManager();

        // creating CompletedContestViewModel and CompletedContestController
            CompletedContestViewModel completedContestViewModel = new CompletedContestViewModel();
            CompletedContestView completedContestView = ContestUseCaseFactory.createCompletedContestView(completedContestViewModel, firebaseDataAccess, viewModelManager, contestId, "dhruvpatt");
            views.add(completedContestView, completedContestView.viewName);
            viewModelManager.setActiveView(completedContestView.viewName);
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
