package view.EnrolledContest;

import interface_adapters.Enrolled.EnrolledController;
import interface_adapters.Enrolled.EnrolledState;
import interface_adapters.Enrolled.EnrolledViewModel;
import view.CompletedContests.CompletedContestView;
// import interface_adapters.MAINVIEWPACKAGE.MAINVIEWState; TODO CHANGE THIS

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.ZoneId;

/**
 * View of a specific Enrolled StockContest object.
 *
 * This class displays the features of a specific StockContest which the current logged in user is enrolled in
 * and has selected to be displayed.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledView extends JDialog implements ActionListener, PropertyChangeListener {
    public final String viewName = "enrolledcontest";
//    private final HomePageController homePageController;
    private final EnrolledViewModel enrolledViewModel;
    private EnrolledState enrolledState;

    // Variables for timer usage
    private static JLabel timerLabel;
    private static int timeLeft; // Seconds

    public EnrolledView(EnrolledController enrolledController, EnrolledViewModel viewModel) {
//        this.homePageController = controller;
        this.enrolledViewModel = viewModel;
        this.enrolledViewModel.addPropertyChangeListener(this);
        this.enrolledState = viewModel.getState();

        // J Swing stuff goes here

        // THIS CODE IS FOR CREATING THE WINDOW - PASS THE ACTUAL FRAME LATER ON?
//        JPanel frame = new JPanel(enrolledState.getTitle() + " Enrolled Contest ID: " + enrolledState.getContestId());
        JPanel frame = new JPanel(new BorderLayout());
//        frame.(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Actual Stuff
        // Grid layout for ease - OOS: More accurate pixel placements so better looks
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns

        // Date Labels
        JLabel startDateLabel = new JLabel("Start Date: " + enrolledState.getStartDate().toString(), SwingConstants.CENTER);
        JLabel endDateLabel = new JLabel("End Date: " + enrolledState.getEndDate().toString(), SwingConstants.CENTER);

        // Begin Timer stuff
        timeLeft = (int) (enrolledState.getEndDate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()
                        - enrolledState.getEndDate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()); // e.g., 1 hour in seconds
        timerLabel = new JLabel("Time Remaining: " + formatTime(timeLeft), SwingConstants.CENTER);
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText("Time Remaining: " + formatTime(timeLeft));
                } else {
                    timerLabel.setText("Time's up!");
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
        // End Timer Stuff


        // Add all labels
        topPanel.add(new JLabel(enrolledState.getTitle(), SwingConstants.CENTER)); // TITLE
        topPanel.add(startDateLabel);
        topPanel.add(new JLabel("Contest ID: " + enrolledState.getContestId(), SwingConstants.CENTER));
        topPanel.add(endDateLabel);
        topPanel.add(timerLabel);

        // Add the top panel with all labels
        frame.add(topPanel, BorderLayout.NORTH);

        // Table holder
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.LINE_AXIS));

        // What the columns are called
        String[] columns = new String[]{"Stock", "Quantity", "Purchase Price", "Current Price", "Value"};

        // Opponents
        String[] competitors = enrolledState.getOpponents().toArray(new String[0]); // TODO Load in enemies, just example

        // Add a table for each opponent with random data
        for (String competitor : competitors) {
            JPanel panelWithLabelAndTable = new JPanel(new BorderLayout());
            panelWithLabelAndTable.add(new JLabel(competitor, SwingConstants.CENTER), BorderLayout.NORTH);

            // Table data for each (Example, edit later on)
            Object[][] data = new Object[][]{
                    {"AAPL", "10", "$150.00", "$155.00", "$1550.00"},
                    {"MSFT", "15", "$200.00", "$210.00", "$3150.00"}
                    // Add more stocks as needed
            };

            // Make table
            DefaultTableModel model = new DefaultTableModel(data, columns);
            JTable table = new JTable(model);
            table.setPreferredScrollableViewportSize(new Dimension(300, 100));
            table.setFillsViewportHeight(true);

            // Add table to individual scroll pane (So you can scroll through all the stocks)
            JScrollPane verticalScrollPane = new JScrollPane(table);
            verticalScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            // Add a margin around the table (bumper)
            verticalScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Add the scroll pane to the panel
            panelWithLabelAndTable.add(verticalScrollPane);
            tablePanel.add(panelWithLabelAndTable);
        }

        // Add the table panel to the frame within a scroll pane for horizontal scrolling (too many enemies)
        JScrollPane horizontalScrollPane = new JScrollPane(tablePanel);
        horizontalScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        horizontalScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        frame.add(horizontalScrollPane, BorderLayout.CENTER);
//        JButton cancelButton = new JButton("Cancel");
//        frame.add(cancelButton);
        this.add(frame);


//        cancelButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        });
//        // Pack and show the frame
//        frame.pack();
//        frame.setVisible(true);
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

    /**
     * Helper object for time.
     *
     * Formats the time in a good manner (hours, mins, secs)
     * TODO Add days?
     *
     * @author Mikhail Skazhenyuk
     * @version 0.0
     */
    private static String formatTime(int totalSecs) {
        int days = totalSecs / (3600 * 24);
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }

    public static void launch(EnrolledView dialog) throws IOException {
        dialog.setSize(new Dimension(600,800));
        dialog.setVisible(true);
        // System.exit(0);
    }

    private void onOK() {
        // switch this contest and its configuration to enrolledw
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
