package main.tests.CompletedContestTests;

import FirebaseDataAccess.FirebaseDataAccess;
import app.ContestUseCaseFactory;
import app.Main;
import com.google.cloud.firestore.Firestore;
import interface_adapters.CompletedContests.CompletedContestViewModel;
import interface_adapters.ViewModelManager;
import view.CompletedContests.CompletedContestView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompletedContestViewMain {

    public static boolean popUpDiscovered = false;

//    @org.junit.Test
    public static void testCompletedContestExecute() {
        // initialization for firebase.
        Firestore db;

        try{
            popUpDiscovered = false;
            Main.FirebaseInit();
            var firebaseDataAccess = FirebaseDataAccess.getInstance();
            //  catching exceptions for GoogleCredentials.fromStream and FileInputStream
            String contestId = "CompletedTest";
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

//            createCloseTimer().start();
//            assert (popUpDiscovered);

        } catch (Exception ex){
            System.out.println("Unable to load firebase data, app will have limited functionality");
        }

    }

    private Timer createCloseTimer(){
        ActionListener close = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
//                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
//                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
//                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            CompletedContestViewMain.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }
        };
        Timer t = new Timer(100000, close);
        t.setRepeats(false);
        return t;
    }

    public static void main(String[] args) {
        testCompletedContestExecute();
    }


}
