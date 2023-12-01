package main.tests.EnrolledContestTests;

import FirebaseDataAccess.FirebaseDataAccess;
import app.ContestUseCaseFactory;
import app.Main;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.Contest;
import interface_adapters.Enrolled.EnrolledViewModel;
import interface_adapters.ViewModelManager;
import main.tests.Common.CreateContest;
import view.EnrolledView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class EnrolledContestViewMain {


    public static void main(String[] args) {
        // initialization for firebase.
        Firestore db;

        try{
            Main.FirebaseInit();
            var firebaseDataAccess = FirebaseDataAccess.getInstance();
            // catching exceptions for GoogleCredentials.fromStream and FileInputStream
            String contestId = "1000";
            firebaseDataAccess.getEntity(Contest.class, "Contests", contestId);

            // creating JFrame for app
            JFrame app = new JFrame("Wall Street Warriors");
            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            app.setSize(new Dimension(800, 600));

            CardLayout cardLayout = new CardLayout();
            JPanel views = new JPanel(cardLayout);
            app.add(views);
            ViewModelManager viewModelManager = new ViewModelManager();

            EnrolledViewModel enrolledViewModel = new EnrolledViewModel();
            EnrolledView enrolledView = ContestUseCaseFactory.createEnrolledView(enrolledViewModel, firebaseDataAccess, viewModelManager, contestId, "dhruvpatt");
            views.add(enrolledView, enrolledView.viewName);
            viewModelManager.setActiveView(enrolledView.viewName);
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
