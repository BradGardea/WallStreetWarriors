package app;

import common.CreateContest;
import firebaseDataAccess.FirebaseDataAccess;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import interfaceAdapters.HomePage.HomePageViewModel;
import interfaceAdapters.SignUpLogIn.LoginViewModel;
import interfaceAdapters.SignUpLogIn.SignupViewModel;
import interfaceAdapters.ViewModelManager;
import view.LogInSignUp.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public class Main{
    public static void main(String[] args){
        //Initialization for firebase.
        try{
            CreateContest.initDefaultContests(5);
            JFrame app = new JFrame("Wall Street Warriors");
            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            CardLayout cardLayout = new CardLayout();

            JPanel views = new JPanel(cardLayout);
            app.add(views);

            ViewModelManager viewModelManager = new ViewModelManager();
            new ViewManager(views, cardLayout, viewModelManager);


            LoginViewModel loginViewModel = new LoginViewModel();
            HomePageViewModel homepageViewModel = new HomePageViewModel();
            SignupViewModel signupViewModel = new SignupViewModel();

            FirebaseDataAccess userDataAccessObject = FirebaseDataAccess.getInstance();

            LoginView loginView = (LoginView)MainNavigationFactory.createMainView("log in", viewModelManager, homepageViewModel, signupViewModel, loginViewModel, userDataAccessObject, null);

            loginView.views = views;
            views.add(loginView, loginView.getViewName());

            SignupView signupView = (SignupView) MainNavigationFactory.createMainView("sign up", viewModelManager, homepageViewModel, signupViewModel, loginViewModel, userDataAccessObject, null);
            views.add(signupView, signupView.getViewName());

            //HomePageView homePageView = new HomePageView(homepageViewModel);
            //views.add(homePageView, homePageView.viewName);

            viewModelManager.setActiveView(signupView.getViewName());
            viewModelManager.firePropertyChanged();

            app.pack();
            app.setVisible(true);
        }
        catch (Exception ex){
            System.out.println("Unable to load Firebase data, app will have limited functionality." + ex);
        }
    }
    public static void FirebaseInit() throws IOException {
        var stream = Main.class.getClassLoader().getResourceAsStream("wallstreetwarriors-firebase-adminsdk-8g503-275acc4c97.json");
        String text = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        var textStream = new ByteArrayInputStream(text.getBytes());
        var gc = GoogleCredentials.fromStream(textStream);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(gc)
                .build();

        boolean hasBeenInitialized=false;
        var firebaseApps = FirebaseApp.getApps();
        for(FirebaseApp app : firebaseApps){
            if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)){
                hasBeenInitialized=true;
            }
        }
        if(!hasBeenInitialized) {
            FirebaseApp.initializeApp(options);
        }
        var db = FirestoreClient.getFirestore();
        //"Initialize" singleton entity level data access factory
        var firebaseDataAccess = FirebaseDataAccess.getInstance();
        firebaseDataAccess.setFirestore(db);
        System.out.println("Initialized Firebase");
    }
}