package view;

import interface_adapters.enrolled.EnrolledController;
import interface_adapters.enrolled.EnrolledViewModel;
// import interface_adapters.MAINVIEWPACKAGE.MAINVIEWState; TODO CHANGE THIS

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View of a specific Enrolled StockContest object.
 *
 * This class displays the features of a specific StockContest which the current logged in user is enrolled in
 * and has selected to be displayed.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "enrolledcontest";
    private final EnrolledViewModel enrolledViewModel;

    // J Swing stuff goes here
    // TODO Add EnrolledView Java Swing stuff - decide on format






    // J Swing stuff ends here
    private final EnrolledController enrolledController;

    public EnrolledView(EnrolledViewModel enrolledViewModel, EnrolledController controller, EnrolledViewModel viewModel) {
        this.enrolledController = controller;
        this.enrolledViewModel = viewModel;
        this.enrolledViewModel.addPropertyChangeListerner(this); // TODO

        // J Swing Stuff blah blah blah

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
