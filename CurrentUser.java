/**
 * Project05 -- CurrentUser
 *
 * This class records the details of
 * the user currently logged into
 * the application
 *
 *   
 *
 * @version November 22, 2023
 */
public class CurrentUser {
    private String username;
    private String role;
    private String storeName;
    private String storesList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoresList() {
        return storesList;
    }

    public void setStoresList(String storesList) {
        this.storesList = storesList;
    }
}
