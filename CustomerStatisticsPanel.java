import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * Project05 -- CustomerStatisticsPanel
 *
 * This class creates the panel for the
 * customer to view statistics.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class CustomerStatisticsPanel extends JPanel {
    JButton report1Btn;
    JButton report2Btn;
    JButton back;
    public CustomerStatisticsPanel() {
    }

    public CustomerStatisticsPanel(JFrame frame, JPanel customerPanel, ActionListener actionListener) {
        // removes the customer panel
        customerPanel.setVisible(false);
        this.setLayout(null);
        // replacing the seller panel with the next panel
        frame.add(this);
        // creating welcome message label
        JLabel welcomeLbl = new JLabel("Welcome to Customer Statistics Dashboard!");
        welcomeLbl.setBounds(275, 120, 300, 25);
        this.add(welcomeLbl);

        // adding view store report button
        report1Btn = new JButton("View store report");
        report1Btn.setBounds(275, 200, 200, 25);
        report1Btn.addActionListener(actionListener);
        this.add(report1Btn);

        // add product report button
        report2Btn = new JButton("View product report");
        report2Btn.setBounds(525, 200, 200, 25);
        report2Btn.addActionListener(actionListener);
        this.add(report2Btn);

        // creating back button
        back = new JButton("Back");
        back.setBounds(725, 300, 80, 25);
        back.addActionListener(actionListener);
        this.add(back);
    }
}
