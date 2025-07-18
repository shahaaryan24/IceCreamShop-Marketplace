import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Project05 -- EditAccountSettingsPanel
 *
 * This class creates the panel that
 * lets users edit or delete their accounts.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class EditAccountSettingsPanel extends JPanel {
    JLabel pageTitle;
    JLabel userID;
    JLabel updatePwd;
    JTextField updatePwdTxt;
    JButton updateBtn;
    JButton backBtn;
    JLabel delete;
    JButton deleteBtn;

    public EditAccountSettingsPanel() {
    }

    public EditAccountSettingsPanel(JFrame frame, JPanel sellerMainPanel, ActionListener actionListener, CurrentUser user) {
        sellerMainPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        userID = new JLabel();
        userID.setBounds(350, 30, 300, 25);
        userID.setText("Edit Account Settings " );
        this.add(userID);

        userID = new JLabel();
        userID.setBounds(175, 90, 300, 25);
        userID.setText("Your User ID is: " + user.getUsername());
        this.add(userID);

        updatePwd = new JLabel("New Password:");
        updatePwd.setBounds(225, 140, 100, 25);
        this.add(updatePwd);
        updatePwdTxt = new JTextField();
        updatePwdTxt.setBounds(325, 140, 200, 25);
        this.add(updatePwdTxt);


        // creating edit button
        updateBtn = new JButton("Update Password");
        updateBtn.setBounds(225, 180, 140, 25);
        updateBtn.addActionListener(actionListener);
        this.add(updateBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(375, 180, 80, 25);
        backBtn.addActionListener(actionListener);
        this.add(backBtn);

        delete = new JLabel("To delete your user account click on Delete Account button");
        delete.setBounds(225, 260, 900, 25);
        this.add(delete);
        deleteBtn = new JButton("Delete Account");
        deleteBtn.setBounds(225, 290, 160, 25);
        deleteBtn.addActionListener(actionListener);
        this.add(deleteBtn);
    }

}
