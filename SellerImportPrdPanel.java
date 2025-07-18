import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Project05 -- SellerImportPrdPanel
 *
 * This class creates the panel that lets
 * sellers import products into their stores.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class SellerImportPrdPanel extends JPanel {
    JLabel importMessage;
    JButton importBtn;
    JButton back;
    JLabel filepathLbl;
    JTextField filepathTxt;
    JTable sellerProductsTable;
    JButton selectFileBtn;
    JFileChooser fc = new JFileChooser();
    File selectedFile;
    HashMap<String, Product> tableData;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public SellerImportPrdPanel() {
    }

    public SellerImportPrdPanel(JFrame frame, JPanel sellerPanel, ActionListener actionListener, CurrentUser user) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        importMessage = new JLabel();
        importMessage.setBounds(150, 90, 300, 25);
        importMessage.setText("Import a csv file");
        this.add(importMessage);

        filepathLbl = new JLabel("Enter import file path");
        filepathLbl.setBounds(225, 140, 200, 25);
        this.add(filepathLbl);
        filepathTxt = new JTextField();
        filepathTxt.setBounds(425, 140, 200, 25);
        this.add(filepathTxt);

        // creating select file button
        selectFileBtn = new JButton("Select File");
        selectFileBtn.setBounds(650, 140, 100, 25);
        selectFileBtn.addActionListener(actionListener);
        this.add(selectFileBtn);

        // creating import button
        importBtn = new JButton("Import");
        importBtn.setBounds(225, 180, 80, 25);
        importBtn.addActionListener(actionListener);
        this.add(importBtn);

        back = new JButton("Back to Seller Menu");
        back.setBounds(325, 180, 150, 25);
        back.addActionListener(actionListener);
        this.add(back);

    }

    public void setTableData(HashMap<String, Product> resultList) {
        tableData = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Flavor", "Store", "Description", "Quantity", "Price"};

            @Override
            public int getRowCount() {
                return resultList.size();
            }

            public int getColumnCount() {
                return 5;
            }
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
            @Override
            public String getColumnName(int index) {
                return columnHeaders[index];
            }
        };

        if (tableContainer != null) {
            this.remove(tableContainer);
        }
        sellerProductsTable = new JTable(model);
        tableContainer = new JScrollPane(sellerProductsTable);
        tableContainer.setBounds(150, 230, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Map.Entry<String, Product> item : resultList.entrySet()) {
            Product prd = item.getValue();
            sellerProductsTable.setValueAt(prd.getFlavorName(), i, 0);
            sellerProductsTable.setValueAt(prd.getStore(), i, 1);
            sellerProductsTable.setValueAt(prd.getDescription(), i, 2);
            sellerProductsTable.setValueAt(prd.getQuantity(), i, 3);
            sellerProductsTable.setValueAt(prd.getPrice(), i, 4);
            i++;
        }
    }
}
