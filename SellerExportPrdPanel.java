import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

/**
 * Project 05 -- SellerExportPanel
 *
 * This class provides the implementation
 * for the GUI of the Export Product
 * panel in the seller menu
 *
 *   
 *  
 *  
 *   
 *
 * @version November 23, 2023
 */
public class SellerExportPrdPanel extends JPanel {
    JLabel importMessage;
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
    public SellerExportPrdPanel() {
    }

    public SellerExportPrdPanel(JFrame frame, JPanel sellerPanel, ActionListener actionListener, CurrentUser user) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        importMessage = new JLabel();
        importMessage.setBounds(175, 90, 300, 25);
        importMessage.setText("Export a csv file");
        this.add(importMessage);

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

        back = new JButton("Back to Seller Menu");
        back.setBounds(325, 180, 150, 25);
        back.addActionListener(actionListener);
        this.add(back);
    }
}
