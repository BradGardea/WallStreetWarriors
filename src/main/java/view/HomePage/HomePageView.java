package view.HomePage;

import firebaseDataAccess.FirebaseDataAccess;
import app.ContestUseCaseFactory;
import app.MainNavigationView;
import entity.Contest;
import entity.User;
import interfaceAdapters.AvailableContests.AvailableContestsViewModel;
import interfaceAdapters.CompletedContests.CompletedContestViewModel;
import interfaceAdapters.Enrolled.EnrolledViewModel;
import interfaceAdapters.HomePage.HomePageController;
import interfaceAdapters.HomePage.HomePageState;
import interfaceAdapters.HomePage.HomePageViewModel;
import interfaceAdapters.ViewModelManager;
import view.AvailableContests.AvailableContestDetailView;
import view.CompletedContests.CompletedContestView;
import view.EnrolledContest.EnrolledView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class HomePageView extends JPanel implements ActionListener, PropertyChangeListener, MainNavigationView {
    private final String viewName = "homepage view";
    private HomePageController homepageController;
    private HomePageViewModel homepageViewModel;
    private JScrollPane availableScrollPane;
    private JScrollPane enrolledScrollPane;
    private JScrollPane completedScrollPane;
    private ViewModelManager viewModelManager;
    private JPanel contentPanel;

    public HomePageView(HomePageViewModel viewModel, HomePageController controller, ViewModelManager viewModelManager){
        this.homepageViewModel = viewModel;
        this.homepageController = controller;
        this.viewModelManager = viewModelManager;
        this.homepageViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Header panel with title and sign-out button and welcome label
        JPanel headerPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Wall Street Warriors");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("Welcome " + homepageViewModel.username + "!", SwingConstants.CENTER);

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setHorizontalAlignment(JLabel.RIGHT);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setHorizontalAlignment(JLabel.RIGHT);
        refreshButton.setPreferredSize(new Dimension(50, 30));

        signOutButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signOutButton)) {
                            homepageController.executeSignOut();
                        }
                    }
                }
        );

        refreshButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(refreshButton)) {
                            homepageController.execute();
                        }
                    }
                }
        );



        buttonsPanel.add(signOutButton);
        buttonsPanel.add(refreshButton);

        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);

        headerPanel.add(buttonsPanel, BorderLayout.EAST);

//        headerPanel.add(signOutButton, BorderLayout.EAST);
//        headerPanel.add(refreshButton, BorderLayout.CENTER);


        // Main content panel with scrollable lists
        // Add sub-panels to the main panel
        add(headerPanel, BorderLayout.NORTH); // Only add the headerPanel here
        setLists();
        // welcomeLabel should not be added here again since it's already part of the headerPanel
//        add(this.contentPanel, BorderLayout.CENTER);


    }

    // TODO: implement later
    public void actionPerformed(ActionEvent evt){

    }

    public void propertyChange(PropertyChangeEvent evt){
        HomePageState state = (HomePageState) evt.getNewValue();
        if (state.availableContests != null && state.completedContests != null && state.enrolledContests != null){
            setLists();
        }
    }

    private void setLists() {

        if (this.contentPanel != null){
            Component[] components = this.contentPanel.getComponents();
            for (var c: components) {
                this.contentPanel.remove(c); // Remove the last component
                revalidate();
                repaint();
            }
        }
        if (this.contentPanel == null){
            this.contentPanel = new JPanel(new GridLayout(1, 3, 10, 10));;
            add(this.contentPanel, BorderLayout.CENTER);
        }

        enrolledScrollPane = createScrollablePanel("Enrolled", homepageViewModel.getState().enrolledContests,  true);
        completedScrollPane = createScrollablePanel("Completed", homepageViewModel.getState().completedContests, false);
        availableScrollPane = createScrollablePanel("Available", homepageViewModel.getState().availableContests, true);

        this.contentPanel.add(availableScrollPane);
        this.contentPanel.add(enrolledScrollPane);
        this.contentPanel.add(completedScrollPane);

        revalidate();
        repaint();
    }

    /**
     * Creates a scrollable panel with a vertical BoxLayout to stack contest panels vertically.
     *
     * @param  title         the title of the panel
     * @param  contests      the list of contests to be displayed in the panel
     * @param  showTimeLeft  a flag indicating whether to display the time left for each contest
     * @return               a JScrollPane containing the scrollable panel
     */
    private JScrollPane createScrollablePanel(String title, ArrayList<Contest> contests, boolean showTimeLeft) {
        // Create a panel with a vertical BoxLayout to stack contest panels vertically
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        // Create a separate panel for each contest and add it to the main panel
        for (Contest contest : contests) {
            JPanel contestPanel = createContestPanel(contest, showTimeLeft);
            panel.add(contestPanel);
        }

        // Create a scroll pane that will only scroll vertically as needed
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    /**
     * Creates a JPanel for the given Contest with an optional time left display.
     *
     * @param  contest       the Contest object for which the panel is created
     * @param  showTimeLeft  a boolean value indicating whether to display the time left
     * @return               the created JPanel
     */
    private JPanel createContestPanel(Contest contest, boolean showTimeLeft) {
        JPanel contestPanel = new JPanel(new BorderLayout());

        JLabel descriptionLabel = new JLabel(contest.getDescription());
        descriptionLabel.setHorizontalAlignment(JLabel.LEFT);
        contestPanel.add(descriptionLabel, BorderLayout.CENTER);

        JButton infoButton = new JButton("Info");
        infoButton.setMinimumSize(new Dimension(85, 50));
        contestPanel.add(infoButton, BorderLayout.EAST);

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var user = FirebaseDataAccess.getInstance().getEntity(User.class, "Users", homepageViewModel.username);
                if (!user.getCompletedContests().contains(contest.getContestId()) && !user.getEnrolledContests().contains(contest.getContestId()) && !Objects.equals(viewModelManager.getActiveView(), EnrolledView.viewName) && !Objects.equals(viewModelManager.getActiveView(), CompletedContestView.viewName)){
                    showAvailableContestDetailsScreen(contest);
                }
                else if (!user.getEnrolledContests().contains(contest.getContestId()) && !Objects.equals(viewModelManager.getActiveView(), EnrolledView.viewName))
                {
                    showCompletedContestDetailsScreen(contest);
                }
                else if (!user.getCompletedContests().contains(contest.getContestId()) && !Objects.equals(viewModelManager.getActiveView(), AvailableContestDetailView.viewName)){
                    showEnrolledContestDetailsScreen(contest);
                }
            }
        });
        if (showTimeLeft) {
            Date javaEndDate = contest.getEndTime().toDate();
            String dateEndString = formatAsDateString(javaEndDate);
            JLabel timeLeftLabel = new JLabel("End Time: " + dateEndString); // Time calculation should be done here
            timeLeftLabel.setHorizontalAlignment(JLabel.RIGHT);
            contestPanel.add(timeLeftLabel, BorderLayout.SOUTH);
        }
        contestPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        contestPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        return contestPanel;
    }

    /**
     * Shows the available contest details screen.
     *
     * @param  contest	the contest object for which to show the details
     */
    private void showAvailableContestDetailsScreen(Contest contest) {
        AvailableContestsViewModel availableContestsViewModel = new AvailableContestsViewModel();
        AvailableContestDetailView availableContestDetailView = ContestUseCaseFactory.createAvailableContestDetailView(availableContestsViewModel, viewModelManager, contest.getContestId(), homepageViewModel.username);
//            views.add(availableContestDetailView, availableContestDetailView.viewName);
        try{
            viewModelManager.setActiveView(AvailableContestDetailView.viewName);
            viewModelManager.firePropertyChanged();
            AvailableContestDetailView.launch(availableContestDetailView);
            System.out.println(viewModelManager.getActiveView());
            System.out.println(availableContestDetailView.enrollSuccess);
            if (availableContestDetailView.enrollSuccess){
                homepageController.execute();
            }
            viewModelManager.setActiveView(this.viewName);
            viewModelManager.firePropertyChanged();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    /**
     * Shows the details screen of an enrolled contest.
     *
     * @param  contest    the contest object representing the enrolled contest
     */
    private void showEnrolledContestDetailsScreen(Contest contest) {
        EnrolledViewModel enrolledViewModel = new EnrolledViewModel();
        EnrolledView enrolledView = ContestUseCaseFactory.createEnrolledView(enrolledViewModel, FirebaseDataAccess.getInstance(), viewModelManager, contest.getContestId(), homepageViewModel.username);
        try{
            viewModelManager.setActiveView(enrolledView.viewName);
            viewModelManager.firePropertyChanged();
            EnrolledView.launch(enrolledView);
            if (enrolledView.contestExpired){
                homepageController.execute();
            }
            viewModelManager.setActiveView(this.viewName);
            viewModelManager.firePropertyChanged();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Shows the completed contest details screen.
     *
     * @param  contest    the contest object to display details for
     */
    private void showCompletedContestDetailsScreen(Contest contest) {
        CompletedContestViewModel completedContestViewModel = new CompletedContestViewModel();
        CompletedContestView completedContestView = ContestUseCaseFactory.createCompletedContestView(completedContestViewModel, FirebaseDataAccess.getInstance(), viewModelManager, contest.getContestId(), homepageViewModel.username);
        try{
            viewModelManager.setActiveView(completedContestView.viewName);
            viewModelManager.firePropertyChanged();
            CompletedContestView.launch(completedContestView);
            viewModelManager.setActiveView(this.viewName);
            viewModelManager.firePropertyChanged();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public String getViewName() {
        return this.viewName;
    }

    private static String formatAsDateString(Date date) {
        // Choose your desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the date as a string
        return dateFormat.format(date);
    }
}

