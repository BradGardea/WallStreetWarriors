package app;

import FirebaseDataAccess.FirebaseDataAccess;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import interface_adapters.Contests.ContestViewModel;
import interface_adapters.ViewModelManager;
import view.ContestView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

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
            Message m = new Message("too", "loo");

            firebaseDataAccess.setOrUpdateEntity(m, "Messages", "1234");


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
        catch (Exception ex){
            System.out.println("Unable to load Firebase data, app will have limited functionality.");
        }
    }

}