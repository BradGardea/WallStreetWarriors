//package HomePageTests;
//
//import FirebaseDataAccess.FirebaseDataAccess;
//import app.HomePageUseCaseFactory;
//import app.Main;
//import interface_adapters.HomePage.*;
//import interface_adapters.ViewModelManager;
//import view.HomePage.HomePageView;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class HomePageViewTest {
//
//    @org.junit.Test
//    public void testExecute() {
//        // initialization for firebase.
//
//        try {
//
//            Main.FirebaseInit();
//            var firebaseDataAccess = FirebaseDataAccess.getInstance();
//            //  catching exceptions for GoogleCredentials.fromStream and FileInputStream
//            // firebaseDataAccess.getEntity(Contest.class, "Contests", contestId);
//
//            // creating JFrame for app
//            JFrame app = new JFrame("Wall Street Warriors");
//            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            app.setSize(new Dimension(900, 600));
//
//
//            ViewModelManager viewModelManager = new ViewModelManager();
//
//            JPanel views = new JPanel();
//
//            // creating CompletedContestViewModel and CompletedContestController
//            HomePageViewModel homePageViewModel = new HomePageViewModel();
//            HomePageView homePageView = HomePageUseCaseFactory.create(viewModelManager, homePageViewModel, firebaseDataAccess, "dhruv1");
//            views.add(homePageView, homePageView.viewName);
////            viewModelManager.setActiveView(homePageView.viewName);
////            viewModelManager.firePropertyChanged();
//            app.add(homePageView);
//            app.setVisible(true);
//
//
//        } catch (Exception ex) {
//            System.out.println("Unable to load firebase data, app will have limited functionality");
//        }
//
//    }
//
//}