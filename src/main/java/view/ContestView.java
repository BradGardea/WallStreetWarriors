package view;

import interfaceAdapters.Contests.ContestViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ContestView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "contest view";

    private final ContestViewModel contestViewModel;
    public ContestView(ContestViewModel contestViewModel){
        this.contestViewModel = contestViewModel;

        this.contestViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Contest Screen");
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
