import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Project05 -- Client
 *
 * This class implements the entire functionality
 * of the program on the client side. The Client component serves
 * as the operational hub, managing transactions, product listings
 * and user interactions within our program using gui.
 *
 *  
 *
 * @version November 22, 2023
 */
public class Client extends JComponent implements Runnable {
    JFrame frame;
    JButton loginBtn;
    JTextField usernameTxt;
    JPasswordField pwdText;
    JButton exitBtn;
    JButton createAccBtn;
    JPanel loginPanel;
    JLabel errorText;
    JPanel createAccPanel;
    JTextField newAccUserTxt;
    JTextField newAccPwdText;
    JButton createBtn;
    JButton backToLoginBtn;
    JComboBox rolesDropdown;
    JTextField storeTxt;
    JLabel storeName;
    JLabel successText;
    JPanel sellerPanel;
    JButton createProductBtn;
    JButton editProductBtn;
    JButton deleteProductBtn;
    JButton importProductsBtn;
    JButton exportProductsBtn;
    JButton salesByStoreBtn;
    JButton viewStatisticsBtn;
    JButton shoppingCartBtn;
    JButton logoutBtn;
    JButton homeBtn;
    SellerEditPrdSelPanel sellerEditPrdSelPanel = new SellerEditPrdSelPanel();
    SellerEditProductPanel sellerEditProductPanel = new SellerEditProductPanel();
    SellerDeletePrdSelPanel sellerDeletePrdSelPanel = new SellerDeletePrdSelPanel();
    SellerCreatePrdPanel sellerCreatePrdPanel = new SellerCreatePrdPanel();
    SellerImportPrdPanel sellerImportPrdPanel = new SellerImportPrdPanel();
    SellerExportPrdPanel sellerExportPrdPanel = new SellerExportPrdPanel();
    SellerViewSalesByStore sellerViewSalesByStore = new SellerViewSalesByStore();
    CreateCustomerPanel createCustomerPanel = new CreateCustomerPanel();
    CustomerSearchForPrdPanel customerSearchForPrdPanel = new CustomerSearchForPrdPanel();
    CustomerPurchaseHistoryPanel customerPurchaseHistoryPanel = new CustomerPurchaseHistoryPanel();
    CustomerExportPurchaseHistoryPanel customerExportPurchaseHistoryPanel = new CustomerExportPurchaseHistoryPanel();
    CustomerViewMarketPlaceSelectionPanel customerViewMarketPlaceSelectionPanel = new
            CustomerViewMarketPlaceSelectionPanel();
    CustomerPurchasePrdPanel customerPurchasePrdPanel = new CustomerPurchasePrdPanel();
    CustomerViewShoppingCartPanel customerViewShoppingCartPanel = new CustomerViewShoppingCartPanel();
    SellerMainPanel sellerMainPanel = new SellerMainPanel();
    SellerCreateNewStore sellerCreateNewStore = new SellerCreateNewStore();
    SellerViewShoppingCartPanel sellerViewShoppingCartPanel = new SellerViewShoppingCartPanel();
    SellerStatisticsPanel sellerStatisticsPanel = new SellerStatisticsPanel();
    SellerStatisticsCustomerReportPanel sellerStatisticsCustomerReportPanel = new
            SellerStatisticsCustomerReportPanel();
    SellerStatisticsProductReportPanel sellerStatisticsProductReportPanel = new SellerStatisticsProductReportPanel();
    CustomerStatisticsPanel customerStatisticsPanel = new CustomerStatisticsPanel();
    CustomerStatisticsStoreReportPanel customerStatisticsStoreReportPanel = new CustomerStatisticsStoreReportPanel();
    CustomerStatisticsProductReportPanel customerStatisticsProductReportPanel = new
            CustomerStatisticsProductReportPanel();
    EditAccountSettingsPanel editAccountSettingsPanel = new EditAccountSettingsPanel();
    CurrentUser user = new CurrentUser();
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    ObjectInputStream objReader;
    public Client(Socket socket) {
        this.socket = socket;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginBtn) {
                loginButtonAction();
            }
            //exit login page
            if (e.getSource() == exitBtn) {
                frame.dispose();
            }
            // go to create account page from login page
            if (e.getSource() == createAccBtn) {
                accountCreationPanel();
            }
            // go back to login page from create account page
            if (e.getSource() == backToLoginBtn) {
                createAccPanel.setVisible(false);
                createLoginPanel();
            }
            // select role on create account page
            if (e.getSource() == rolesDropdown) {
                if (Objects.requireNonNull(rolesDropdown.getSelectedItem()).equals("Customer")) {
                    storeName.setText("");
                    createAccPanel.remove(storeTxt);
                }
                if (rolesDropdown.getSelectedItem().equals("Seller")) {
                    storeName.setText("Store");
                    createAccPanel.add(storeTxt);
                }
            }
            // create an account on create account page
            if (e.getSource() == createBtn) {
                createBtnAction();
            }
            // create a product on seller page
            if (e.getSource() == createProductBtn) {
                createProductPanel();
                try {
                    selectCreateProductAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            // edit product on seller page
            if (e.getSource() == editProductBtn) {
                sellerEditProductPanel.setVisible(false);
                createEditProductPanel();
                try {
                    sellerEditProductPanel.setVisible(false);
                    selectEditProductAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            // delete product on seller page
            if (e.getSource() == deleteProductBtn) {
                createDeleteProductPanel();
                try {
                    selectDeleteProductAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            // import products on seller page
            if (e.getSource() == importProductsBtn) {
                createImportPrdPanel();
                try {
                    selectImportProductAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            // export products on seller page
            if (e.getSource() == exportProductsBtn) {
                createExportPrdPanel();
            }
            // view sales of store on seller page
            if (e.getSource() == salesByStoreBtn) {
                createVIewSalesPanel();
                try {
                    salesByStoreBtnAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            // view statistics on seller page
            if (e.getSource() == viewStatisticsBtn) {
                sellerStatisticsCustomerReportPanel.setVisible(false);
                sellerStatisticsProductReportPanel.setVisible(false);
                createSellerStatisticsPanel();
            }
            // logout of seller page and return to login page
            if (e.getSource() == logoutBtn) {
                sellerPanel.setVisible(false);
                createLoginPanel();
            }
            if (e.getSource() == sellerDeletePrdSelPanel.deleteBtn) {
                try {
                    deleteBtnInDeletePanelAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerDeletePrdSelPanel.cancelBtn) {
                sellerDeletePrdSelPanel.setVisible(false);
                sellerPanel.setVisible(true);
            }
            if (e.getSource() == sellerCreatePrdPanel.create) {
                try {
                    createBtnInCreatePrdPanelAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerCreatePrdPanel.cancel) {
                sellerCreatePrdPanel.setVisible(false);
                sellerPanel.setVisible(true);
            }
            if (e.getSource() == sellerImportPrdPanel.selectFileBtn) {
                sellerImportPrdPanel.fc.addChoosableFileFilter(new CsvFilter());
                sellerImportPrdPanel.fc.setAcceptAllFileFilterUsed(false);
                sellerImportPrdPanel.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnValue = sellerImportPrdPanel.fc.showDialog(frame, "Select");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    sellerImportPrdPanel.selectedFile = sellerImportPrdPanel.fc.getSelectedFile();
                    sellerImportPrdPanel.filepathTxt.setText(sellerImportPrdPanel.selectedFile.getPath());
                }
            }
            if (e.getSource() == sellerImportPrdPanel.importBtn) {
                try {
                    importProductsBtnAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerImportPrdPanel.back) {
                sellerImportPrdPanel.setVisible(false);
                sellerPanel.setVisible(true);
            }
            if (e.getSource() == sellerExportPrdPanel.exportBtn) {
                try {
                    exportProductsBtnAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerExportPrdPanel.selectFileBtn) {
                sellerExportPrdPanel.fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = sellerExportPrdPanel.fc.showDialog(frame, "Select");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    sellerExportPrdPanel.selectedFile = sellerExportPrdPanel.fc.getSelectedFile();
                    sellerExportPrdPanel.filepathTxt.setText(sellerExportPrdPanel.selectedFile.getPath() + "\\" +
                            user.getStoreName() + ".csv");
                }
            }
            if (e.getSource() == sellerExportPrdPanel.back) {
                sellerExportPrdPanel.setVisible(false);
                sellerPanel.setVisible(true);
            }
            if (e.getSource() == sellerViewSalesByStore.back) {
                sellerViewSalesByStore.setVisible(false);
                sellerPanel.setVisible(true);
            }
            // customer panel
            if (e.getSource() == createCustomerPanel.viewMarketPlace) {
                customerPurchasePrdPanel.setVisible(false);
                createViewMarketPlacePanel();
                try {
                    customerViewMarketPlaceBtnAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == createCustomerPanel.searchForProducts) {
                searchForProductsAction();
            }
            if (e.getSource() == createCustomerPanel.viewPurchaseHistory) {
                createPurchaseHistoryPanel();
                try {
                    viewPurchaseHistoryAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerPurchaseHistoryPanel.back) {
                customerPurchaseHistoryPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == createCustomerPanel.exportPurchaseHistory) {
                createExportPurchaseHistoryPanel();
            }
            if (e.getSource() == customerExportPurchaseHistoryPanel.selectFileBtn) {
                customerExportPurchaseHistoryPanel.fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = customerExportPurchaseHistoryPanel.fc.showDialog(frame, "Select");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    customerExportPurchaseHistoryPanel.selectedFile =
                            customerExportPurchaseHistoryPanel.fc.getSelectedFile();
                    customerExportPurchaseHistoryPanel.filepathTxt.setText(
                            customerExportPurchaseHistoryPanel.selectedFile.getPath() + "\\" +
                                    user.getUsername() + ".csv");
                }
            }
            if (e.getSource() == customerExportPurchaseHistoryPanel.exportBtn) {
                exportPurchaseHistoryAction();
            }
            if (e.getSource() == customerExportPurchaseHistoryPanel.back) {
                customerExportPurchaseHistoryPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == createCustomerPanel.viewStatistics) {
                customerStatisticsProductReportPanel.setVisible(false);
                customerStatisticsStoreReportPanel.setVisible(false);
                createCustomerStatisticsPanel();
            }
            if (e.getSource() == createCustomerPanel.logout) {
                createCustomerPanel.setVisible(false);
                createLoginPanel();
            }
            if (e.getSource() == customerSearchForPrdPanel.search) {
                try {
                    searchPrdBtnAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerSearchForPrdPanel.back) {
                customerSearchForPrdPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == customerPurchaseHistoryPanel.back) {
                customerPurchaseHistoryPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == customerExportPurchaseHistoryPanel.back) {
                customerExportPurchaseHistoryPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == customerSearchForPrdPanel.back) {
                customerSearchForPrdPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == customerViewMarketPlaceSelectionPanel.back) {
                customerViewMarketPlaceSelectionPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == customerViewMarketPlaceSelectionPanel.purchase) {
                createCustomerPurchasePrdPanel();
            }
            if (e.getSource() == customerPurchasePrdPanel.cancel) {
                customerPurchasePrdPanel.setVisible(false);
                customerViewMarketPlaceSelectionPanel.setVisible(true);
            }
            if (e.getSource() == customerPurchasePrdPanel.purchase) {
                try {
                    purchaseBtnInPurchasePrdPanel();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerViewMarketPlaceSelectionPanel.sortByPrice) {
                try {
                    customerViewSortedByPriceMarketPlaceBtnAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerViewMarketPlaceSelectionPanel.sortByQuantity) {
                try {
                    customerViewSortedByQuantityMarketPlaceBtnAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerViewMarketPlaceSelectionPanel.refresh) {
                try {
                    customerViewMarketPlaceBtnAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerMainPanel.continueBtn) {
                // set store name to current selected store
                user.setStoreName(sellerMainPanel.storesDropdown.getSelectedItem().toString().trim());
                updatingStoreNameForServer();
                sellerMainPanel.setVisible(false);
                createSellerPanel();
            }
            if (e.getSource() == sellerMainPanel.logout) {
                sellerMainPanel.setVisible(false);
                createLoginPanel();
            }
            if (e.getSource() == homeBtn) {
                sellerPanel.setVisible(false);
                sellerMainPanel.setVisible(true);
            }
            if (e.getSource() == sellerCreateNewStore.back) {
                sellerCreateNewStore.setVisible(false);
                sellerMainPanel.setVisible(true);
            }
            if (e.getSource() == sellerMainPanel.createStore) {
                createSellerCreateNewStore();
            }
            if (e.getSource() == sellerCreateNewStore.create) {
                createBtnInCreateNewStorePanel();
            }
            if (e.getSource() == shoppingCartBtn) {
                createSellerShoppingCartPanel();
                try {
                    viewSellerShoppingCartAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerViewShoppingCartPanel.back) {
                sellerViewShoppingCartPanel.setVisible(false);
                sellerPanel.setVisible(true);
            }
            if (e.getSource() == createCustomerPanel.viewShoppingCart) {
                createCustomerShoppingCartPanel();
                try {
                    viewCustomerShoppingCartAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerViewShoppingCartPanel.cancelBtn) {
                customerViewShoppingCartPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == customerViewShoppingCartPanel.deleteBtn) {
                try {
                    deletePrdShoppingCartAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerStatisticsPanel.report1Btn) {
                createCustomerStatisticsStoreReportPanel();
                try {
                    customerStatisticsStoreReportAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerStatisticsPanel.report2Btn) {
                createCustomerStatisticsProductReportPanel();
                try {
                    customerStatisticsProductReportAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerStatisticsPanel.report1Btn) {
                createSellerStatisticsCustomerReportPanel();
                try {
                    sellerStatisticsCustomerReportAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerStatisticsPanel.report2Btn) {
                createSellerStatisticsProductReportPanel();
                try {
                    sellerStatisticsProductReportAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerStatisticsPanel.back) {
                sellerStatisticsPanel.setVisible(false);
                sellerPanel.setVisible(true);
            }
            if (e.getSource() == customerStatisticsPanel.back) {
                customerStatisticsPanel.setVisible(false);
                createCustomerPanel.setVisible(true);
            }
            if (e.getSource() == sellerStatisticsCustomerReportPanel.back) {
                sellerStatisticsCustomerReportPanel.setVisible(false);
                sellerStatisticsPanel.setVisible(true);
            }
            if (e.getSource() == sellerStatisticsProductReportPanel.back) {
                sellerStatisticsProductReportPanel.setVisible(false);
                sellerStatisticsPanel.setVisible(true);
            }
            if (e.getSource() == customerStatisticsProductReportPanel.back) {
                customerStatisticsProductReportPanel.setVisible(false);
                customerStatisticsPanel.setVisible(true);
            }
            if (e.getSource() == customerStatisticsStoreReportPanel.back) {
                customerStatisticsStoreReportPanel.setVisible(false);
                customerStatisticsPanel.setVisible(true);
            }
            if (e.getSource() == sellerStatisticsCustomerReportPanel.sort) {
                try {
                    sellerStatisticsSortCustomerReportAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerStatisticsProductReportPanel.sort) {
                try {
                    sellerStatisticsSortProductReportAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerStatisticsStoreReportPanel.sort) {
                try {
                    customerStatisticsSortStoreReportAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerViewShoppingCartPanel.purchaseBtn) {
                try {
                    purchaseCustomerShoppingCartAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == customerPurchasePrdPanel.addToShoppingCart) {
                try {
                    addShoppingCartAction();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == sellerMainPanel.editAcc) {
                createEditAccountSettingsPanel(sellerMainPanel);
            }
            if (e.getSource() == editAccountSettingsPanel.updateBtn) {
                editAcctSettingsUpdatePwd();
            }
            if (e.getSource() == editAccountSettingsPanel.backBtn) {
                editAccountSettingsPanel.setVisible(false);
                if (user.getRole().equalsIgnoreCase("seller")) {
                    sellerMainPanel.setVisible(true);
                } else {
                    createCustomerPanel.setVisible(true);
                }
            }
            if (e.getSource() == editAccountSettingsPanel.deleteBtn) {
                editAcctSettingsDeleteAccount();
            }
            if (e.getSource() == createCustomerPanel.editAcc) {
                createEditAccountSettingsPanel(createCustomerPanel);
            }
        }
    };

    ActionListener actionListenerEdit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sellerEditPrdSelPanel.editPrdInEditPrdSelPnlBtn) {
                editBtnInEditPrdSelPanelAction();
            }
            if (e.getSource() == sellerEditPrdSelPanel.sellerEditPrdSelPanelCancelBtn) {
                sellerEditPrdSelPanel.setVisible(false);
                sellerEditProductPanel.setVisible(false);
                sellerPanel.setVisible(true);
            }
            if (e.getSource() == sellerEditProductPanel.cancel) {
                sellerEditProductPanel.setVisible(false);
                sellerEditPrdSelPanel.setVisible(true);
            }
            if (e.getSource() == sellerEditProductPanel.edit) {
                try {
                    editBtnInEditProductPanel();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    };

    public static void main(String[] args) {
        try {
            //port number is 4242 and host name is local host
            Socket socket = new Socket("localhost", 4242);
            SwingUtilities.invokeLater(new Client(socket));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error. Could not connect to the Marketplace " +
                            "server.",
                    "Server Status", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            objReader = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // creating marketplace gui
        frame = new JFrame("MarketPlace");
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createLoginPanel();

        frame.setVisible(true);
    }

    // panel displayed when program is started
    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        frame.add(loginPanel);

        // creating welcome message label
        JLabel welcomeLbl = new JLabel("Welcome to MarketPlace Application!");
        welcomeLbl.setBounds(175, 90, 300, 25);
        loginPanel.add(welcomeLbl);

        // creating username label
        JLabel usernameLbl = new JLabel("Username");
        usernameLbl.setBounds(225, 120, 80, 25);
        loginPanel.add(usernameLbl);

        usernameTxt = new JTextField();
        usernameTxt.setBounds(325, 120, 165, 25);
        loginPanel.add(usernameTxt);

        // creating password label
        JLabel pwdLbl = new JLabel("Password");
        pwdLbl.setBounds(225, 150, 80, 25);
        loginPanel.add(pwdLbl);

        pwdText = new JPasswordField();
        pwdText.setBounds(325, 150, 165, 25);
        loginPanel.add(pwdText);

        // creating login button
        loginBtn = new JButton("Login");
        loginBtn.setBounds(225, 200, 80, 25);
        loginBtn.addActionListener(actionListener);
        loginPanel.add(loginBtn);

        // creating exit button
        exitBtn = new JButton("Exit");
        exitBtn.setBounds(325, 200, 80, 25);
        exitBtn.addActionListener(actionListener);
        loginPanel.add(exitBtn);

        // creating new account button
        createAccBtn = new JButton("Create Account");
        createAccBtn.setBounds(425, 200, 125, 25);
        createAccBtn.addActionListener(actionListener);
        loginPanel.add(createAccBtn);

        // creating and adding error text label
        errorText = new JLabel("");
        errorText.setBounds(225, 230, 300, 25);
        loginPanel.add(errorText);
    }

    // when user clicks the login button in the login panel
    private void loginButtonAction() {
        // sends the username and password to the server only if they are not blank
        if (!usernameTxt.getText().isEmpty() && !pwdText.getText().isEmpty()) {
            String msg = String.format("MsgType: login | %s, %s", usernameTxt.getText(),
                    pwdText.getText());
            // sending login info to server
            writer.write(msg);
            writer.println();
            writer.flush();
        } else {
            errorText.setText("Please enter username and password");
            return;
        }


        // waiting for server to validate user information
        String validatedUserInfo = "";
        try {
            validatedUserInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // user does not have an account or incorrect info entered
        if (validatedUserInfo.equalsIgnoreCase("error")) {
            errorText.setText("Invalid login credentials");
            return;
        }

        // user exists
        // determining the next panel user sees - customer or seller
        String role = "";
        if (validatedUserInfo.contains("exists")) {
            user.setUsername(usernameTxt.getText());
            role = validatedUserInfo.split("\\|:")[1].trim().split("\\|")[0].trim();
            user.setRole(role);
        }

        if (role.equalsIgnoreCase("seller")) {
            user.setStoresList(validatedUserInfo.split("\\|:")[1].trim().split("\\|")[1].trim());
            createSellerMainPanel();
        }

        if (role.equalsIgnoreCase("customer")) {
            createCustomerPanel();
        }

    }

    // panel displayed when user clicks create account button in login panel
    private void accountCreationPanel() {
        // removes the login panel
        loginPanel.setVisible(false);

        // replacing login panel with create account panel
        createAccPanel = new JPanel();
        createAccPanel.setLayout(null);
        frame.add(createAccPanel);

        // creating welcome message label
        JLabel welcomeLbl = new JLabel("Welcome to MarketPlace Application!");
        welcomeLbl.setBounds(175, 90, 300, 25);
        createAccPanel.add(welcomeLbl);

        // creating enter information message label
        JLabel enterInfoLbl = new JLabel("Fill the fields below to create a new account");
        enterInfoLbl.setBounds(175, 120, 300, 25);
        createAccPanel.add(enterInfoLbl);

        // creating username label
        JLabel newAccUserLbl = new JLabel("Username");
        newAccUserLbl.setBounds(225, 150, 80, 25);
        createAccPanel.add(newAccUserLbl);

        newAccUserTxt = new JTextField();
        newAccUserTxt.setBounds(325, 150, 165, 25);
        createAccPanel.add(newAccUserTxt);

        // creating password label
        JLabel newAccPwdLbl = new JLabel("Password");
        newAccPwdLbl.setBounds(225, 180, 80, 25);
        createAccPanel.add(newAccPwdLbl);

        newAccPwdText = new JPasswordField();
        newAccPwdText.setBounds(325, 180, 165, 25);
        createAccPanel.add(newAccPwdText);

        // adding role label
        JLabel role = new JLabel("Role");
        role.setBounds(225, 210, 80, 25);
        createAccPanel.add(role);

        // adding role dropdown
        String[] rolesArr = {"Customer", "Seller"};
        rolesDropdown = new JComboBox(rolesArr);
        rolesDropdown.setBounds(325, 210, 100, 25);
        rolesDropdown.addActionListener(actionListener);
        createAccPanel.add(rolesDropdown);

        // adding store label
        storeName = new JLabel("");
        storeName.setBounds(225, 240, 80, 25);
        createAccPanel.add(storeName);

        storeTxt = new JTextField();
        storeTxt.setBounds(325, 240, 165, 25);

        //create button to finalize creation
        createBtn = new JButton("Create");
        createBtn.setBounds(225, 300, 80, 25);
        createBtn.addActionListener(actionListener);
        createAccPanel.add(createBtn);

        //adding exit button to panel
        exitBtn.setBounds(325, 300, 80, 25);
        createAccPanel.add(exitBtn);

        // back to log in button
        backToLoginBtn = new JButton("Back to login");
        backToLoginBtn.setBounds(425, 300, 125, 25);
        backToLoginBtn.addActionListener(actionListener);
        createAccPanel.add(backToLoginBtn);

        // creating and adding success text label
        successText = new JLabel("");
        successText.setBounds(225, 330, 300, 25);
        createAccPanel.add(successText);

        createAccPanel.setVisible(true);
    }
    private void createBtnAction() {
        // sends the username, password, role and store name to server
        String msg = "";
        // username and password should not be blank
        if (!newAccUserTxt.getText().isEmpty() && !newAccPwdText.getText().isEmpty()) {
            if (Objects.equals(rolesDropdown.getSelectedItem(), "Customer")) {
                msg = String.format("MsgType: Create_Account | %s, %s, %s, nostore", newAccUserTxt.getText(),
                        newAccPwdText.getText(), rolesDropdown.getSelectedItem());
                // sending login info to server
                writer.write(msg);
                writer.println();
                writer.flush();
            }

            // seller leaves store field blank
            if (rolesDropdown.getSelectedItem().equals("Seller") && storeTxt.getText().isEmpty()) {
                successText.setText("Please enter store name");
                return;
            }

            // store field is not empty
            if (rolesDropdown.getSelectedItem().equals("Seller") && !storeTxt.getText().isEmpty()) {
                msg = String.format("MsgType: Create_Account | %s, %s, %s, %s", newAccUserTxt.getText(),
                        newAccPwdText.getText(), rolesDropdown.getSelectedItem(), storeTxt.getText());
                // sending login info to server
                writer.write(msg);
                writer.println();
                writer.flush();
            }
        } else { // username or password is blank
            successText.setText("Please enter username and password");
            return;
        }

        // waiting for server to give account creation status
        String accCreated = "";
        try {
            accCreated = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // user already has an account or other errors
        if (accCreated.equalsIgnoreCase("error")) {
            successText.setText("Error creating account. Please try again.");
        }

        if (accCreated.equalsIgnoreCase("success")) {
            successText.setText("Account created successfully!");
        }
    }

    private void createSellerPanel() {
        // removes the login panel
        loginPanel.setVisible(false);

        // replacing the login panel with the seller panel
        sellerPanel = new JPanel();
        sellerPanel.setLayout(null);
        frame.add(sellerPanel);

        // creating welcome message label
        JLabel welcomeLbl = new JLabel("Welcome to MarketPlace Application!");
        welcomeLbl.setBounds(125, 120, 300, 25);
        sellerPanel.add(welcomeLbl);

        // adding create products button
        createProductBtn = new JButton("Create a product");
        createProductBtn.setBounds(275, 200, 200, 25);
        createProductBtn.addActionListener(actionListener);
        sellerPanel.add(createProductBtn);

        // add edit product button
        editProductBtn = new JButton("Edit a product");
        editProductBtn.setBounds(525, 200, 200, 25);
        editProductBtn.addActionListener(actionListener);
        sellerPanel.add(editProductBtn);

        // creating delete product button
        deleteProductBtn = new JButton("Delete a product");
        deleteProductBtn.setBounds(775, 200, 200, 25);
        deleteProductBtn.addActionListener(actionListener);
        sellerPanel.add(deleteProductBtn);

        // creating import products button
        importProductsBtn = new JButton("Import products");
        importProductsBtn.setBounds(275, 300, 200, 25);
        importProductsBtn.addActionListener(actionListener);
        sellerPanel.add(importProductsBtn);

        // creating export products button
        exportProductsBtn = new JButton("Export products");
        exportProductsBtn.setBounds(525, 300, 200, 25);
        exportProductsBtn.addActionListener(actionListener);
        sellerPanel.add(exportProductsBtn);

        // creating view sales by store button
        salesByStoreBtn = new JButton("View sales for this store");
        salesByStoreBtn.setBounds(525, 400, 200, 25);
        salesByStoreBtn.addActionListener(actionListener);
        sellerPanel.add(salesByStoreBtn);

        // creating view statistics
        viewStatisticsBtn = new JButton("Statistics Dashboard");
        viewStatisticsBtn.setBounds(275, 400, 200, 25);
        viewStatisticsBtn.addActionListener(actionListener);
        sellerPanel.add(viewStatisticsBtn);

        // creating view shopping cart button
        shoppingCartBtn = new JButton("Shopping Cart");
        shoppingCartBtn.setBounds(775, 400, 200, 25);
        shoppingCartBtn.addActionListener(actionListener);
        sellerPanel.add(shoppingCartBtn);

        // creating log out button
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(1050, 30, 80, 25);
        logoutBtn.addActionListener(actionListener);
        sellerPanel.add(logoutBtn);

        // creating home button
        homeBtn = new JButton("Home");
        homeBtn.setBounds(940, 30, 80, 25);
        homeBtn.addActionListener(actionListener);
        sellerPanel.add(homeBtn);
    }

    private void createEditProductPanel() {
        sellerEditProductPanel.setVisible(false);
        sellerEditPrdSelPanel = new SellerEditPrdSelPanel(frame, sellerPanel, actionListenerEdit);
        sellerEditProductPanel.setVisible(true);
    }

    private void selectEditProductAction() throws IOException, ClassNotFoundException {
        String msg = String.format("MsgType: SELECT_PRODUCTS |");
        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();
        // wait for server to send list of products
        HashMap<String, Product> resultList = (HashMap<String, Product>) objReader.readObject();
        // update the table data with the data in resultList
        sellerEditPrdSelPanel.setTableData(resultList);
    }

    private void editBtnInEditPrdSelPanelAction() {
        if (sellerEditPrdSelPanel.tableData != null && !sellerEditPrdSelPanel.tableData.isEmpty()) {
            String flavorEntered = sellerEditPrdSelPanel.flavorToEditTxt.getText();
            // user enters a flavor that doesn't exist
            if (flavorEntered.isEmpty() || sellerEditPrdSelPanel.tableData.get(flavorEntered) == null) {
                JOptionPane.showMessageDialog(null, "Enter a valid flavor name",
                        "Edit Product", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Product prd = sellerEditPrdSelPanel.tableData.get(flavorEntered);
            sellerEditProductPanel = new SellerEditProductPanel(frame, sellerEditPrdSelPanel, actionListenerEdit, prd);

        } else {
            JOptionPane.showMessageDialog(null, "Error. No data to edit",
                    "Edit Product", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editBtnInEditProductPanel() throws IOException, ClassNotFoundException {
        // validating the data entered in fields
        // field is empty
        if (sellerEditProductPanel.newDesTxt.getText().isEmpty() ||
                sellerEditProductPanel.newQuantityTxt.getText().isEmpty() ||
                sellerEditProductPanel.newPriceTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter data for all fields",
                    "Edit Product", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Integer.parseInt(sellerEditProductPanel.newQuantityTxt.getText()) <= 0 ||
                Double.parseDouble(sellerEditProductPanel.newPriceTxt.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Please valid data for Price and Quantity",
                    "Edit Product", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product selectedPrd = sellerEditProductPanel.selectedProduct;
        selectedPrd.setDescription(sellerEditProductPanel.newDesTxt.getText());
        selectedPrd.setQuantity(Integer.parseInt(sellerEditProductPanel.newQuantityTxt.getText()));
        selectedPrd.setPrice(Double.parseDouble(sellerEditProductPanel.newPriceTxt.getText()));
        // creating message being sent to server
        String msg = String.format("MsgType: edit_product | %s, %s, %s, %s", selectedPrd.getFlavorName(),
                selectedPrd.getDescription(), selectedPrd.getQuantity(), selectedPrd.getPrice());
        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();
        // receive from server result
        String productEditedInfo = "";
        try {
            productEditedInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // if success display success message take user back to edit selection panel
        if (productEditedInfo.contains("success")) {
            JOptionPane.showMessageDialog(null, "Product edited successfully",
                    "Edit Product", JOptionPane.INFORMATION_MESSAGE);
            // if error display error message go back to edit selection panel
        } else {
            JOptionPane.showMessageDialog(null, productEditedInfo,
                    "Edit Product", JOptionPane.ERROR_MESSAGE);
        }
        sellerEditProductPanel.setVisible(false);
        selectEditProductAction();
        sellerEditPrdSelPanel.setVisible(true);
    }

    private void createDeleteProductPanel() {
        sellerDeletePrdSelPanel = new SellerDeletePrdSelPanel(frame, sellerPanel, actionListener);
        sellerDeletePrdSelPanel.setVisible(true);
    }

    private void selectDeleteProductAction() throws IOException, ClassNotFoundException {
        String msg = String.format("MsgType: SELECT_PRODUCTS |");
        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();
        // wait for server to send list of products
        HashMap<String, Product> resultList = (HashMap<String, Product>) objReader.readObject();
        // update the table data with the data in resultList
        sellerDeletePrdSelPanel.setTableData(resultList);
    }

    private void deleteBtnInDeletePanelAction() throws IOException, ClassNotFoundException {
        if (sellerDeletePrdSelPanel.tableData != null && !sellerDeletePrdSelPanel.tableData.isEmpty()) {
            String flavorEntered = sellerDeletePrdSelPanel.flavorToEditTxt.getText();
            // user enters a flavor that doesn't exist
            if (flavorEntered.isEmpty() || sellerDeletePrdSelPanel.tableData.get(flavorEntered) == null) {
                JOptionPane.showMessageDialog(null, "Enter a valid flavor name",
                        "Delete Product", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Product prd = sellerDeletePrdSelPanel.tableData.get(flavorEntered);

            // send product information to server for deletion
            // creating message being sent to server
            String msg = String.format("MsgType: delete_product | %s, %s, %s, %s", prd.getFlavorName(),
                    prd.getDescription(), prd.getQuantity(), prd.getPrice());
            // sending product info to server
            writer.write(msg);
            writer.println();
            writer.flush();
            // receive from server result
            String productDeletedInfo = "";
            try {
                productDeletedInfo = reader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // if success display success message take user back to delete selection panel
            if (productDeletedInfo.contains("success")) {
                JOptionPane.showMessageDialog(null, "Product deleted successfully",
                        "Delete Product", JOptionPane.INFORMATION_MESSAGE);
                // if error display error message go back to delete selection panel
            } else {
                JOptionPane.showMessageDialog(null, productDeletedInfo,
                        "Delete Product", JOptionPane.ERROR_MESSAGE);
            }
            selectDeleteProductAction();
        } else {
            JOptionPane.showMessageDialog(null, "Error. No data to delete",
                    "Delete Product", JOptionPane.ERROR_MESSAGE);
        }
    }

    // user clicks create product button on seller page
    private void createProductPanel() {
        sellerCreatePrdPanel = new SellerCreatePrdPanel(frame, sellerPanel, actionListener, user);
        sellerCreatePrdPanel.setVisible(true);
    }
    private void selectCreateProductAction() throws IOException, ClassNotFoundException {
        String msg = String.format("MsgType: SELECT_PRODUCTS |");
        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();
        // wait for server to send list of products
        HashMap<String, Product> resultList = (HashMap<String, Product>) objReader.readObject();
        // update the table data with the data in resultList
        sellerCreatePrdPanel.setTableData(resultList);
    }

    private void createBtnInCreatePrdPanelAction() throws IOException, ClassNotFoundException {
        // validating the data entered in fields
        // field is empty
        if (sellerCreatePrdPanel.newFlavorTxt.getText().isEmpty() || sellerCreatePrdPanel.newDesTxt.getText().isEmpty()
                || sellerCreatePrdPanel.newQuantityTxt.getText().isEmpty() ||
                sellerCreatePrdPanel.newPriceTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter data for all fields",
                    "Create Product", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Integer.parseInt(sellerCreatePrdPanel.newQuantityTxt.getText()) <= 0 ||
                Double.parseDouble(sellerCreatePrdPanel.newPriceTxt.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Please valid data for Price and Quantity",
                    "Create Product", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // setting the product details to the details entered if all the fields contain valid data
        sellerCreatePrdPanel.prd.setFlavorName(sellerCreatePrdPanel.newFlavorTxt.getText());
        sellerCreatePrdPanel.prd.setStore(user.getStoreName());
        sellerCreatePrdPanel.prd.setDescription(sellerCreatePrdPanel.newDesTxt.getText());
        sellerCreatePrdPanel.prd.setQuantity(Integer.parseInt(sellerCreatePrdPanel.newQuantityTxt.getText()));
        sellerCreatePrdPanel.prd.setPrice(Double.parseDouble(sellerCreatePrdPanel.newPriceTxt.getText()));

        // product being created
        Product prd = sellerCreatePrdPanel.prd;
        // send product information to server for creation
        // creating message being sent to server
        String msg = String.format("MsgType: create_product | %s, %s, %s, %s", prd.getFlavorName(),
                prd.getDescription(), prd.getQuantity(), prd.getPrice());
        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();
        // receive from server result
        String productCreatedInfo = "";
        try {
            productCreatedInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // if success display success message take user back to create selection panel
        if (productCreatedInfo.contains("success")) {
            JOptionPane.showMessageDialog(null, "Product created successfully",
                    "Create Product", JOptionPane.INFORMATION_MESSAGE);
            // if error display error message go back to create selection panel
        } else {
            JOptionPane.showMessageDialog(null, productCreatedInfo,
                    "Create Product", JOptionPane.ERROR_MESSAGE);
        }
        selectCreateProductAction();
    }

    // creating the import product panel page that the user sees
    private void createImportPrdPanel() {
        sellerImportPrdPanel = new SellerImportPrdPanel(frame, sellerPanel, actionListener, user);
        sellerImportPrdPanel.setVisible(true);
    }

    private void selectImportProductAction() throws IOException, ClassNotFoundException {
        String msg = String.format("MsgType: SELECT_PRODUCTS |");
        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();
        // wait for server to send list of products
        HashMap<String, Product> resultList = (HashMap<String, Product>) objReader.readObject();
        // update the table data with the data in resultList
        sellerImportPrdPanel.setTableData(resultList);
    }

    private void importProductsBtnAction() throws IOException, ClassNotFoundException {
        String msg = String.format("MsgType: import_product | %s", sellerImportPrdPanel.selectedFile.getPath());

        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String productsImportedInfo = "";
        try {
            productsImportedInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to import file selection panel
        if (productsImportedInfo.contains("success")) {
            JOptionPane.showMessageDialog(null, "File imported successfully",
                    "Import File", JOptionPane.INFORMATION_MESSAGE);
            // if error display error message go back to import file selection panel
        } else {
            JOptionPane.showMessageDialog(null, productsImportedInfo,
                    "Import File", JOptionPane.WARNING_MESSAGE);
        }
        selectImportProductAction();
    }

    // creating the export product panel page that the user sees
    private void createExportPrdPanel() {
        sellerExportPrdPanel = new SellerExportPrdPanel(frame, sellerPanel, actionListener, user);
        sellerExportPrdPanel.setVisible(true);
    }

    private void exportProductsBtnAction() throws IOException, ClassNotFoundException {
        String msg = String.format("MsgType: export_product | %s", sellerExportPrdPanel.selectedFile.getPath() + "\\" +
                user.getStoreName() + ".csv");

        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String productsImportedInfo = "";
        try {
            productsImportedInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to export file selection panel
        if (productsImportedInfo.contains("success")) {
            JOptionPane.showMessageDialog(null, "File exported successfully",
                    "Export File", JOptionPane.INFORMATION_MESSAGE);
            // if error display error message go back to export file selection panel
        } else {
            JOptionPane.showMessageDialog(null, productsImportedInfo,
                    "Export File", JOptionPane.ERROR_MESSAGE);
        }
    }

    // creating view sales panel
    private void createVIewSalesPanel() {
        sellerPanel.setVisible(false);
        sellerViewSalesByStore = new SellerViewSalesByStore(frame, sellerPanel, actionListener);
        sellerViewSalesByStore.setVisible(true);
    }

    private void salesByStoreBtnAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: VIEW_SALES_BY_STORE | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String viewSalesInfo = "";
        try {
            viewSalesInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to export file selection panel
        if (viewSalesInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Sales", JOptionPane.ERROR_MESSAGE);
            sellerViewSalesByStore.setVisible(false);
            sellerPanel.setVisible(true);
            return;
        }

        if (viewSalesInfo.contains("No sales yet")) {
            JOptionPane.showMessageDialog(null, "No sales yet",
                    "View Sales", JOptionPane.INFORMATION_MESSAGE);
            sellerViewSalesByStore.setVisible(false);
            sellerPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        ArrayList<String> resultList = (ArrayList<String>) objReader.readObject();
        // update the table data with the data in resultList
        sellerViewSalesByStore.setTableData(resultList);
    }

    private void createCustomerPanel() {
        createCustomerPanel = new CreateCustomerPanel(frame, loginPanel, actionListener);
        createCustomerPanel.setVisible(true);
    }
    private void createViewMarketPlacePanel() {
        customerViewMarketPlaceSelectionPanel = new CustomerViewMarketPlaceSelectionPanel(frame, createCustomerPanel,
                actionListener);
        customerViewMarketPlaceSelectionPanel.setVisible(true);
    }

    private void customerViewMarketPlaceBtnAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: VIEW_MARKETPLACE | ";

        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String result = "";
        try {
            result = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (result.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View MarketPlace", JOptionPane.ERROR_MESSAGE);
            customerViewMarketPlaceSelectionPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (result.contains("No products have been listed yet")) {
            JOptionPane.showMessageDialog(null, result,
                    "View MarketPlace", JOptionPane.INFORMATION_MESSAGE);
            customerViewMarketPlaceSelectionPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        HashMap<String, Product> resultList;
        if (result.equalsIgnoreCase("success")) {
            resultList = (HashMap<String, Product>) objReader.readObject();
            customerViewMarketPlaceSelectionPanel.setTableData(resultList);
        }
    }

    private void customerViewSortedByPriceMarketPlaceBtnAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: VIEW_SORTED_BY_PRICE_MARKETPLACE | ";

        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String result = "";
        try {
            result = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (result.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View MarketPlace", JOptionPane.ERROR_MESSAGE);
            customerViewMarketPlaceSelectionPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (result.contains("No products have been listed yet")) {
            JOptionPane.showMessageDialog(null, result,
                    "View MarketPlace", JOptionPane.INFORMATION_MESSAGE);
            customerViewMarketPlaceSelectionPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        ArrayList<Product> resultList;
        if (result.equalsIgnoreCase("success")) {
            resultList = (ArrayList<Product>) objReader.readObject();
            customerViewMarketPlaceSelectionPanel.setTableData(resultList);
        }
    }

    private void customerViewSortedByQuantityMarketPlaceBtnAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: VIEW_SORTED_BY_QUANTITY_MARKETPLACE | ";

        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String result = "";
        try {
            result = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (result.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View MarketPlace", JOptionPane.ERROR_MESSAGE);
            customerViewMarketPlaceSelectionPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (result.contains("No products have been listed yet")) {
            JOptionPane.showMessageDialog(null, result,
                    "View MarketPlace", JOptionPane.INFORMATION_MESSAGE);
            customerViewMarketPlaceSelectionPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        ArrayList<Product> resultList;
        if (result.equalsIgnoreCase("success")) {
            resultList = (ArrayList<Product>) objReader.readObject();
            customerViewMarketPlaceSelectionPanel.setTableData(resultList);
        }
    }

    private void createCustomerPurchasePrdPanel() {
        if (customerViewMarketPlaceSelectionPanel.tableData != null &&
                !customerViewMarketPlaceSelectionPanel.tableData.isEmpty()) {
            String flavorEntered = customerViewMarketPlaceSelectionPanel.flavorToSelectTxt.getText();
            String storeNameText = customerViewMarketPlaceSelectionPanel.storeSelectedTxt.getText();
            // user enters a flavor that doesn't exist
            if (flavorEntered.isEmpty() || storeNameText.isEmpty() ||
                    customerViewMarketPlaceSelectionPanel.tableData.get(flavorEntered + storeNameText) == null) {
                JOptionPane.showMessageDialog(null, "Enter a valid flavor and store name",
                        "Purchase Product", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Product prd = customerViewMarketPlaceSelectionPanel.tableData.get(flavorEntered + storeNameText);
            customerPurchasePrdPanel = new CustomerPurchasePrdPanel(frame, customerViewMarketPlaceSelectionPanel,
                    actionListener, prd);
        } else {
            JOptionPane.showMessageDialog(null, "Error. No products to purchase",
                    "Purchase Product", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void purchaseBtnInPurchasePrdPanel() throws IOException, ClassNotFoundException {
        // validating the data entered in fields
        // field is empty
        if (customerPurchasePrdPanel.quantityPurchasedTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a quantity",
                    "Purchase Product", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Integer.parseInt(customerPurchasePrdPanel.quantityPurchasedTxt.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Please a valid Quantity",
                    "Purchase Product", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product selectedPrd = customerPurchasePrdPanel.selectedProduct;

        // creating message being sent to server
        String msg = String.format("MsgType: PURCHASE_PRODUCT | %s, %s, %s", selectedPrd.getFlavorName(),
                selectedPrd.getStore(), Integer.parseInt(customerPurchasePrdPanel.quantityPurchasedTxt.getText()));
        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();
        // receive from server result
        String productPurchasedInfo = "";
        try {
            productPurchasedInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // if success display success message take user back to edit selection panel
        if (productPurchasedInfo.contains("success")) {
            JOptionPane.showMessageDialog(null, "Product purchased successfully",
                    "Purchase Product", JOptionPane.INFORMATION_MESSAGE);
            customerPurchasePrdPanel.setVisible(false);
            customerViewMarketPlaceBtnAction();
            customerViewMarketPlaceSelectionPanel.setVisible(true);
            // if error display error message go back to edit selection panel
        } else if (productPurchasedInfo.contains("Sorry")) {
            JOptionPane.showMessageDialog(null, productPurchasedInfo,
                    "Purchase Product", JOptionPane.WARNING_MESSAGE);
            customerPurchasePrdPanel.setVisible(false);
            customerViewMarketPlaceBtnAction();
            customerViewMarketPlaceSelectionPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, productPurchasedInfo,
                    "Purchase Product", JOptionPane.ERROR_MESSAGE);
        }
        customerViewMarketPlaceBtnAction();
    }

    private void searchForProductsAction() {
        customerSearchForPrdPanel = new CustomerSearchForPrdPanel(frame, createCustomerPanel, actionListener, user);
        customerSearchForPrdPanel.setVisible(true);
    }

    private void searchPrdBtnAction() throws IOException, ClassNotFoundException {
        if (customerSearchForPrdPanel.searchStringTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a search term",
                    "Search for products", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String msg = String.format("MsgType: SEARCH_FOR_PRODUCT | %s",
                customerSearchForPrdPanel.searchStringTxt.getText());

        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String searchResultsInfo = "";
        try {
            searchResultsInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to import file selection panel
        if (searchResultsInfo.contains("Your search did not match any products")) {
            customerSearchForPrdPanel.setTableData(new HashMap<>());
            JOptionPane.showMessageDialog(null, searchResultsInfo,
                    "Search for products", JOptionPane.WARNING_MESSAGE);
            return;
            // if error display error message go back to customer panel
        } else if (searchResultsInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "Search for products", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // wait for server to send list of products
        HashMap<String, Product> resultList = (HashMap<String, Product>) objReader.readObject();
        // update the table data with the data in resultList
        customerSearchForPrdPanel.setTableData(resultList);
    }

    private void createPurchaseHistoryPanel() {
        customerPurchaseHistoryPanel = new CustomerPurchaseHistoryPanel(frame, createCustomerPanel, actionListener);
        customerPurchaseHistoryPanel.setVisible(true);
    }
    private void viewPurchaseHistoryAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: VIEW_PURCHASE_HISTORY | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String purchaseHistoryInfo = "";
        try {
            purchaseHistoryInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (purchaseHistoryInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Sales", JOptionPane.ERROR_MESSAGE);
            customerPurchaseHistoryPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (purchaseHistoryInfo.contains("No products have been purchased yet")) {
            JOptionPane.showMessageDialog(null, "No products have been purchased yet",
                    "View Sales", JOptionPane.INFORMATION_MESSAGE);
            customerPurchaseHistoryPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        ArrayList<String> resultList = (ArrayList<String>) objReader.readObject();
        // update the table data with the data in resultList
        customerPurchaseHistoryPanel.setTableData(resultList);
    }
    private void createExportPurchaseHistoryPanel() {
        customerExportPurchaseHistoryPanel = new CustomerExportPurchaseHistoryPanel(frame, createCustomerPanel,
                actionListener);
        customerExportPurchaseHistoryPanel.setVisible(true);
    }
    private void exportPurchaseHistoryAction() {
        String msg = String.format("MsgType: EXPORT_PURCHASE_HISTORY | %s",
                customerExportPurchaseHistoryPanel.filepathTxt.getText());

        // customerExportPurchaseHistoryPanel.
        //                selectedFile.getPath() + "\\" + user.getUsername() + ".csv"

        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String exportInfo = "";
        try {
            exportInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (exportInfo.contains("success")) {
            JOptionPane.showMessageDialog(null, "File exported successfully",
                    "Export File", JOptionPane.INFORMATION_MESSAGE);
            // if error display error message go back to customer panel
        } else {
            JOptionPane.showMessageDialog(null, exportInfo,
                    "Export File", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createSellerMainPanel() {
        sellerMainPanel = new SellerMainPanel(frame, loginPanel, actionListener, user);
        sellerMainPanel.setVisible(true);
    }

    private void createSellerCreateNewStore() {
        sellerCreateNewStore = new SellerCreateNewStore(frame, sellerMainPanel, actionListener);
        sellerCreateNewStore.setVisible(true);
    }

    private void createBtnInCreateNewStorePanel() {
        // sends the username and password to the server only if they are not blank
        if (!sellerCreateNewStore.storeNameTxt.getText().isEmpty()) {
            String[] storeNames = user.getStoresList().split(",");
            for (int i = 0; i < storeNames.length; i++) {
                if (storeNames[i].equalsIgnoreCase(sellerCreateNewStore.storeNameTxt.getText())) {
                    JOptionPane.showMessageDialog(null, "Store already exists",
                            "Create Store", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String msg = String.format("MsgType: SELLER_CREATE_STORE | %s",
                    sellerCreateNewStore.storeNameTxt.getText());
            // sending message to server
            writer.write(msg);
            writer.println();
            writer.flush();
        } else {
            JOptionPane.showMessageDialog(null, "Enter a store name",
                    "Create Store", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // waiting for server to validate user information
        String storeCreatedInfo = "";
        try {
            storeCreatedInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (storeCreatedInfo.contains("success")) {
            sellerCreateNewStore.storeNameTxt.setText("");
            user.setStoresList(storeCreatedInfo.split("\\|")[1]);
            sellerMainPanel.storesDropdown.setModel(new DefaultComboBoxModel(user.getStoresList().split(",")));
            JOptionPane.showMessageDialog(null, "Store created!",
                    "Create Store", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error",
                    "Create Store", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createSellerShoppingCartPanel() {
        sellerViewShoppingCartPanel = new SellerViewShoppingCartPanel(frame, sellerPanel, actionListener);
        sellerViewShoppingCartPanel.setVisible(true);
    }
    private void viewSellerShoppingCartAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: SELLER_VIEW_SHOPPING_CART | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String shoppingCartInfo = "";
        try {
            shoppingCartInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (shoppingCartInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Shopping Cart", JOptionPane.ERROR_MESSAGE);
            sellerViewShoppingCartPanel.setVisible(false);
            sellerPanel.setVisible(true);
            return;
        }

        if (shoppingCartInfo.contains("No products have been added to any shopping cart yet")) {
            JOptionPane.showMessageDialog(null, "No products have been added yet",
                    "View Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
            sellerViewShoppingCartPanel.setVisible(false);
            sellerPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        ArrayList<String> resultList = (ArrayList<String>) objReader.readObject();
        // update the table data with the data in resultList
        sellerViewShoppingCartPanel.setTableData(resultList);
    }
    private void createCustomerShoppingCartPanel() {
        customerViewShoppingCartPanel = new CustomerViewShoppingCartPanel(frame, createCustomerPanel, actionListener);
        customerViewShoppingCartPanel.setVisible(true);
    }
    private void viewCustomerShoppingCartAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: VIEW_SHOPPING_CART | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String shoppingCartInfo = "";
        try {
            shoppingCartInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (shoppingCartInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Shopping Cart", JOptionPane.ERROR_MESSAGE);
            customerViewShoppingCartPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (shoppingCartInfo.contains("No products have been added to shopping cart yet")) {
            JOptionPane.showMessageDialog(null, "No products have been added yet",
                    "View Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
            customerViewShoppingCartPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        ArrayList<String> resultList = (ArrayList<String>) objReader.readObject();
        // update the table data with the data in resultList
        customerViewShoppingCartPanel.setTableData(resultList);
    }

    private void deletePrdShoppingCartAction() throws IOException, ClassNotFoundException {
        boolean flavorFound = false;
        String currentStore = "";
        String storeEntered = "";
        if (customerViewShoppingCartPanel.tableData != null && !customerViewShoppingCartPanel.tableData.isEmpty()) {
            String flavorEntered = customerViewShoppingCartPanel.flavorToEditTxt.getText();
            storeEntered = customerViewShoppingCartPanel.storeSelectedTxt.getText();
            for (String item : customerViewShoppingCartPanel.tableData) {
                String currentFlavor = item.split(",")[4];
                currentStore = item.split(",")[3];
                if (currentFlavor.equalsIgnoreCase(flavorEntered) && currentStore.equalsIgnoreCase(storeEntered)) {
                    flavorFound = true;
                    break;
                }
            }
            // user enters a flavor that doesn't exist
            if (flavorEntered.isEmpty() || !flavorFound) {
                JOptionPane.showMessageDialog(null, "Enter a valid flavor name and store name",
                        "Delete Product from Shopping Cart", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // send product information to server for deletion
            // creating message being sent to server
            String msg = String.format("MsgType: DELETE_FROM_SHOPPING_CART | %s, %s", flavorEntered, currentStore);
            // sending product info to server
            writer.write(msg);
            writer.println();
            writer.flush();
            // receive from server result
            String productDeletedInfo = "";
            try {
                productDeletedInfo = reader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // if success display success message take user back to delete selection panel
            if (productDeletedInfo.contains("success")) {
                JOptionPane.showMessageDialog(null, "Product removed from shopping cart " +
                                "successfully",
                        "Delete Product", JOptionPane.INFORMATION_MESSAGE);
                // if error display error message go back to delete selection panel
            } else {
                JOptionPane.showMessageDialog(null, productDeletedInfo,
                        "Delete Product", JOptionPane.ERROR_MESSAGE);
            }
            updateShoppingCartAction();
        } else {
            JOptionPane.showMessageDialog(null, "Error. No data to delete",
                    "Delete Product", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateShoppingCartAction() throws IOException, ClassNotFoundException {
        String msg = String.format("MsgType: VIEW_SHOPPING_CART |");
        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String shoppingCartInfo = "";
        try {
            shoppingCartInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (shoppingCartInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Shopping Cart", JOptionPane.ERROR_MESSAGE);
            customerViewShoppingCartPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (shoppingCartInfo.contains("No products have been added to any shopping cart yet")) {
            JOptionPane.showMessageDialog(null, "No products have been added yet",
                    "View Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
            customerViewShoppingCartPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }
        // wait for server to send list of products
        ArrayList<String> resultList = (ArrayList<String>) objReader.readObject();
        // update the table data with the data in resultList
        customerViewShoppingCartPanel.setTableData(resultList);
    }

    private void addShoppingCartAction() throws IOException, ClassNotFoundException {
        // validating the data entered in fields
        // field is empty
        if (customerPurchasePrdPanel.quantityPurchasedTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a quantity",
                    "Add Product", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Integer.parseInt(customerPurchasePrdPanel.quantityPurchasedTxt.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Quantity",
                    "Add Product", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product selectedPrd = customerPurchasePrdPanel.selectedProduct;
        //selectedPrd.setQuantity(Integer.parseInt(customerPurchasePrdPanel.quantityPurchasedTxt.getText()));

        // creating message being sent to server
        String msg = String.format("MsgType: ADD_TO_SHOPPING_CART | %s, %s, %s", selectedPrd.getFlavorName(),
                selectedPrd.getStore(), Integer.parseInt(customerPurchasePrdPanel.quantityPurchasedTxt.getText()));

        // sending product info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String shoppingCartInfo = "";
        try {
            shoppingCartInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        // Sorry! Product out of Stock
        if (shoppingCartInfo.contains("Sorry! Product out of Stock")) {
            JOptionPane.showMessageDialog(null, "Sorry! Product out of Stock",
                    "Add to Shopping Cart", JOptionPane.ERROR_MESSAGE);
            customerPurchasePrdPanel.setVisible(false);
            customerViewMarketPlaceSelectionPanel.setVisible(true);
            return;
        }
        if (shoppingCartInfo.contains("Error. Quantity you are trying to add to shopping cart exceeds quantity in " +
                "stock")) {
            JOptionPane.showMessageDialog(null, "Error. Quantity you are trying to add to " +
                            "shopping cart exceeds quantity in stock",
                    "Add to Shopping Cart", JOptionPane.ERROR_MESSAGE);
            customerPurchasePrdPanel.setVisible(false);
            customerViewMarketPlaceSelectionPanel.setVisible(true);
            return;
        }
        if (shoppingCartInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "Add to Shopping Cart", JOptionPane.ERROR_MESSAGE);
            customerPurchasePrdPanel.setVisible(false);
            customerViewMarketPlaceSelectionPanel.setVisible(true);
            return;
        }


        if (shoppingCartInfo.contains("No products have been added to any shopping cart yet")) {
            JOptionPane.showMessageDialog(null, "No products have been added yet",
                    "View Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
            customerPurchasePrdPanel.setVisible(false);
            customerViewMarketPlaceSelectionPanel.setVisible(true);
            return;
        }
        JOptionPane.showMessageDialog(null, "Product added to shopping cart",
                "View Shopping Cart", JOptionPane.INFORMATION_MESSAGE);

        customerPurchasePrdPanel.setVisible(false);
        customerViewMarketPlaceSelectionPanel.setVisible(true);
    }
    private void purchaseCustomerShoppingCartAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: PURCHASE_SHOPPING_CART | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String shoppingCartInfo = "";
        try {
            shoppingCartInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (shoppingCartInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "Purchase Shopping Cart", JOptionPane.ERROR_MESSAGE);
            customerViewShoppingCartPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (shoppingCartInfo.contains("Product no longer sold")) {
            JOptionPane.showMessageDialog(null, shoppingCartInfo,
                    "Purchase Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
            customerViewShoppingCartPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (shoppingCartInfo.contains("Product out of Stock")) {
            JOptionPane.showMessageDialog(null, shoppingCartInfo,
                    "Purchase Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
            customerViewShoppingCartPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        if (shoppingCartInfo.contains("Quantity you are trying to purchase exceeds quantity in stock")) {
            JOptionPane.showMessageDialog(null, shoppingCartInfo,
                    "Purchase Shopping Cart", JOptionPane.ERROR_MESSAGE);
            customerViewShoppingCartPanel.setVisible(false);
            createCustomerPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        ArrayList<String> resultList = (ArrayList<String>) objReader.readObject();
        // update the table data with the data in resultList
        customerViewShoppingCartPanel.setTableData(resultList);
        JOptionPane.showMessageDialog(null, "Products purchased successfully! Shopping Cart " +
                        "emptied",
                "Purchase Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
        customerViewShoppingCartPanel.setVisible(false);
        createCustomerPanel.setVisible(true);
    }
    private void createSellerStatisticsPanel() {
        sellerStatisticsPanel = new SellerStatisticsPanel(frame, sellerPanel, actionListener);
        sellerStatisticsPanel.setVisible(true);
    }

    private void createSellerStatisticsCustomerReportPanel() {
        sellerStatisticsCustomerReportPanel = new SellerStatisticsCustomerReportPanel(frame, sellerStatisticsPanel,
                actionListener);
        sellerStatisticsCustomerReportPanel.setVisible(true);
    }

    private void sellerStatisticsCustomerReportAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: SELLER_STATISTICS_CUSTOMER_LIST | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String reportInfo = "";
        try {
            reportInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (reportInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Customer Report", JOptionPane.ERROR_MESSAGE);
            sellerStatisticsCustomerReportPanel.setVisible(false);
            sellerStatisticsPanel.setVisible(true);
            return;
        }

        if (reportInfo.contains("No sales yet")) {
            JOptionPane.showMessageDialog(null, reportInfo,
                    "View Customer Report", JOptionPane.INFORMATION_MESSAGE);
            sellerStatisticsCustomerReportPanel.setVisible(false);
            sellerStatisticsPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        HashMap<String, Integer> resultList = (HashMap<String, Integer>) objReader.readObject();
        // update the table data with the data in resultList
        sellerStatisticsCustomerReportPanel.setTableData(resultList);
    }

    private void createSellerStatisticsProductReportPanel() {
        sellerStatisticsProductReportPanel = new SellerStatisticsProductReportPanel(frame, sellerStatisticsPanel,
                actionListener);
        sellerStatisticsProductReportPanel.setVisible(true);
    }

    private void sellerStatisticsProductReportAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: SELLER_STATISTICS_PRODUCT_LIST | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String reportInfo = "";
        try {
            reportInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (reportInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Product Report", JOptionPane.ERROR_MESSAGE);
            sellerStatisticsProductReportPanel.setVisible(false);
            sellerStatisticsPanel.setVisible(true);
            return;
        }

        if (reportInfo.contains("No sales yet")) {
            JOptionPane.showMessageDialog(null, reportInfo,
                    "View Product Report", JOptionPane.INFORMATION_MESSAGE);
            sellerStatisticsProductReportPanel.setVisible(false);
            sellerStatisticsPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        HashMap<String, Integer> resultList = (HashMap<String, Integer>) objReader.readObject();
        // update the table data with the data in resultList
        sellerStatisticsProductReportPanel.setTableData(resultList);
    }
    private void createCustomerStatisticsPanel() {
        customerStatisticsPanel = new CustomerStatisticsPanel(frame, createCustomerPanel, actionListener);
        customerStatisticsPanel.setVisible(true);
    }

    private void createCustomerStatisticsStoreReportPanel() {
        customerStatisticsStoreReportPanel = new CustomerStatisticsStoreReportPanel(frame, customerStatisticsPanel,
                actionListener);
        customerStatisticsStoreReportPanel.setVisible(true);
    }

    private void customerStatisticsStoreReportAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: CUSTOMER_STATISTICS_STORE_BY_NUMBER_OF_PRODUCTS | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String reportInfo = "";
        try {
            reportInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (reportInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Store Report", JOptionPane.ERROR_MESSAGE);
            customerStatisticsStoreReportPanel.setVisible(false);
            customerStatisticsPanel.setVisible(true);
            return;
        }

        if (reportInfo.contains("No purchases have been made yet")) {
            JOptionPane.showMessageDialog(null, reportInfo,
                    "View Store Report", JOptionPane.INFORMATION_MESSAGE);
            customerStatisticsStoreReportPanel.setVisible(false);
            customerStatisticsPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        HashMap<String, Integer> resultList = (HashMap<String, Integer>) objReader.readObject();
        // update the table data with the data in resultList
        customerStatisticsStoreReportPanel.setTableData(resultList);
    }

    private void createCustomerStatisticsProductReportPanel() {
        customerStatisticsProductReportPanel = new CustomerStatisticsProductReportPanel(frame, customerStatisticsPanel,
                actionListener);
        customerStatisticsProductReportPanel.setVisible(true);
    }

    private void customerStatisticsProductReportAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: CUSTOMER_STATISTICS_STORE_BY_PRODUCT | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String reportInfo = "";
        try {
            reportInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (reportInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Store Report", JOptionPane.ERROR_MESSAGE);
            customerStatisticsProductReportPanel.setVisible(false);
            customerStatisticsPanel.setVisible(true);
            return;
        }

        if (reportInfo.contains("No purchases have been made yet")) {
            JOptionPane.showMessageDialog(null, reportInfo,
                    "View Store Report", JOptionPane.INFORMATION_MESSAGE);
            customerStatisticsProductReportPanel.setVisible(false);
            customerStatisticsPanel.setVisible(true);
            return;
        }
        // wait for server to send list of products
        HashMap<String, Integer> resultList = (HashMap<String, Integer>) objReader.readObject();
        // update the table data with the data in resultList
        customerStatisticsProductReportPanel.setTableData(resultList);
    }
    private void sellerStatisticsSortCustomerReportAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: SELLER_STATISTICS_SORT_CUSTOMER_LIST | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String reportInfo = "";
        try {
            reportInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (reportInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Customer Report", JOptionPane.ERROR_MESSAGE);
            sellerStatisticsCustomerReportPanel.setVisible(false);
            sellerStatisticsPanel.setVisible(true);
            return;
        }

        if (reportInfo.contains("No sales yet")) {
            JOptionPane.showMessageDialog(null, reportInfo,
                    "View Customer Report", JOptionPane.INFORMATION_MESSAGE);
            sellerStatisticsCustomerReportPanel.setVisible(false);
            sellerStatisticsPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        ArrayList<SellerSortCustomer> resultList = (ArrayList<SellerSortCustomer>) objReader.readObject();
        // update the table data with the data in resultList
        sellerStatisticsCustomerReportPanel.setTableData(resultList);

    }

    private void sellerStatisticsSortProductReportAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: SELLER_STATISTICS_SORT_PRODUCT_LIST | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String reportInfo = "";
        try {
            reportInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (reportInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Product Report", JOptionPane.ERROR_MESSAGE);
            sellerStatisticsProductReportPanel.setVisible(false);
            sellerStatisticsPanel.setVisible(true);
            return;
        }

        if (reportInfo.contains("No sales yet")) {
            JOptionPane.showMessageDialog(null, reportInfo,
                    "View Product Report", JOptionPane.INFORMATION_MESSAGE);
            sellerStatisticsProductReportPanel.setVisible(false);
            sellerStatisticsPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        ArrayList<SellerSortProduct> resultList = (ArrayList<SellerSortProduct>) objReader.readObject();
        // update the table data with the data in resultList
        sellerStatisticsProductReportPanel.setTableData(resultList);

    }

    private void customerStatisticsSortStoreReportAction() throws IOException, ClassNotFoundException {
        String msg = "MsgType: CUSTOMER_STATISTICS_SORT_STORE_BY_NUMBER_OF_PRODUCTS | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String reportInfo = "";
        try {
            reportInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (reportInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error",
                    "View Store Report", JOptionPane.ERROR_MESSAGE);
            customerStatisticsStoreReportPanel.setVisible(false);
            customerStatisticsPanel.setVisible(true);
            return;
        }

        if (reportInfo.contains("No purchases have been made yet")) {
            JOptionPane.showMessageDialog(null, reportInfo,
                    "View Store Report", JOptionPane.INFORMATION_MESSAGE);
            customerStatisticsStoreReportPanel.setVisible(false);
            customerStatisticsPanel.setVisible(true);
            return;
        }

        // wait for server to send list of products
        ArrayList<CustomerSortStore> resultList = (ArrayList<CustomerSortStore>) objReader.readObject();
        // update the table data with the data in resultList
        customerStatisticsStoreReportPanel.setTableData(resultList);
    }
    private void updatingStoreNameForServer() {
        String msg = "MsgType: UPDATE_STORE_NAME_FOR_SERVER | " + user.getStoreName();
        writer.write(msg);
        writer.println();
        writer.flush();
    }
    private void createEditAccountSettingsPanel(JPanel parentPanel) {
        // This is created both for seller and customer accounts
        // Must call with the correct parent panel
        editAccountSettingsPanel.setVisible(false);
        editAccountSettingsPanel = new EditAccountSettingsPanel(frame, parentPanel, actionListener, user);
        editAccountSettingsPanel.setVisible(true);
    }
    private void editAcctSettingsUpdatePwd() {
        String pwdEntered = editAccountSettingsPanel.updatePwdTxt.getText();
        // user enters a flavor that doesn't exist
        if (pwdEntered.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a valid password",
                    "Edit Account Settings", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String msg = "MsgType: UPDATE_PASSWORD | " + pwdEntered;

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String resultInfo = "";
        try {
            resultInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (resultInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error updating password",
                    "Edit Account Settings", JOptionPane.ERROR_MESSAGE);
            editAccountSettingsPanel.setVisible(false);
            if (user.getRole().equalsIgnoreCase("seller")) {
                sellerMainPanel.setVisible(true);
            } else {
                createCustomerPanel.setVisible(true);
            }
            return;
        }
        JOptionPane.showMessageDialog(null, "Password updated successfully",
                "Edit Account Settings", JOptionPane.INFORMATION_MESSAGE);
        editAccountSettingsPanel.setVisible(false);
        if (user.getRole().equalsIgnoreCase("seller")) {
            sellerMainPanel.setVisible(true);
        } else {
            createCustomerPanel.setVisible(true);
        }
    }
    private  void editAcctSettingsDeleteAccount() {
        String msg = "MsgType: DELETE_ACCOUNT | ";

        // sending message type info to server
        writer.write(msg);
        writer.println();
        writer.flush();

        // waiting for server to validate user information
        String resultInfo = "";
        try {
            resultInfo = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // if success display success message take user back to customer panel
        if (resultInfo.contains("error")) {
            JOptionPane.showMessageDialog(null, "Error deleting account",
                    "Edit Account Settings", JOptionPane.ERROR_MESSAGE);
            editAccountSettingsPanel.setVisible(false);
            if (user.getRole().equalsIgnoreCase("seller")) {
                sellerMainPanel.setVisible(true);
            } else {
                createCustomerPanel.setVisible(true);
            }
            return;
        }
        JOptionPane.showMessageDialog(null, "Account deleted successfully",
                "Edit Account Settings", JOptionPane.INFORMATION_MESSAGE);
        editAccountSettingsPanel.setVisible(false);
        // Take user back to login screen
        createLoginPanel();
    }
}
