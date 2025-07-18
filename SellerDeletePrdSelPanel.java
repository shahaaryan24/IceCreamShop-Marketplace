import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


/**
 * Project05 -- SellerDeletePrdSelPanel
 *
 * This class creates the panel that lets a
 * seller delete a product from their store.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class SellerDeletePrdSelPanel extends JPanel {
    JTextField flavorToEditTxt;
    JButton deleteBtn;
    JButton cancelBtn;
    JTable sellerDeleteProductTable;
    HashMap<String, Product> tableData;
    JScrollPane tableContainer;
    public SellerDeletePrdSelPanel() {
    }

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public SellerDeletePrdSelPanel(JFrame frame, JPanel sellerPanel, ActionListener actionListener) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        JLabel testlbl = new JLabel("Delete a product");
        testlbl.setBounds(150, 100, 150, 25);
        this.add(testlbl);

        JLabel flavorNameLbl = new JLabel("Select a product to delete");
        flavorNameLbl.setBounds(200, 525, 200, 25);
        this.add(flavorNameLbl);

        flavorToEditTxt = new JTextField();
        flavorToEditTxt.setBounds(450, 525, 200, 25);
        this.add(flavorToEditTxt);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(500, 575, 80, 25);
        deleteBtn.addActionListener(actionListener);
        this.add(deleteBtn);

        cancelBtn = new JButton("Back");
        cancelBtn.setBounds(630, 575, 80, 25);
        cancelBtn.addActionListener(actionListener);
        this.add(cancelBtn);
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
        sellerDeleteProductTable = new JTable(model);
        tableContainer = new JScrollPane(sellerDeleteProductTable);
        tableContainer.setBounds(150, 150, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Map.Entry<String, Product> item : resultList.entrySet()) {
            Product prd = item.getValue();
            sellerDeleteProductTable.setValueAt(prd.getFlavorName(), i, 0);
            sellerDeleteProductTable.setValueAt(prd.getStore(), i, 1);
            sellerDeleteProductTable.setValueAt(prd.getDescription(), i, 2);
            sellerDeleteProductTable.setValueAt(prd.getQuantity(), i, 3);
            sellerDeleteProductTable.setValueAt(prd.getPrice(), i, 4);
            i++;
        }
    }
}
