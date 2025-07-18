import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Project05 -- SellerMainPanel
 *
 * This class creates the panel that is the seller home page.
 * Sellers can pick a store to continue with from
 * this panel.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class SellerMainPanel extends JPanel {
    JButton createStore;
    JButton continueBtn;
    JButton editAcc;
    JButton logout;
    JComboBox storesDropdown;
    public SellerMainPanel() {
    }

    public SellerMainPanel(JFrame frame, JPanel loginPanel, ActionListener actionListener, CurrentUser user) {
        // removes the login panel
        loginPanel.setVisible(false);
        this.setLayout(null);
        // replacing the login panel with the next panel
        frame.add(this);
        // creating welcome message label
        JLabel welcomeLbl = new JLabel("Welcome to MarketPlace Application!");
        welcomeLbl.setBounds(125, 120, 300, 25);
        this.add(welcomeLbl);

        // adding role label
        JLabel role = new JLabel("Select a Store");
        role.setBounds(225, 150, 100, 25);
        this.add(role);

        // adding role dropdown
        String[] storesArr = user.getStoresList().split(",");
        storesDropdown = new JComboBox();
        storesDropdown.setModel(new DefaultComboBoxModel(storesArr));
        storesDropdown.setBounds(325, 150, 100, 25);
        storesDropdown.addActionListener(actionListener);
        this.add(storesDropdown);


        // adding view marketplace button
        createStore = new JButton("Create Store");
        createStore.setBounds(275, 200, 125, 25);
        createStore.addActionListener(actionListener);
        this.add(createStore);

        // add continue button
        continueBtn = new JButton("Continue");
        continueBtn.setBounds(425, 200, 100, 25);
        continueBtn.addActionListener(actionListener);
        this.add(continueBtn);

        // creating edit account button
        editAcc = new JButton("Edit Account");
        editAcc.setBounds(900, 30, 125, 25);
        editAcc.addActionListener(actionListener);
        this.add(editAcc);

        // creating log out button
        logout = new JButton("Logout");
        logout.setBounds(1050, 30, 80, 25);
        logout.addActionListener(actionListener);
        this.add(logout);
    }
}
