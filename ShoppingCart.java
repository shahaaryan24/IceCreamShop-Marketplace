import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project05 -- ShoppingCart
 *
 *This class implements the functionality
 * for the shopping cart
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class ShoppingCart {
    static HashMap<String, Product> productList;
    ArrayList<String> shoppingCartItemsList = new ArrayList<>();

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

    synchronized public static String addProductToShoppingCart(String incomingMsg, CurrentUser user) {
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
            return "Error. Quantity you are trying to add to shopping cart exceeds quantity in stock";
        }

        try {
            Transaction transaction = new Transaction();
            PrintWriter customerWriter = new PrintWriter(new FileOutputStream(user.getUsername() + "_ShoppingCart.txt",
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
            customerWriter.close();
            return "success";
        } catch (IOException e) {
            return "error";
        }
    }

    synchronized public static String purchaseProducts(CurrentUser user)  {
        loadProducts();
        // parse shopping cart items
        String customerFileName = user.getUsername() + "_ShoppingCart.txt";
        String errorMsg = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(customerFileName));
            String line = reader.readLine();

            while (line != null) {
                String flavorName = line.split(",")[4].trim();
                String storeName = line.split(",")[3].trim();
                int quantityBeingPurchased = Integer.parseInt(line.split(",")[5].trim());
                Product givenPrd = productList.get(flavorName + storeName);

                if (givenPrd == null) {
                    errorMsg += "Flavor: " + flavorName + " from Store: " + storeName + " Product no longer " +
                            "sold";
                    line = reader.readLine();
                    continue;
                }
                if (givenPrd.getQuantity() == 0) {
                    errorMsg += "Flavor: " + flavorName + " from Store: " + storeName + " Product out of " +
                            "Stock";
                    line = reader.readLine();
                    continue;
                }

                if (givenPrd.getQuantity() < quantityBeingPurchased) {
                    errorMsg += "Flavor: " + flavorName + " from Store: " + storeName + "Quantity you are" +
                            " trying to purchase exceeds quantity in stock";
                    line = reader.readLine();
                    continue;
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
                        String transactionMsg = transaction.getTransactionID() + "," + transaction.getTransactionTime()
                                + "," +
                                transaction.getCustomerId() + "," + transaction.getStoreName() + "," +
                                transaction.getFlavorName() + "," + transaction.getQuantity() + "," +
                                transaction.getPurchasePrice() + "," + transaction.getTotalPrice();

                        // write transaction details to customer and seller transaction files
                        customerWriter.println(transactionMsg);
                        sellerWriter.println(transactionMsg);

                        customerWriter.close();
                        sellerWriter.close();
                    }
                } catch (IOException e) {
                    return "error";
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        // empty the shopping cart
        ArrayList<String> newShoppingCart = new ArrayList<>();
        PrintWriter customerWriter = null;
        try {
            customerWriter = new PrintWriter(new FileOutputStream(user.getUsername() + "_ShoppingCart.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (String str : newShoppingCart) {
            customerWriter.println(str);
        }
        customerWriter.close();
        if (errorMsg.isEmpty()) {
            return "success";
        } else {
            return errorMsg;
        }
    }

    public ArrayList<String> viewShoppingCart(CurrentUser user) {
        String customerFileName = user.getUsername() + "_ShoppingCart.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(customerFileName));
            String line = reader.readLine();

            while (line != null) {
                shoppingCartItemsList.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return shoppingCartItemsList;
    }

    synchronized public static String deleteProductFromCart(String incomingMsg, CurrentUser user) {
        ArrayList<String> newShoppingCart = new ArrayList<>();
        // parsing the incoming message
        String flavorName = incomingMsg.split("\\|")[1].split(",")[0].trim();
        String storeName = incomingMsg.split("\\|")[1].split(",")[1].trim();
        String cartFileName = user.getUsername() + "_ShoppingCart.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(cartFileName));
            String line = reader.readLine();

            while (line != null) {
                String currentFlavorName = line.split(",")[4].trim();
                String currentStoreName = line.split(",")[3].trim();
                if (!(flavorName.equalsIgnoreCase(currentFlavorName) && storeName.equalsIgnoreCase(currentStoreName))) {
                    newShoppingCart.add(line);
                }
                line = reader.readLine();
            }
            reader.close();
            PrintWriter customerWriter = new PrintWriter(new FileOutputStream(user.getUsername()
                    + "_ShoppingCart.txt"));
            for (String str : newShoppingCart) {
                customerWriter.println(str);
            }
            customerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
        return "success";
    }
    public ArrayList<String> getAllCustomerShoppingCartItems(CurrentUser user) {
        ArrayList<String> itemList = new ArrayList<>();
        String currentStoreName = user.getStoreName();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line = reader.readLine();

            while (line != null) {
                // only adds products to the dictionary if the user is a seller
                if (line.split("\\|")[2].equalsIgnoreCase("customer")) {
                    String customerShoppingCartFileName = line.split("\\|")[0] + "_ShoppingCart.txt";
                    BufferedReader cartReader = new BufferedReader(new FileReader(customerShoppingCartFileName));
                    String cartLine = cartReader.readLine();

                    while (cartLine != null) {
                        // creating the product that will be added to dictionary as the value
                        String storeName = cartLine.split(",")[3].trim();
                        if (storeName.equalsIgnoreCase(currentStoreName)) {
                            itemList.add(cartLine);
                        }
                        cartLine = cartReader.readLine();
                    }
                    cartReader.close();
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return itemList;
    }
}
