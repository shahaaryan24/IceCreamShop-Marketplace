import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

/**
 * Project05 -- User
 *
 * This class handles data preservation
 * of the user details and the validation
 * of users
 *
 *   
 *
 * @version November 21, 2023
 */
public class User {
    static Map<String, String> userList;
    public static void loadUsers() {
        try {
            new File("users.txt").createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            userList = new Hashtable<>();
            String line = reader.readLine();

            while (line != null) {
                // only adds to the dictionary if the user doesn't previously exist
                if (userList.get(line.split("\\|", 2)[0]) == null) {
                    // key is the user id
                    // value is the user details which are pwd, role and store
                    userList.put(line.split("\\|", 2)[0], line.split("\\|", 2)[1]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String authenticateUser(String userid, String givenPwd, CurrentUser user) throws IOException {
        loadUsers();
        // user does not exist
        if (userList.get(userid) == null) {
            return "error";
        }

        // extracting the password of given user
        String actualPwd = userList.get(userid).split("\\|", 2)[0];

        // returns exists followed by role and store if password matches
        if (actualPwd.equals(givenPwd)) {
            // saves the current user info
            user.setUsername(userid);
            user.setRole(userList.get(userid).split("\\|")[1].trim());
            user.setStoreName(userList.get(userid).split("\\|")[2].trim());

            // if user is customer store name is nostore
            // return exists + role + store
            if (user.getRole().equalsIgnoreCase("seller")) {
                return "exists|:" + user.getRole() + "|" + Seller.getSellerStores(user);
            } else {
                return "exists|:" + user.getRole() + "|";
            }

        } else {
            // returns error if password doesn't match
            return "error";
        }
    }

    synchronized public static boolean addUser(String userid, String value) {
        try {
            loadUsers();
            // user already exists
            if (userList.get(userid) != null) {
                return false;
            }

            PrintWriter writer = new PrintWriter(new FileOutputStream("users.txt", true));
            writer.println(userid + "|" + value);
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    synchronized public static boolean updateUserPwd(String userid, String newPwd) {
        try {
            loadUsers();
            // user already exists
            if (userList.get(userid) != null) {
                String pwd;
                String role = userList.get(userid).split("\\|")[1];
                String store = userList.get(userid).split("\\|")[2];
                pwd = newPwd;
                String newValue = String.format("%s|%s|%s", pwd, role, store);
                userList.put(userid, newValue);
            }

            PrintWriter writer = new PrintWriter(new FileOutputStream("users.txt"));
            for (Map.Entry<String, String > item : userList.entrySet()) {
                writer.println(item.getKey() + "|" + item.getValue());
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    synchronized public static boolean deleteUserAccount(String userid) {
        try {
            loadUsers();
            // user already exists
            if (userList.get(userid) != null) {
                // remove user stores file
                // remove store transactions files
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
                    String line = reader.readLine();

                    while (line != null) {
                        // delete sellerstores.txt file and store files if the user is a seller
                        if (line.split("\\|")[0].equalsIgnoreCase(userid)) {
                            if (line.split("\\|")[2].equalsIgnoreCase("seller")) {
                                String sellerStoresFileName = line.split("\\|")[0] + "_SellerStores.txt";
                                BufferedReader storeReader = new BufferedReader(new FileReader(sellerStoresFileName));
                                String storeNamesLine = storeReader.readLine();
                                String[] storeNames = storeNamesLine.split(",");
                                for (int i = 0; i < storeNames.length; i++) {
                                    new File(storeNames[i] + ".txt").delete();
                                    new File(storeNames[i] + "Transaction.txt").delete();
                                }
                                storeReader.close();
                                new File(sellerStoresFileName).delete();
                            } else {
                                // Customer user
                                // delete purchase history file
                                String customerTransactionsFile = line.split("\\|")[0] + ".txt";
                                new File(customerTransactionsFile).delete();
                                // delete shopping cart file if exists
                                File cartFile = new File(line.split("\\|")[0] + "_ShoppingCart.txt");
                                if (cartFile.exists()) {
                                    cartFile.delete();
                                }
                            }
                            break;
                        }
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException e) {
                    return false;
                }
                userList.remove(userid);
            }

            PrintWriter writer = new PrintWriter(new FileOutputStream("users.txt"));
            for (Map.Entry<String, String > item : userList.entrySet()) {
                writer.println(item.getKey() + "|" + item.getValue());
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
