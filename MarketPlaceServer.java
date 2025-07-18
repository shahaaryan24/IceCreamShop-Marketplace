import javax.swing.plaf.ColorUIResource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project05 -- MarketPlaceServer
 *
 * This class implements the entire functionality
 * of the program on the server side. The MarketPlace component serves
 * as the operational hub, managing transactions, product listings
 * and user interactions within our program.
 *
 *   
 *
 * @version November 22, 2023
 */
public class MarketPlaceServer extends Thread {
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    ObjectOutputStream objWriter;

    public MarketPlaceServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            objWriter = new ObjectOutputStream(socket.getOutputStream());
            CurrentUser user = new CurrentUser();

            while (true) {
                try {
                    // receives the login info from the client
                    String incomingMsg = reader.readLine();
                    String msgType = "";

                    // parsing the message
                    // "MsgType: login | %s, %s" for login
                    msgType = incomingMsg.split("\\|")[0].split(":")[1].trim();

                    switch (msgType.toUpperCase()) {
                        case "LOGIN":
                            handleLogin(incomingMsg, user);
                            break;
                        case "CREATE_ACCOUNT":
                            handleCreateAccount(incomingMsg);
                            break;
                        case "UPDATE_PASSWORD":
                            handleUpdatePassword(incomingMsg, user);
                            break;
                        case "DELETE_ACCOUNT":
                            handleDeleteAccount(user.getUsername());
                            break;
                            // seller menu
                        case "SELLER_CREATE_STORE":
                            handleCreateStore(incomingMsg, user);
                            break;
                        case "IMPORT_PRODUCT":
                            handleImportProduct(incomingMsg, user);
                            break;
                        case "EXPORT_PRODUCT":
                            handleExportProduct(incomingMsg, user);
                            break;
                        case "CREATE_PRODUCT":
                            handleCreateProduct(incomingMsg, user);
                            break;
                        case "SELECT_PRODUCTS":
                            handleSelectProduct(user);
                            break;
                        case "EDIT_PRODUCT":
                            handleEditProduct(incomingMsg, user);
                            break;
                        case "DELETE_PRODUCT":
                            handleDeleteProduct(incomingMsg, user);
                            break;
                        case "VIEW_SALES_BY_STORE":
                            handleViewSalesForStore(user);
                            break;
                        case "SELLER_STATISTICS_CUSTOMER_LIST":
                            handleSellerStatisticsCustomerList(user);
                            break;
                        case "SELLER_STATISTICS_SORT_CUSTOMER_LIST":
                            handleSellerStatisticsSortCustomerList(user);
                            break;
                        case "SELLER_STATISTICS_PRODUCT_LIST":
                            handleSellerStatisticsProductList(user);
                            break;
                        case "SELLER_STATISTICS_SORT_PRODUCT_LIST":
                            handleSellerStatisticsSortProductList(user);
                            break;
                        // customer page
                        case "VIEW_MARKETPLACE":
                            handleViewMarketPlace();
                            break;
                        case "VIEW_SORTED_BY_PRICE_MARKETPLACE":
                            handleViewSortedByPriceMarketPlace();
                            break;
                        case "VIEW_SORTED_BY_QUANTITY_MARKETPLACE":
                            handleViewSortedByQuantityMarketPlace();
                            break;
                        case "PURCHASE_PRODUCT":
                            handlePurchaseProduct(incomingMsg, user);
                            break;
                        case "SEARCH_FOR_PRODUCT":
                            handleSearchForProduct(incomingMsg);
                            break;
                        case "VIEW_PURCHASE_HISTORY":
                            handleViewPurchaseHistory(user);
                            break;
                        case "EXPORT_PURCHASE_HISTORY":
                            handleExportPurchaseHistory(incomingMsg, user);
                            break;
                        case "CUSTOMER_STATISTICS_STORE_BY_PRODUCT":
                            handleCustomerStatisticsStoreByProduct(user);
                            break;
                        case "CUSTOMER_STATISTICS_STORE_BY_NUMBER_OF_PRODUCTS":
                            handleCustomerStatisticsStoreByNumberOfProducts(user);
                            break;
                        case "CUSTOMER_STATISTICS_SORT_STORE_BY_NUMBER_OF_PRODUCTS":
                            handleCustomerStatisticsSortStoreByNumberOfProducts(user);
                            break;
                        case "UPDATE_STORE_NAME_FOR_SERVER":
                            handleUpdateStoreNameForServer(incomingMsg, user);
                            break;
                        // Shopping cart pages
                        case "ADD_TO_SHOPPING_CART":
                            handleAddToShoppingCart(incomingMsg, user);
                            break;
                        case "DELETE_FROM_SHOPPING_CART":
                            handleDeleteFromShoppingCart(incomingMsg, user);
                            break;
                        case "VIEW_SHOPPING_CART":
                            handleViewShoppingCart(user);
                            break;
                        case "SELLER_VIEW_SHOPPING_CART":
                            handleSellerViewShoppingCart(user);
                            break;
                        case "PURCHASE_SHOPPING_CART":
                            handlePurchaseShoppingCart(user);
                            break;
                        default:
                    }
                } catch (IOException e) {
                    break;
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        try {
            // Server port number 4242
            ServerSocket serverSocket = new ServerSocket(4242);

            while (true) {
                Socket socket = serverSocket.accept();
                MarketPlaceServer mp = new MarketPlaceServer(socket);
                mp.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLogin(String incomingMsg, CurrentUser user) throws IOException {
        String userid = incomingMsg.split("\\|")[1].split(",")[0].trim();
        String pwd = incomingMsg.split("\\|")[1].split(",")[1].trim();
        // validating user information
        String result = User.authenticateUser(userid, pwd, user);

        // sending the results to the server
        writer.write(result);
        writer.println();
        writer.flush();
    }

    private void handleCreateAccount(String incomingMsg) {
        // parsing incoming message from server
        String userid = incomingMsg.split("\\|")[1].split(",")[0].trim();
        String pwd = incomingMsg.split("\\|")[1].split(",")[1].trim();
        String role = incomingMsg.split("\\|")[1].split(",")[2].trim();
        String storeName = incomingMsg.split("\\|")[1].split(",")[3].trim();

        // Creating value string for userid
        String value = pwd + "|" + role + "|" + storeName;
        boolean result = User.addUser(userid, value);

        try {
            if (result) {
                // create a new file for store if user is seller
                // creates a new file for transactions associated with each store
                if (!storeName.equalsIgnoreCase("nostore")) {
                    new File(storeName + ".txt").createNewFile();
                    new File(storeName + "Transaction.txt").createNewFile();
                    new File(userid + "_SellerStores.txt");
                    PrintWriter writerSellerStores = new PrintWriter(new FileOutputStream(userid +
                            "_SellerStores.txt"));
                    writerSellerStores.write(storeName);
                    writerSellerStores.close();
                } else { // creates a new file for customer with userid
                    new File(userid + ".txt").createNewFile();
                    new File(userid + "_ShoppingCart.txt").createNewFile();
                }

                writer.write("success");
                writer.println();
                writer.flush();
            } else {
                writer.write("error");
                writer.println();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCreateStore(String incomingMsg, CurrentUser user) throws IOException {
        String result = "";
        try {
            // parsing incoming message from client
            String storeName = incomingMsg.split("\\|")[1].trim();
            Seller.updateStores(storeName, user);
            result = "success|" + Seller.getSellerStores(user);
        } catch (Exception e) {
            result = "error";
        }
        writer.write(result);
        writer.println();
        writer.flush();
    }

    private void handleImportProduct(String incomingMsg, CurrentUser user) {
        // parsing the incoming message for import file name
        String importFileName = incomingMsg.split("\\|")[1].trim();
        String storeFileName = user.getStoreName() + ".txt";
        String importStatus = Seller.importProducts(importFileName, storeFileName);

        // sending the result of importProduct to the server
        writer.write(importStatus);
        writer.println();
        writer.flush();
    }

    private void handleExportProduct(String incomingMsg, CurrentUser user) {
        String exportFileName = incomingMsg.split("\\|")[1].trim();
        String storeFileName = user.getStoreName() + ".txt";
        String exportStatus = Seller.exportProducts(exportFileName, storeFileName);

        // sending the results of exportProduct to the server
        writer.write(exportStatus);
        writer.println();
        writer.flush();
    }

    private void handleCreateProduct(String incomingMsg, CurrentUser user) {
        // parsing the incoming message
        String flavorName = incomingMsg.split("\\|")[1].split(",")[0].trim();
        String storeName = user.getStoreName();
        String description = incomingMsg.split("\\|")[1].split(",")[1].trim();
        int quantity = Integer.parseInt(incomingMsg.split("\\|")[1].split(",")[2].trim());
        double price = Double.parseDouble(incomingMsg.split("\\|")[1].split(",")[3].trim());
        String storeFileName = user.getStoreName() + ".txt";
        Product prd = new Product(flavorName, storeName, description, quantity, price);

        String createProductStatus = Seller.createProduct(prd, storeFileName);

        // sending the result of createProduct to the server
        writer.write(createProductStatus);
        writer.println();
        writer.flush();
    }

    private void handleEditProduct(String incomingMsg, CurrentUser user) throws IOException {
        // parsing the incoming message
        String flavorName = incomingMsg.split("\\|")[1].split(",")[0].trim();
        String storeName = user.getStoreName();
        String description = incomingMsg.split("\\|")[1].split(",")[1].trim();
        int quantity = Integer.parseInt(incomingMsg.split("\\|")[1].split(",")[2].trim());
        double price = Double.parseDouble(incomingMsg.split("\\|")[1].split(",")[3].trim());
        String storeFileName = user.getStoreName() + ".txt";
        Product prd = new Product(flavorName, storeName, description, quantity, price);

        String editProductStatus = Seller.editProduct(prd, storeFileName);

        // sending the result of createProduct to the server
        writer.write(editProductStatus);
        writer.println();
        writer.flush();
    }

    private void handleSelectProduct(CurrentUser user) throws IOException {
        // send list of all products in store to client
        Seller.loadProducts(user.getStoreName() + ".txt");
        objWriter.writeObject(Seller.productList);
        objWriter.flush();
    }

    private void handleDeleteProduct(String incomingMsg, CurrentUser user) {
        // parsing the incoming message
        String flavorName = incomingMsg.split("\\|")[1].split(",")[0].trim();
        String storeName = user.getStoreName();
        String description = incomingMsg.split("\\|")[1].split(",")[1].trim();
        int quantity = Integer.parseInt(incomingMsg.split("\\|")[1].split(",")[2].trim());
        double price = Double.parseDouble(incomingMsg.split("\\|")[1].split(",")[3].trim());
        String storeFileName = user.getStoreName() + ".txt";
        Product prd = new Product(flavorName, storeName, description, quantity, price);

        String deleteProductStatus = Seller.deleteProduct(prd, storeFileName);

        // sending the result of createProduct to the server
        writer.write(deleteProductStatus);
        writer.println();
        writer.flush();
    }

    private void handleViewSalesForStore(CurrentUser user) throws IOException {
        String storeFileName = user.getStoreName() + "Transaction.txt";
        ArrayList<String> result = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(storeFileName));
        String line = bufferedReader.readLine();

        while (line != null) {
            result.add(line);
            line = bufferedReader.readLine();
        }

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No sales yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleSellerStatisticsCustomerList(CurrentUser user) throws IOException {
        String storeFileName = user.getStoreName() + "Transaction.txt";
        HashMap<String, Integer> result;
        SellerStatistics ss = new SellerStatistics();
        result = ss.loadCustomers(storeFileName);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No sales yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleSellerStatisticsSortCustomerList(CurrentUser user) throws IOException {
        String storeFileName = user.getStoreName() + "Transaction.txt";
        ArrayList<SellerSortCustomer> result;
        SellerStatistics ss = new SellerStatistics();
        result = ss.sortCustomers(storeFileName);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No sales yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleSellerStatisticsProductList(CurrentUser user) throws IOException {
        String storeFileName = user.getStoreName() + "Transaction.txt";
        HashMap<String, Integer> result;
        SellerStatistics ss = new SellerStatistics();
        result = ss.loadProducts(storeFileName);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No sales yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleSellerStatisticsSortProductList(CurrentUser user) throws IOException {
        String storeFileName = user.getStoreName() + "Transaction.txt";
        ArrayList<SellerSortProduct> result;
        SellerStatistics ss = new SellerStatistics();
        result = ss.sortProducts(storeFileName);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No sales yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleViewMarketPlace() throws IOException {
        HashMap<String, Product> result;
        result = Customer.loadProducts();

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No products have been listed yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();
        // necessary wait to avoid java optionalData and output stream corrupted exceptions on client side
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleViewSortedByPriceMarketPlace() throws IOException {
        ArrayList<Product> result;
        Customer customer = new Customer();
        result = customer.sortProductsByPrice();

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No products have been listed yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleViewSortedByQuantityMarketPlace() throws IOException {
        ArrayList<Product> result;
        Customer customer = new Customer();
        result = customer.sortProductsByQuantity();

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No products have been listed yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleSearchForProduct(String incomingMsg) throws IOException {
        String searchString = incomingMsg.split("\\|")[1].trim();
        HashMap<String, Product> result;
        Customer customer = new Customer();
        result = customer.searchForProducts(searchString);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("Your search did not match any products");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleViewPurchaseHistory(CurrentUser user) throws IOException {
        ArrayList<String> result;
        Customer customer = new Customer();
        result = customer.viewPurchaseHistory(user);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No products have been purchased yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handlePurchaseProduct(String incomingMsg, CurrentUser user) {
        String purchasePrdStatus = Customer.purchaseProduct(incomingMsg, user);

        // sending the results of exportProduct to the server
        writer.write(purchasePrdStatus);
        writer.println();
        writer.flush();
    }

    private void handleExportPurchaseHistory(String incomingMsg, CurrentUser user) {
        String exportFileName = incomingMsg.split("\\|")[1].trim();
        String customerFileName = user.getUsername() + ".txt";
        String exportStatus = Customer.exportPurchaseHistory(exportFileName, customerFileName);

        // sending the results of exportProduct to the server
        writer.write(exportStatus);
        writer.println();
        writer.flush();
    }

    private void handleCustomerStatisticsStoreByProduct(CurrentUser user) throws IOException {
        String customerFileName = user.getUsername() + ".txt";
        HashMap<String, String> result;
        CustomerStatistics ss = new CustomerStatistics();
        result = ss.loadProducts(customerFileName);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No purchases have been made yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }
    private void handleCustomerStatisticsStoreByNumberOfProducts(CurrentUser user) throws IOException {
        String customerFileName = user.getUsername() + ".txt";
        HashMap<String, Integer> result;
        CustomerStatistics ss = new CustomerStatistics();
        result = ss.loadStores(customerFileName);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No purchases have been made yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }
    private void handleCustomerStatisticsSortStoreByNumberOfProducts(CurrentUser user) throws IOException {
        String customerFileName = user.getUsername() + ".txt";
        ArrayList<CustomerSortStore> result;
        CustomerStatistics ss = new CustomerStatistics();
        result = ss.sortStores(customerFileName);

        // sending the result to the client
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No purchases have been made yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }

    private void handleUpdateStoreNameForServer(String incomingMsg, CurrentUser user) {
        String newStoreName = incomingMsg.split("\\|")[1].trim();
        user.setStoreName(newStoreName);
    }

    private void handleAddToShoppingCart(String incomingMsg, CurrentUser user) throws IOException {
        String result = ShoppingCart.addProductToShoppingCart(incomingMsg, user);
        writer.write(result);
        writer.println();
        writer.flush();
    }
    private void handleDeleteFromShoppingCart(String incomingMsg, CurrentUser user) {
        String result = ShoppingCart.deleteProductFromCart(incomingMsg, user);
        writer.write(result);
        writer.println();
        writer.flush();
    }
    private void handleViewShoppingCart(CurrentUser user) throws IOException {
        ArrayList<String> result;
        ShoppingCart sc = new ShoppingCart();
        result = sc.viewShoppingCart(user);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No products have been added to shopping cart yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }
    private void handleSellerViewShoppingCart(CurrentUser user) throws IOException {
        ArrayList<String> result;
        ShoppingCart sc = new ShoppingCart();
        result = sc.getAllCustomerShoppingCartItems(user);

        // sending the result to the sever
        if (result == null) {
            writer.write("error");
            writer.println();
            writer.flush();
            return;
        } else if (result.isEmpty()) {
            writer.write("No products have been added to any shopping cart yet");
            writer.println();
            writer.flush();
            return;
        }

        writer.write("success");
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending object to client
        objWriter.writeObject(result);
        objWriter.flush();
    }
    private void handlePurchaseShoppingCart(CurrentUser user) throws IOException {
        String result = ShoppingCart.purchaseProducts(user);
        writer.write(result);
        writer.println();
        writer.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Sending empty list to client
        ArrayList<String> resultList = new ArrayList<>();
        objWriter.writeObject(resultList);
        objWriter.flush();
    }
    private void handleUpdatePassword(String incomingMsg, CurrentUser user) {
        String pwd = incomingMsg.split("\\|")[1].split(",")[0].trim();

        // validating user information
        boolean result = User.updateUserPwd(user.getUsername(), pwd);
        String resultStr = "error";
        if (result) {
            resultStr = "success";
        }

        // sending the results to the server
        writer.write(resultStr);
        writer.println();
        writer.flush();
    }
    private void handleDeleteAccount(String userId) {
        boolean result = User.deleteUserAccount(userId);
        String resultStr = "error";
        if (result) {
            resultStr = "success";
        }
        writer.write(resultStr);
        writer.println();
        writer.flush();
    }
}
