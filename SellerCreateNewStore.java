import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Project05 -- SellerCreateNewStore
 *
 * This class creates the panel that
 * allows sellers to create a new store.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class SellerCreateNewStore extends JPanel {
    JLabel createMsg;
    JLabel createStore;
    JTextField storeNameTxt;
    JButton create;
    JButton back;
    public SellerCreateNewStore() {
    }

    public SellerCreateNewStore(JFrame frame, JPanel sellerMainPanel, ActionListener actionListener) {
        sellerMainPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        createMsg = new JLabel();
        createMsg.setBounds(175, 90, 300, 25);
        createMsg.setText("Create a new Store:");
        this.add(createMsg);

        createStore = new JLabel("Enter name of the store:");
        createStore.setBounds(225, 130, 200, 25);
        this.add(createStore);
        storeNameTxt = new JTextField();
        storeNameTxt.setBounds(425, 130, 200, 25);
        this.add(storeNameTxt);

        // creating create button
        create = new JButton("Create");
        create.setBounds(225, 170, 80, 25);
        create.addActionListener(actionListener);
        this.add(create);

        back = new JButton("Back");
        back.setBounds(325, 170, 80, 25);
        back.addActionListener(actionListener);
        this.add(back);
    }
}
