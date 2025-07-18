import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Project05 -- CustomerViewShoppingCartPanel
 *
 * This class creates the panel that
 * displays the shopping cart for the customer.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class CustomerViewShoppingCartPanel extends JPanel {
    JTextField flavorToEditTxt;
    JTextField storeSelectedTxt;
    JButton deleteBtn;
    JButton cancelBtn;
    JButton purchaseBtn;
    JTable shoppingCartProductTable;
    ArrayList<String> tableData;
    JScrollPane tableContainer;
    public CustomerViewShoppingCartPanel() {
    }

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public CustomerViewShoppingCartPanel(JFrame frame, JPanel sellerPanel, ActionListener actionListener) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        JLabel testlbl = new JLabel("View Shopping Cart");
        testlbl.setBounds(150, 100, 150, 25);
        this.add(testlbl);

        JLabel testlbl1 = new JLabel("To Delete a flavor from shopping cart:");
        testlbl1.setBounds(200, 495, 250, 25);
        this.add(testlbl1);

        JLabel flavorNameLbl = new JLabel("Select a product to delete");
        flavorNameLbl.setBounds(200, 525, 200, 25);
        this.add(flavorNameLbl);

        flavorToEditTxt = new JTextField();
        flavorToEditTxt.setBounds(450, 525, 200, 25);
        this.add(flavorToEditTxt);

        JLabel storeNameLbl = new JLabel("Enter the store name:");
        storeNameLbl.setBounds(200, 550, 200, 25);
        this.add(storeNameLbl);

        storeSelectedTxt = new JTextField();
        storeSelectedTxt.setBounds(450, 550, 200, 25);
        this.add(storeSelectedTxt);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(500, 580, 80, 25);
        deleteBtn.addActionListener(actionListener);
        this.add(deleteBtn);

        cancelBtn = new JButton("Back");
        cancelBtn.setBounds(630, 580, 80, 25);
        cancelBtn.addActionListener(actionListener);
        this.add(cancelBtn);

        purchaseBtn = new JButton("Purchase");
        purchaseBtn.setBounds(840, 580, 100, 25);
        purchaseBtn.addActionListener(actionListener);
        this.add(purchaseBtn);
    }

    public void setTableData(ArrayList<String> resultList) {
        tableData = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Time Added", "Customer Name", "Store", "Flavor", "Quantity " +
                    "Purchased", "Unit Price", "Total Price"};

            @Override
            public int getRowCount() {
                return resultList.size();
            }

            public int getColumnCount() {
                return 7;
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
        shoppingCartProductTable = new JTable(model);
        tableContainer = new JScrollPane(shoppingCartProductTable);
        tableContainer.setBounds(150, 130, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (String item : resultList) {
            shoppingCartProductTable.setValueAt(item.split(",")[0], i, 0);
            shoppingCartProductTable.setValueAt(item.split(",")[2], i, 1);
            shoppingCartProductTable.setValueAt(item.split(",")[3], i, 2);
            shoppingCartProductTable.setValueAt(item.split(",")[4], i, 3);
            shoppingCartProductTable.setValueAt(item.split(",")[5], i, 4);
            shoppingCartProductTable.setValueAt(item.split(",")[6], i, 5);
            shoppingCartProductTable.setValueAt(item.split(",")[7], i, 6);
            i++;
        }
    }
}
