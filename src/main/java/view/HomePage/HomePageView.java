package view.HomePage;

import entity.Contest;
import interface_adapters.HomePage.HomePageController;
import interface_adapters.HomePage.HomePageViewModel;

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

    public HomePageView(HomePageViewModel viewModel){

        this.homepageViewModel = viewModel;
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
        availableScrollPane = createScrollablePanel("Available", homepageViewModel.availableContests);
        enrolledScrollPane = createScrollablePanel("Enrolled", homepageViewModel.enrolledContests);
        completedScrollPane = createScrollablePanel("Completed", homepageViewModel.completedContests);

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
    private JScrollPane createScrollablePanel(String title, ArrayList<Contest> contests) {
        // This method would create a panel that can be filled with data.
        // empty panel with a border.
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 5));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }
}
