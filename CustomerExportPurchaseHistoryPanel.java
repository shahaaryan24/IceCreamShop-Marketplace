import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
/**
 * Project05 -- CustomerExportPurchaseHistory
 *
 * this class creates the customer export
 * purchase history panel.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class CustomerExportPurchaseHistoryPanel extends JPanel {
    JLabel exportMsg;
    JButton exportBtn;
    JButton back;
    JLabel filepathLbl;
    JTextField filepathTxt;
    JButton selectFileBtn;
    JFileChooser fc = new JFileChooser();
    File selectedFile;

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public CustomerExportPurchaseHistoryPanel() {
    }

    public CustomerExportPurchaseHistoryPanel(JFrame frame, JPanel customerPanel, ActionListener actionListener) {
        customerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        exportMsg = new JLabel();
        exportMsg.setBounds(175, 90, 300, 25);
        exportMsg.setText("Export a csv file with purchase history");
        this.add(exportMsg);

        filepathLbl = new JLabel("Enter export folder path");
        filepathLbl.setBounds(225, 140, 200, 25);
        this.add(filepathLbl);
        filepathTxt = new JTextField();
        filepathTxt.setBounds(425, 140, 200, 25);
        this.add(filepathTxt);

        // creating select file button
        selectFileBtn = new JButton("Select Folder");
        selectFileBtn.setBounds(650, 140, 150, 25);
        selectFileBtn.addActionListener(actionListener);
        this.add(selectFileBtn);

        // creating import button
        exportBtn = new JButton("Export");
        exportBtn.setBounds(225, 180, 80, 25);
        exportBtn.addActionListener(actionListener);
        this.add(exportBtn);

        back = new JButton("Back to Customer Menu");
        back.setBounds(325, 180, 200, 25);
        back.addActionListener(actionListener);
        this.add(back);
    }
}
