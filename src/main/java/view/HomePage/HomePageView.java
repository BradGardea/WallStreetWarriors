package view.HomePage;

import interface_adapters.Contests.ContestViewModel;
import interface_adapters.HomePage.HomePageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePageView extends JPanel implements ActionListener, PropertyChangeListener {
    private JButton signOutButton;
    private JScrollPane CompletedList;
    private JScrollPane EnrolledList;
    private JScrollPane AvailableList;

    public final String viewName = "homepage view";

    private final HomePageViewModel homepageViewModel;

    public HomePageView(HomePageViewModel homepageViewModel){
        this.homepageViewModel = homepageViewModel;

        this.homepageViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Home Page Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
    }


    // TODO: implement later
    public void actionPerformed(ActionEvent evt){

    }

    // TODO: implement later
    public void propertyChange(PropertyChangeEvent evt){

    }
}
