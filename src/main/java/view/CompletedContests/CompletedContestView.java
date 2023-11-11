package view.CompletedContests;

import interface_adapters.CompletedContests.CompletedContestController;
import interface_adapters.CompletedContests.CompletedContestViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.zip.GZIPInputStream;

public class CompletedContestView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "completed contest";

    private CompletedContestController completedContestController;

    private CompletedContestViewModel completedContestViewModel;

    public CompletedContestView(CompletedContestController controller, CompletedContestViewModel viewModel){
        this.completedContestController = controller;
        this.completedContestViewModel = viewModel;
        this.completedContestViewModel.addPropertyChangeListener(this);

//        // Java Swing Code

        // this panel holds all the sub panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        mainPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Creating JLabels for the topPanel
        // TODO: Make it so these update with data from firebase through the view model.
        JLabel contestName = new JLabel("Contest Name");
        JLabel startDate = new JLabel("Start Date: 2023-01-01", SwingConstants.CENTER);
        JLabel contestIndustry = new JLabel("Contest Industry");
        JLabel endDate = new JLabel("End Date: 2023-02-01", SwingConstants.CENTER);
        JLabel yourPortfolio = new JLabel("Your Portfolio");
        JLabel leaderboard = new JLabel("Leaderboard", SwingConstants.CENTER);

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
//        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

        // Column Names For Table
        String[] columns = {"Ticker", "Quantity", "Purchase Price", "End Price", "Value"};
        Object[][] data = {
                {"AAPL", "10", "150.00", "155.00", "1550.00"},
                {"MSFT", "10", "200.00", "210.00", "2100.00"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
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

        // Mock Data
        // TODO: Replace with real data from firebase
        String[] usernames = {
                "StockMaster83",
                "BullishTrader",
                "MarketGuru007",
                "EquityExplorer",
                "PortfolioPro22",
                "TradeWhiz",
                "InvestNinja",
                "WealthHarbor",
                "StockSavvy2023",
                "FinanceWizardX"
        };

        JList<String> leaderboardData = new JList<>(usernames);

        // Making the JList Scrollable
        JScrollPane listScrollPane = new JScrollPane(leaderboardData);
        listPanel.add(listScrollPane);
        centerPanel.add(listPanel);

        // Creating Bottom Panel to Hold Profit and Placement
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JLabel profit = new JLabel("Profit: $1000.00");
        JLabel placement = new JLabel("Placement: 1st");

        bottomPanel.add(profit);
        bottomPanel.add(placement);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 30, 10));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.setPreferredSize( new Dimension(800, 300));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.setPreferredSize( new Dimension(800, 600));

        this.add(mainPanel);
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
