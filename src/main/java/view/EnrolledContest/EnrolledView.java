package view.EnrolledContest;

import interfaceAdapters.Enrolled.EnrolledController;
import interfaceAdapters.Enrolled.EnrolledState;
import interfaceAdapters.Enrolled.EnrolledViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
    public JPanel frame;
    public static final String viewName = "enrolledcontest";
//    private final HomePageController homePageController;
    private final EnrolledController enrolledController;
    private final EnrolledViewModel enrolledViewModel;
    private EnrolledState enrolledState;
    private static Timer timer;

    // Variables for timer usage
    private static JLabel timerLabel;
    private static int timeLeft; // Seconds

    public boolean contestExpired;

    public EnrolledView(EnrolledController enrolledController, EnrolledViewModel viewModel) {
//        this.homePageController = controller;
        this.enrolledViewModel = viewModel;
        this.enrolledViewModel.addPropertyChangeListener(this);
        this.enrolledState = viewModel.getState();
        this.enrolledController = enrolledController;

        setModal(true);

        // J Swing stuff goes here

        // THIS CODE IS FOR CREATING THE WINDOW - PASS THE ACTUAL FRAME LATER ON?
//        JPanel frame = new JPanel(enrolledState.getTitle() + " Enrolled Contest ID: " + enrolledState.getContestId());
        this.frame = new JPanel(new BorderLayout());
//        frame.(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Actual Stuff
        // Grid layout for ease - OOS: More accurate pixel placements so better looks
        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 3 rows, 2 columns

        // Date Labels
        JLabel startDateLabel = new JLabel("Start Date: " + enrolledState.getStartDate().toString(), SwingConstants.CENTER);
        JLabel endDateLabel = new JLabel("End Date: " + enrolledState.getEndDate().toString(), SwingConstants.CENTER);

        JLabel descriptionLabel = new JLabel("Description: " + enrolledState.getDescription(), SwingConstants.CENTER);
        JLabel industryLabel = new JLabel("Industry: " + enrolledState.getIndustry(), SwingConstants.CENTER);

        // Begin Timer stuff
        timeLeft = (int) (enrolledState.getEndDate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()
                        - Instant.now().getEpochSecond());
        timerLabel = new JLabel("Time Remaining: " + formatTime(timeLeft), SwingConstants.CENTER);
        // End Timer Stuff


        // Add all labels
        topPanel.add(new JLabel(enrolledState.getTitle(), SwingConstants.CENTER)); // TITLE
        topPanel.add(industryLabel);
        topPanel.add(descriptionLabel);
        topPanel.add(new JLabel("Contest ID: " + enrolledState.getContestId(), SwingConstants.CENTER));
        topPanel.add(startDateLabel);
        topPanel.add(endDateLabel);
        topPanel.add(timerLabel);

        // Add the top panel with all labels
        frame.add(topPanel, BorderLayout.NORTH);

        // Table holder
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.LINE_AXIS));

        // What the columns are called
        String[] columns = new String[]{"Stock", "Quantity", "Purchase Price", "Current Price", "Value"};

        // Populate table
        String username = enrolledState.getUsername();
        LinkedList<String> users = (LinkedList<String>) enrolledState.getOpponents(); // TODO Load in enemies
        users.add(0, username);

        // Add a table for each user
        for (String user : users) {
            JPanel panelWithLabelAndTable = new JPanel(new BorderLayout());
            panelWithLabelAndTable.add(new JLabel(user, SwingConstants.CENTER), BorderLayout.NORTH);

            // Table data for each (Example, edit later on)
            ArrayList<Object []> dataArrayList = new ArrayList<>();

            HashMap<String, HashMap<String, HashMap<String, String>>> userPortfolios = enrolledState.getPortfolios();
            for (String s : userPortfolios.get(user).keySet()) {

                String quantity = userPortfolios.get(user).get(s).get("Quantity");

                String purchasePrice = userPortfolios.get(user).get(s).get("Purchase Price");

                String closePrice = userPortfolios.get(user).get(s).get("Close Price");

                String value = userPortfolios.get(user).get(s).get("Value");



                Object[] stock = new Object[]{
                        s,
                        quantity,
                        purchasePrice,
                        closePrice,
                        value
                };
                dataArrayList.add(stock);
            }

            Object[][] data = new Object[dataArrayList.size()][];
            for (int i = 0; i < data.length; i++) {
                data[i] = dataArrayList.get(i);
            }

            // Make table
            DefaultTableModel model = new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            JTable table = new JTable(model);
            table.setPreferredScrollableViewportSize(new Dimension(300, 50));
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
        if (timer != null) {resetTimer();}

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText("Time Remaining: " + formatTime(timeLeft));
                } else {
                    frame.removeAll();
                    frame.setLayout(new BorderLayout());
                    frame.add(new JLabel("Time's up!", SwingConstants.CENTER), BorderLayout.CENTER);

                    JButton okButton = new JButton("OK");
                    frame.add(okButton, BorderLayout.SOUTH);

                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            contestExpired = enrolledController.markContestCompleted(enrolledState.getUsername(), enrolledState.getContestId());
                            dispose();
                        }

                    });

                    frame.revalidate();
                    frame.repaint();
                    ((Timer) e.getSource()).stop();


                }
            }
        });
        timer.start();
        this.add(frame);


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
     *
     *
     * @author Mikhail Skazhenyuk
     * @version 0.0
     */
    private static String formatTime(int totalSecs) {
        int days = totalSecs / 86400;
        int hours = (totalSecs % 86400) / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }

    public static void launch(EnrolledView dialog) throws IOException {
        dialog.setSize(new Dimension(600,800));
        dialog.setVisible(true);
//        resetTimer();
        // System.exit(0);
    }

    public static void resetTimer() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    private void onOK() {
        // switch this contest and its configuration to enrolled
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
