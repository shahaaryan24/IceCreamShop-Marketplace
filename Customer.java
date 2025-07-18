import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
/**
 * Project05 -- Customer
 *
 * Customers can view the overall marketplace listing products for sale, search for
 * specific products using terms that match the name, store, or description,
 * and sort the marketplace on price or quantity available.
 *
 * Customers can also purchase items from the product page and review a
 * history of their previously purchased items.
 *
 *   
 *
 * @version November 22, 2023
 */
public class Customer {
    static HashMap<String, Product> productList;
    HashMap<String, Product> searchResults = new HashMap<>();
    ArrayList<Product> sortedList = new ArrayList<>();
    ArrayList<String> purchaseHistory = new ArrayList<>();

    // loads all flavors available in marketplace into productList dictionary
    /*public static HashMap<String, Product> loadProducts() {
        productList = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line = reader.readLine();

            while (line != null) {
                // only adds products to the dictionary if the user is a seller
                if (line.split("\\|")[2].equalsIgnoreCase("seller")) {
                    String storeFileName = line.split("\\|")[3] + ".txt";
                    BufferedReader productReader = new BufferedReader(new FileReader(storeFileName));
                    String productLine = productReader.readLine();

                    while (productLine != null) {
                        // creating the product that will be added to dictionary as the value
                        Product productToBeAdded = new Product(productLine.split(",")[0].trim(),
                                productLine.split(",")[1], productLine.split(",")[2],
                                Integer.parseInt(productLine.split(",")[3].trim()),
                                Double.parseDouble(productLine.split(",")[4]));
                        // key is the flavor name + store name
                        // value is the product created above
                        productList.putIfAbsent(productLine.split(",")[0] + line.split("\\|")[3],
                                productToBeAdded);
                        productLine = productReader.readLine();
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return productList;
    }*/

    public static HashMap<String, Product> loadProducts() {
        productList = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line = reader.readLine();

            while (line != null) {
                // only adds products to the dictionary if the user is a seller
                if (line.split("\\|")[2].equalsIgnoreCase("seller")) {
                    String sellerStoresFileName = line.split("\\|")[0] + "_SellerStores.txt";
                    BufferedReader storeReader = new BufferedReader(new FileReader(sellerStoresFileName));
                    String storeNamesLine = storeReader.readLine();
                    String[] storeNames = storeNamesLine.split(",");
                    for (int i = 0; i < storeNames.length; i++) {
                        BufferedReader productReader = new BufferedReader(new FileReader(storeNames[i] + ".txt"));
                        String productLine = productReader.readLine();
                        while (productLine != null) {
                            // creating the product that will be added to dictionary as the value
                            Product productToBeAdded = new Product(productLine.split(",")[0].trim(),
                                    productLine.split(",")[1], productLine.split(",")[2],
                                    Integer.parseInt(productLine.split(",")[3].trim()),
                                    Double.parseDouble(productLine.split(",")[4]));
                            // key is the flavor name + store name
                            // value is the product created above
                            productList.putIfAbsent(productLine.split(",")[0] + productLine.split(",")[1],
                                    productToBeAdded);
                            productLine = productReader.readLine();
                        }
                        productReader.close();
                    }
                    storeReader.close();
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return productList;
    }
    public ArrayList<Product> sortProductsByPrice() {
        loadProducts();
        sortedList.clear();
        for (Map.Entry<String, Product> item : productList.entrySet()) {
            sortedList.add(item.getValue());
        }
        Collections.sort(sortedList, Product.priceComparator);
        return sortedList;
    }

    public ArrayList<Product> sortProductsByQuantity() {
        loadProducts();
        sortedList.clear();
        for (Map.Entry<String, Product> item : productList.entrySet()) {
            sortedList.add(item.getValue());
        }
        Collections.sort(sortedList, Product.quantityComparator);
        return sortedList;
    }

    public HashMap<String, Product> searchForProducts(String searchString) {
        loadProducts();
        for (Map.Entry<String, Product> item : productList.entrySet()) {
            String key = item.getKey();
            Product prd = item.getValue();
            // if product contains search string
            if (prd.getFlavorName().contains(searchString) || prd.getStore().contains(searchString) ||
                    prd.getDescription().contains(searchString)) {
                searchResults.put(key, prd);
            }
        }
        return searchResults;
    }

    synchronized public static String purchaseProduct(String incomingMsg, CurrentUser user) {
        loadProducts();
        // parse incoming message
        String flavorName = incomingMsg.split("\\|")[1].split(",")[0].trim();
        String storeName = incomingMsg.split("\\|")[1].split(",")[1].trim();
        int quantityBeingPurchased = Integer.parseInt(incomingMsg.split("\\|")[1].split(",")[2].trim());
        Product givenPrd = productList.get(flavorName + storeName);

        if (givenPrd.getQuantity() == 0) {
            return "Sorry! Product out of Stock";
        }

        if (givenPrd.getQuantity() < quantityBeingPurchased) {
            return "Error. Quantity you are trying to purchase exceeds quantity in stock";
        }

        givenPrd.setQuantity(givenPrd.getQuantity() - quantityBeingPurchased);
        String result = Seller.editProduct(givenPrd, storeName + ".txt");
        try {
            if (result.equalsIgnoreCase("success")) {
                Transaction transaction = new Transaction();
                PrintWriter customerWriter = new PrintWriter(new FileOutputStream(user.getUsername() + ".txt",
                        true));
                PrintWriter sellerWriter = new PrintWriter(new FileOutputStream(storeName + "Transaction.txt",
                        true));

                // setting the transaction details
                transaction.setCustomerId(user.getUsername());
                transaction.setStoreName(storeName);
                transaction.setFlavorName(flavorName);
                transaction.setQuantity(Integer.toString(quantityBeingPurchased));
                transaction.setPurchasePrice(Double.toString(givenPrd.getPrice()));
                transaction.setTotalPrice(Double.toString(givenPrd.getPrice() * quantityBeingPurchased));
                String transactionMsg = transaction.getTransactionID() + "," + transaction.getTransactionTime() + "," +
                        transaction.getCustomerId() + "," + transaction.getStoreName() + "," +
                        transaction.getFlavorName() + "," + transaction.getQuantity() + "," +
                        transaction.getPurchasePrice() + "," + transaction.getTotalPrice();

                // write transaction details to customer and seller transaction files
                customerWriter.println(transactionMsg);
                sellerWriter.println(transactionMsg);

                customerWriter.close();
                sellerWriter.close();
                return "success";
            }
        } catch (IOException e) {
            return "error";
        }
        return "error";
    }

    public ArrayList<String> viewPurchaseHistory(CurrentUser user) {
        String customerFileName = user.getUsername() + ".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(customerFileName));
            String line = reader.readLine();

            while (line != null) {
                purchaseHistory.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            return null;
        }
        return purchaseHistory;
    }

    public synchronized static String exportPurchaseHistory(String exportFileName, String customerFileName) {
        try {
            Files.copy(Paths.get(customerFileName), Paths.get(exportFileName), REPLACE_EXISTING);
            return "success";
        } catch (IOException e) {
            return "error";
        }
    }
}
