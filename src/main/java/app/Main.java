package app;

import FirebaseDataAccess.FirebaseDataAccess;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import interface_adapters.Contests.ContestViewModel;
import interface_adapters.HomePage.HomePageController;
import interface_adapters.HomePage.HomePageViewModel;
import interface_adapters.SignUpLogIn.LoginViewModel;
import interface_adapters.SignUpLogIn.SignupViewModel;
import interface_adapters.ViewModelManager;
import io.opencensus.stats.ViewManager;
import view.ContestView;
import view.HomePage.HomePageView;
import view.LogInSignUp.LoginView;
import view.LogInSignUp.SignupView;
import view.LoggedInView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

class Main{
    public static void main(String[] args){

        Firestore db;
        //Initialization for firebase.
        try{
            URL url =  Main.class.getResource("/wallstreetwarriors-firebase-adminsdk-8g503-9cad46c515.json");
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

            firebaseDataAccess.getEntity(Message.class, "Messages", "123");
            HashMap hm = new HashMap<String, Object>();
            HashMap nested = new HashMap<String, Object>();
            nested.put("hello", "test");
            hm.put("foo", nested);
            var l = new ArrayList<String>();
            l.add("foo");
            Message m = new Message("test", "now", hm, l);

            firebaseDataAccess.setOrUpdateEntity(m, "Messages", "1234");


            JFrame app = new JFrame("Wall Street Warriors");
            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            CardLayout cardLayout = new CardLayout();

            JPanel views = new JPanel(cardLayout);
            app.add(views);

            ViewModelManager viewModelManager = new ViewModelManager();


            LoginViewModel loginViewModel = new LoginViewModel();
            HomePageViewModel homepageViewModel = new HomePageViewModel();
            SignupViewModel signupViewModel = new SignupViewModel();

            FirebaseDataAccess userDataAccessObject;
            userDataAccessObject = new FirebaseDataAccess();


            SignupView signupView = SignupUseCaseFactory.create(viewModelManager, loginViewModel, signupViewModel, userDataAccessObject);
            views.add(signupView, signupView.viewName);

            LoginView loginView = LoginUseCaseFactory.create(viewModelManager, loginViewModel, homepageViewModel, userDataAccessObject);
            views.add(loginView, loginView.viewName);

            //HomePageView homePageView = new HomePageView(homepageViewModel);
            //views.add(homePageView, homePageView.viewName);



            viewModelManager.setActiveView(signupView.viewName);
            viewModelManager.firePropertyChanged();

            app.pack();
            app.setVisible(true);
        }
        catch (Exception ex){
            System.out.println("Unable to load Firebase data, app will have limited functionality.");
        }
    }

}