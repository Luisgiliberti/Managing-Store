import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class ClothingStoreImp implements ClothingStore {

    private final List<Item> inventory;

    public List<Item> getAllItems() {
        return new ArrayList<>(inventory);
    }

    private final List<Customer> customers;


    public ClothingStoreImp() {
        this.inventory = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    @Override
    public List<String> getAllCustomerIDs() {
        List<String> customerIDs = new ArrayList<>();
        for (Customer customer : customers) {
            customerIDs.add(customer.getUserId());
        }
        return customerIDs;
    }
    // 1
    @Override
    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }
    // 2
    @Override
    public boolean removeCustomer(int customerId) {
        return customers.removeIf(customer -> customer.getUserId().equals(String.valueOf(customerId)));
    }

    //3
    @Override
    public Customer getCustomer(int customerId) {
        for (Customer customer : customers) {
            if (customer.getUserId().equals(Integer.toString(customerId))) {
                return customer;
            }
        }
        return null;
    }

    // 4
    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    // 5
    @Override
    public Integer addClothingItem(ClothingItem newClothingItem) {
        inventory.add(newClothingItem);

        return newClothingItem.getItemId();
    }

    // 6
    @Override
    public boolean removeItem(int itemID) {
        return inventory.removeIf(item -> item.getItemId() == itemID);
    }

    // 7
    @Override
    public void displayItemInfo(int itemID) {
        for (Item item : inventory) {
            if (item.getItemId() == itemID) {
                System.out.println("Item ID: " + item.getItemId());
                System.out.println("Item Type: " + item.getItemType());
                System.out.println("Price: " + item.getPrice());
                System.out.println("Stock: " + item.getStock());

                if (item instanceof ClothingItem clothingItem) {
                    System.out.println("Size: " + clothingItem.getSize());
                    System.out.println("Brand: " + clothingItem.getBrand());
                }

                return;
            }
        }

        System.out.println("Item with ID " + itemID + " not found.");
    }

    // 8
    @Override
    public List<Integer> getAllItemIDs() {
        List<Integer> itemIDs = new ArrayList<>();
        for (Item item : inventory) {
            itemIDs.add(item.getItemId());
        }
        return itemIDs;
    }

    // 9
    @Override
    public List<Item> searchItemsByBrand(String brand) {
        List<Item> itemsByBrand = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof ClothingItem clothingItem) {
                if (clothingItem.getBrand().equalsIgnoreCase(brand)) {
                    itemsByBrand.add(item);
                }
            }
        }
        return itemsByBrand;
    }

    // 10
    @Override
    public List<Item> searchItemsBySize(String size) {
        List<Item> itemsBySize = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof ClothingItem clothingItem) {
                if (clothingItem.getSize().equalsIgnoreCase(size)) {
                    itemsBySize.add(item);
                }
            }
        }
        return itemsBySize;
    }

    //11
    @Override
    public ClothingItem getClothingItem(int itemID) {
        for (Item item : inventory) {
            if (item.getItemId() == itemID && item instanceof ClothingItem) {
                return (ClothingItem) item;
            }
        }
        return null;
    }

    @Override
    public boolean purchaseItem(int customerId, int itemID, String size) {
        Customer customer = getCustomer(customerId);
        ClothingItem item = getClothingItem(itemID);

        if (customer != null && item != null) {
            if (item.getStock() > 0 && item.getSize().equalsIgnoreCase(size)) {
                item.reduceStock();
                customer.purchaseItem(item);
                return true;
            }
        }
        return false;
    }

    // 12
    @Override
    public int checkItemStock(int itemID, String size) {
        List<Item> items = getAllItems();

        for (Item item : items) {
            if (item.getItemId() == itemID && item instanceof ClothingItem clothingItem) {
                if (clothingItem.getSize().equalsIgnoreCase(size)) {
                    return clothingItem.getStock();
                }
            }
        }

        return -1;
    }

    // 13
    @Override
    public boolean loadDatabaseFromDisk(String itemsFileName, String customersFileName) {
        return ClothingStore.super.loadDatabaseFromDisk(itemsFileName, customersFileName);
    }
    @Override
    public void loadItemsFromFile(String itemsFileName) {}
    @Override
    public void loadCustomersFromFile(String customersFileName) {}

    // 14
    @Override
    public void saveItemsToFile(String itemsFileName) throws IOException {
        List<Item> items = getAllItems();
        StringBuilder itemContent = new StringBuilder();
        for (Item item : items) {
            if (item instanceof ClothingItem clothingItem) {
                String itemLine = String.format("%d,%s,%.2f,%d,%s,%s",
                        clothingItem.getItemId(), clothingItem.getItemType(), clothingItem.getPrice(),
                        clothingItem.getStock(), clothingItem.getSize(), clothingItem.getBrand());
                itemContent.append(itemLine).append(System.lineSeparator());
            }
        }
        Files.write(Path.of(itemsFileName), itemContent.toString().getBytes());
    }

    @Override
    public void saveCustomersToFile(String usersFileName) throws IOException {
        List<Customer> customers = getAllCustomers();
        StringBuilder customerContent = new StringBuilder();
        for (Customer customer : customers) {
            String customerLine = String.format("%s,%s,%s,%s,%s",
                    customer.getUserId(), customer.getName(), customer.getPhoneNumber(),
                    customer.getEmail(), customer.getAddress());
            customerContent.append(customerLine).append(System.lineSeparator());
        }
        Files.write(Path.of(usersFileName), customerContent.toString().getBytes());
    }

}