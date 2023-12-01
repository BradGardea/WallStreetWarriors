package view.CompletedContests;

import interface_adapters.CompletedContests.CompletedContestController;
import interface_adapters.CompletedContests.CompletedContestViewModel;
import view.AvailableContests.AvailableContestDetailView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CompletedContestView extends JDialog implements ActionListener, PropertyChangeListener {
    private JPanel mainPanel;
    private JTable table1;
    private JList list1;
    private JLabel contestName;
    private JLabel contestIndustry;
    private JLabel placement;
    private JLabel profit;


    public final String viewName = "completed contest";

    private CompletedContestController completedContestController;

    private CompletedContestViewModel completedContestViewModel;

    public CompletedContestView(CompletedContestController controller, CompletedContestViewModel viewModel) {
        this.completedContestController = controller;
        this.completedContestViewModel = viewModel;
        this.completedContestViewModel.addPropertyChangeListener(this);

        // Java Swing Code

        // this panel holds all the sub panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Creating JLabels for the topPanel
        JLabel contestName = new JLabel(completedContestViewModel.contestName);

        // TODO: Find a way to make the date only show the date and not time.

        Date javaStartDate = completedContestViewModel.startDate.toDate();
        String dateStartString = formatAsDateString(javaStartDate);

        String startDateLabel = "Start Date: " + dateStartString;
        JLabel startDate = new JLabel(startDateLabel, SwingConstants.CENTER);
        JLabel contestIndustry = new JLabel(completedContestViewModel.industry);

        // TODO: Same as above
        Date javaEndDate = completedContestViewModel.endDate.toDate();
        String dateEndString = formatAsDateString(javaEndDate);

        String endDateLabel = "End Date: " + dateEndString;
        JLabel endDate = new JLabel(endDateLabel, SwingConstants.CENTER);
        JLabel yourPortfolio = new JLabel(completedContestViewModel.YOUR_PORTFOLIO);
        JLabel leaderboard = new JLabel(completedContestViewModel.LEADERBOARD_LABEL, SwingConstants.CENTER);

        // Adding the JLabels to the Top Panel
        topPanel.add(contestName);
        topPanel.add(startDate);
        topPanel.add(contestIndustry);
        topPanel.add(endDate);
        topPanel.add(yourPortfolio);
        topPanel.add(leaderboard);


        // Creating Bottom Panel which houses a table and a list
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        // Column Names For Table
        String[] columns = {"Ticker", "Quantity", "Purchase Price", "End Price", "Value"};


        HashMap<String, HashMap<String, String>> data = completedContestViewModel.portfolio;

        Object[][] dataArray = convertHashMapDataToArray(data);
        DefaultTableModel model = new DefaultTableModel(dataArray, columns);
        JTable table = new JTable(model);

        // Adding the table to a scroll pane to allow for scroll functionality.
        JScrollPane verticalScrollPane = new JScrollPane(table);
        verticalScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        verticalScrollPane.setBorder(new EmptyBorder(0, 10, 10, 10));
        // adding the table panel to the bottom panel.
        tablePanel.add(verticalScrollPane);
        centerPanel.add(tablePanel);

        // Creating A List Panel for the leaderboard
        JPanel listPanel = new JPanel(new BorderLayout());

        // Setting th leaderboard
        String[] usernames = completedContestViewModel.leaderboard;
        JList<String> leaderboardData = new JList<>(usernames);

        // Making the JList Scrollable
        JScrollPane listScrollPane = new JScrollPane(leaderboardData);
        listPanel.add(listScrollPane);
        centerPanel.add(listPanel);

        // Creating Bottom Panel to Hold Profit and Placement
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        String profitLabel = "Profit: " + completedContestViewModel.profit;
        String placementLabel = "Placement: " + completedContestViewModel.placement;
        JLabel profit = new JLabel(profitLabel);
        JLabel placement = new JLabel(placementLabel);
        JLabel empty = new JLabel("");
        JButton cancelButton = new JButton("Cancel");

        bottomPanel.add(profit);
        bottomPanel.add(placement);
        bottomPanel.add(empty);
        bottomPanel.add(cancelButton);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 30, 10));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.setPreferredSize(new Dimension(800, 300));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.setPreferredSize(new Dimension(800, 600));

        this.add(mainPanel);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

    }


    private Object[][] convertHashMapDataToArray(HashMap<String, HashMap<String, String>> data) {

        int length = data.size();
        Object[][] arrayData = new Object[length][5];
        int iteration = 0;
        // iterates through every ticker
        for (String ticker : data.keySet()) {
            Object[] row = new Object[5];
            row[0] = data.get(ticker).get("Ticker");
            row[1] = data.get(ticker).get("Quantity");
            row[2] = data.get(ticker).get("Purchase Price");
            row[3] = data.get(ticker).get("End Price");
            row[4] = data.get(ticker).get("Value");

            arrayData[iteration] = row;
            iteration += 1;
        }

        return arrayData;
    }

    private static String formatAsDateString(Date date) {
        // Choose your desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the date as a string
        return dateFormat.format(date);
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
//    private Object[][] convertHashMapDataToArray(HashMap<String, HashMap<String, String>> data) {
//
//        int length = data.size();
//        Object[][] arrayData = new Object[length][5];
//        int iteration = 0;
//        // iterates through every ticker
//        for (String ticker : data.keySet()) {
//            Object[] row = new Object[5];
//            row[0] = data.get(ticker).get("Ticker");
//            row[1] = data.get(ticker).get("Quantity");
//            row[2] = data.get(ticker).get("Purchase Price");
//            row[3] = data.get(ticker).get("End Price");
//            row[4] = data.get(ticker).get("Value");
//
//            arrayData[iteration] = row;
//            iteration += 1;
//        }
//
//        return arrayData;
//    }

    public static void launch(CompletedContestView dialog) throws IOException { //TODO: temp
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

