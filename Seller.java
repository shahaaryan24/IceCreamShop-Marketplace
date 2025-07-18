import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
/**
 * Project05 -- Seller
 *
 * Sellers can create, edit, or delete products associated with their stores.
 * Sellers can view a list of their sales by store, including customer
 * information and revenues from the sale.
 *
 *   
 *
 * @version November 22, 2023
 */
public class Seller {
    static HashMap<String, Product> productList;
    public static void loadProducts(String fileName) {
        try {
            productList = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                // only adds to the dictionary if the product doesn't previously exist
                if (productList.get(line.split(",")[0]) == null) {
                    // creating the product that will be added to dictionary as the value
                    Product productToBeAdded = new Product(line.split(",")[0], line.split(",")[1],
                            line.split(",")[2], Integer.parseInt(line.split(",")[3]),
                            Double.parseDouble(line.split(",")[4]));
                    // key is the flavor name
                    // value is the product created above
                    productList.put(line.split(",")[0], productToBeAdded);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // writing all the products to the store file
    public synchronized static void saveProducts(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(fileName));
            // write the products to the file
            for (Map.Entry<String, Product> item : productList.entrySet()) {
                String key = item.getKey();
                Product prd = item.getValue();
                writer.println(prd.getFlavorName() + "," + prd.getStore() + "," + prd.getDescription() + "," +
                        prd.getQuantity() + "," + prd.getPrice());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String importProducts(String importFileName, String storeFileName) {
        String result = "";
        try {
            loadProducts(storeFileName);
            BufferedReader reader = new BufferedReader(new FileReader(importFileName));
            String errorMsg = "These flavors already exist; ";
            boolean importError = false;
            String line = reader.readLine();

            while (line != null) {
                // only adds to the dictionary if the flavor doesn't previously exist
                if (productList.get(line.split(",")[0]) == null) {
                    // creating the product that will be added to dictionary as the value
                    Product productToBeAdded = new Product(line.split(",")[0], line.split(",")[1],
                            line.split(",")[2], Integer.parseInt(line.split(",")[3]),
                            Double.parseDouble(line.split(",")[4]));
                    // key is the flavor name
                    // value is the product created above
                    productList.put(line.split(",")[0], productToBeAdded);
                } else {
                    importError = true;
                    errorMsg += line.split(",")[0] + ", ";
                }
                line = reader.readLine();

                if (importError) {
                    errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
                    errorMsg += "\nUse Edit or Delete to modify these flavors.";
                    result = errorMsg;
                } else {
                    result = "success";
                }
            }
            reader.close();
            saveProducts(storeFileName);
        } catch (IOException e) {
            result = "error";
        }
        return result;
    }

    public synchronized static String exportProducts(String exportFileName, String storeFileName) {
        try {
            Files.copy(Paths.get(storeFileName), Paths.get(exportFileName), REPLACE_EXISTING);
            return "success";
        } catch (IOException e) {
            return "error";
        }

    }

    public synchronized static String createProduct(Product prd, String storeFileName) {
        String result = "";
        loadProducts(storeFileName);
        boolean createError = false;

        // only adds to the dictionary if the flavor doesn't previously exist
        if (productList.get(prd.getFlavorName()) == null) {
            // key is the flavor name
            // value is the product given as input
            productList.put(prd.getFlavorName(), prd);
        } else {
            createError = true;
        }

        if (createError) {
            result = "Error creating new flavor.";
        } else {
            result = "success";
        }
        saveProducts(storeFileName);
        return result;
    }

    public static String editProduct(Product prd, String storeFileName) {
        String result = "";
        loadProducts(storeFileName);
        boolean editError = false;
        Product existingPrd;

        // only adds to the dictionary if the flavor exists
        if (productList.get(prd.getFlavorName()) != null) {
            existingPrd = productList.get(prd.getFlavorName());
            existingPrd.setDescription(prd.getDescription());
            existingPrd.setQuantity(prd.getQuantity());
            existingPrd.setPrice(prd.getPrice());
        } else {
            editError = true;
        }

        if (editError) {
            result = "Error editing flavor.";
        } else {
            result = "success";
        }
        saveProducts(storeFileName);
        return result;
    }

    public static String deleteProduct(Product prd, String storeFileName) {
        String result = "";
        loadProducts(storeFileName);
        boolean deleteError = false;

        // only adds to the dictionary if the flavor doesn't previously exist
        if (productList.get(prd.getFlavorName()) != null) {
            productList.remove(prd.getFlavorName());
        } else {
            deleteError = true;
        }

        if (deleteError) {
            result = "Error deleting flavor.";
        } else {
            result = "success";
        }
        saveProducts(storeFileName);
        return result;
    }

    public static String getSellerStores(CurrentUser user) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(user.getUsername() + "_SellerStores.txt"));
        String storesList = reader.readLine();
        reader.close();
        return storesList;
    }

    public static void updateStores(String newStoreName, CurrentUser user) throws IOException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(user.getUsername() + "_SellerStores.txt",
                true));
        writer.write("," + newStoreName);
        writer.close();
        new File(newStoreName + ".txt").createNewFile();
        new File(newStoreName + "Transaction.txt").createNewFile();
    }
}
