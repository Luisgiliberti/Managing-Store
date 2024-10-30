import java.io.IOException;
import java.util.List;

public interface ClothingStore {

    List<Item> getAllItems();

    List<Customer> getAllCustomers();

    List<Integer> getAllItemIDs();

    List<Item> searchItemsByBrand(String brandName);

    List<Item> searchItemsBySize(String size);

    Customer getCustomer(int customerId);

    List<String> getAllCustomerIDs();

    Integer addClothingItem(ClothingItem newClothingItem);

    ClothingItem getClothingItem(int itemID);


    //1
    boolean addCustomer(Customer customer);

    //2
    boolean removeCustomer(int customerId);

    //6
    boolean removeItem(int itemID);

    //7
    void displayItemInfo(int itemID);

    //11
    boolean purchaseItem(int customerId, int itemID, String size);

    //12
    int checkItemStock(int itemID, String size);

    //13
    default boolean loadDatabaseFromDisk(String itemsFileName, String customersFileName) {
        try {
            loadItemsFromFile(itemsFileName);

            loadCustomersFromFile(customersFileName);

            return true;
        } catch (IOException e) {
            System.out.println("Error loading database from disk: " + e.getMessage());
            return false;
        }
    }

    void loadItemsFromFile(String itemsFileName) throws IOException;

    void loadCustomersFromFile(String customersFileName) throws IOException;

    //14
    default boolean saveDatabaseToDisk(String usersFileName, String itemsFileName) {
        try {
            saveItemsToFile(itemsFileName);
            saveCustomersToFile(usersFileName);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving the database to disk: " + e.getMessage());
            return false;
        }
    }

    void saveItemsToFile(String itemsFileName) throws IOException;

    void saveCustomersToFile(String usersFileName) throws IOException;
}