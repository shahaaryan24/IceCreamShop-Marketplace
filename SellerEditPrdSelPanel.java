import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Project05 -- SellerEditPrdSelPanel
 *
 * This class creates the panel that lets the
 * seller select a product to edit in their store.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class SellerEditPrdSelPanel extends JPanel {
    JTextField flavorToEditTxt;
    JButton editPrdInEditPrdSelPnlBtn;
    JButton sellerEditPrdSelPanelCancelBtn;
    JTable sellerEditProductTable;
    HashMap<String, Product> tableData;
    JScrollPane tableContainer;
    public SellerEditPrdSelPanel() {
    }

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public SellerEditPrdSelPanel(JFrame frame, JPanel sellerPanel, ActionListener actionListener) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        JLabel testlbl = new JLabel("Edit a product");
        testlbl.setBounds(150, 100, 150, 25);
        this.add(testlbl);

        JLabel flavorNameLbl = new JLabel("Select a product to edit");
        flavorNameLbl.setBounds(200, 525, 200, 25);
        this.add(flavorNameLbl);

        flavorToEditTxt = new JTextField();
        flavorToEditTxt.setBounds(450, 525, 200, 25);
        this.add(flavorToEditTxt);

        editPrdInEditPrdSelPnlBtn = new JButton("Edit");
        editPrdInEditPrdSelPnlBtn.setBounds(500, 575, 80, 25);
        editPrdInEditPrdSelPnlBtn.addActionListener(actionListener);
        this.add(editPrdInEditPrdSelPnlBtn);

        sellerEditPrdSelPanelCancelBtn = new JButton("Back");
        sellerEditPrdSelPanelCancelBtn.setBounds(630, 575, 80, 25);
        sellerEditPrdSelPanelCancelBtn.addActionListener(actionListener);
        this.add(sellerEditPrdSelPanelCancelBtn);
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
        sellerEditProductTable = new JTable(model);
        tableContainer = new JScrollPane(sellerEditProductTable);
        tableContainer.setBounds(150, 150, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Map.Entry<String, Product> item : resultList.entrySet()) {
            Product prd = item.getValue();
            sellerEditProductTable.setValueAt(prd.getFlavorName(), i, 0);
            sellerEditProductTable.setValueAt(prd.getStore(), i, 1);
            sellerEditProductTable.setValueAt(prd.getDescription(), i, 2);
            sellerEditProductTable.setValueAt(prd.getQuantity(), i, 3);
            sellerEditProductTable.setValueAt(prd.getPrice(), i, 4);
            i++;
        }
    }
}
