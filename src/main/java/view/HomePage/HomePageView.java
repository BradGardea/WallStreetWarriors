package view.HomePage;

import entity.Contest;
import interface_adapters.HomePage.HomePageController;
import interface_adapters.HomePage.HomePageViewModel;
import interface_adapters.ViewModelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class HomePageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "homepage view";
    private HomePageController homepageController;
    private HomePageViewModel homepageViewModel;
    private JScrollPane availableScrollPane;
    private JScrollPane enrolledScrollPane;
    private JScrollPane completedScrollPane;

    public HomePageView(HomePageViewModel viewModel, HomePageController controller, ViewModelManager viewModelManager){
        this.homepageViewModel = viewModel;
        this.homepageController = controller;
        this.homepageViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Header panel with title and sign-out button and welcome label
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Wall Street Warriors");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("Welcome " + homepageViewModel.username + "!", SwingConstants.CENTER);

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setHorizontalAlignment(JLabel.RIGHT);

        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);
        headerPanel.add(signOutButton, BorderLayout.EAST);

        // Main content panel with scrollable lists
        JPanel contentPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        enrolledScrollPane = createScrollablePanel("Enrolled", homepageViewModel.enrolledContests, false);
        completedScrollPane = createScrollablePanel("Completed", homepageViewModel.completedContests, false);
        availableScrollPane = createScrollablePanel("Available", homepageViewModel.availableContests, true);

        contentPanel.add(availableScrollPane);
        contentPanel.add(enrolledScrollPane);
        contentPanel.add(completedScrollPane);

        // Add sub-panels to the main panel
        add(headerPanel, BorderLayout.NORTH); // Only add the headerPanel here
        // welcomeLabel should not be added here again since it's already part of the headerPanel
        add(contentPanel, BorderLayout.CENTER);

    }

    // TODO: implement later
    public void actionPerformed(ActionEvent evt){

    }

    // TODO: implement later
    public void propertyChange(PropertyChangeEvent evt){

    }
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
    private JPanel createContestPanel(Contest contest, boolean showTimeLeft) {
        JPanel contestPanel = new JPanel(new GridLayout(1, 3)); // Adjust grid layout as needed
        JLabel descriptionLabel = new JLabel(contest.getDescription());
        JButton infoButton = new JButton("Info");
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showContestDetailsScreen(contest);
            }
        });

        JLabel timeLeftLabel = new JLabel(); // TODO calculate time

        // Add components to the panel
        contestPanel.add(descriptionLabel);
        contestPanel.add(infoButton);
        if (showTimeLeft) {
            contestPanel.add(timeLeftLabel);
        }
        contestPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return contestPanel;
    }

    private void showContestDetailsScreen(Contest contest) {
        // TODO switch screen after clicking info
    }

    public void launch(){
        this.setSize(new Dimension(600,800));
        this.setVisible(true);
    }

}
