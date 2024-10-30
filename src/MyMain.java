import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

public class MyMain {

    public static void main(String[] args) {
        String customersFileName = "./MyDatabase/users.txt";
        String itemsFileName = "./MyDatabase/items.txt";

        interactiveSession(customersFileName, itemsFileName);
    }

    public static void interactiveSession(String customersFileName, String itemsFileName) {
        ClothingStore clothingStore = new ClothingStoreImp();
        boolean finish = false;
        int option;
        Scanner sc = new Scanner(System.in);

        while (!finish) {
            printMenu();

            option = selectIntOption(sc, 1, 15);

            switch (option) {
                case 1 -> addCustomer(clothingStore, sc);
                case 2 -> removeCustomer(clothingStore, sc);
                case 3 -> displayCustomerInfo(clothingStore, sc);
                case 4 -> displayAllCustomerIDs(clothingStore);
                case 5 -> addClothingItem(clothingStore, sc);
                case 6 -> removeItem(clothingStore, sc);
                case 7 -> displayItemInfo(clothingStore, sc);
                case 8 -> displayAllItemIDs(clothingStore);
                case 9 -> searchItemsByBrand(clothingStore, sc);
                case 10 -> searchItemsBySize(clothingStore, sc);
                case 11 -> purchaseItem(clothingStore, sc);
                case 12 -> checkItemStock(clothingStore, sc);
                case 13 -> loadDatabaseFromDisk(clothingStore, customersFileName, itemsFileName);
                case 14 -> saveDatabaseToDisk(clothingStore, customersFileName, itemsFileName);
                case 15 -> finish = true;
            }

            if (!finish) {
                System.out.println("Press Enter to continue...");
                sc.nextLine();
            }
        }
    }

    private static void printMenu() {
        System.out.println("------------------------------------");
        System.out.println("              CLOTHING STORE         ");
        System.out.println("------------------------------------");
        System.out.println("1. Add Customer");
        System.out.println("2. Remove Customer");
        System.out.println("3. Display Customer Info");
        System.out.println("4. Display All Customer IDs");
        System.out.println("5. Add Clothing Item");
        System.out.println("6. Remove Item");
        System.out.println("7. Display Item Info");
        System.out.println("8. Display All Item IDs");
        System.out.println("9. Search Items by Brand");
        System.out.println("10. Search Items by Size");
        System.out.println("11. Purchase Item");
        System.out.println("12. Check Item Stock");
        System.out.println("13. Load Database from Disk");
        System.out.println("14. Save Database to Disk");
        System.out.println("15. Exit");
        System.out.println("\n\n");
    }

    public static int selectIntOption(Scanner sc, int fn, int ln) {
        int res = -1;
        boolean validOption = false;

        while (!validOption) {
            System.out.println("Please enter an integer value within the range " + fn + " and " + ln);
            try {
                res = sc.nextInt();
                sc.nextLine();
                if ((res >= fn) && (res <= ln))
                    validOption = true;
                else
                    System.out.println("Sorry but the option must be within the range " + fn + " and " + ln);
            } catch (Exception e) {
                System.out.println("Sorry you did not enter an integer and then press the return key");
                sc.next();
            }
        }
        return res;
    }

    // 1
    private static void addCustomer(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Adding a customer...");

        int customerId = selectIntOption(sc, 1, 100);
        System.out.println("Customer ID:" + customerId);

        System.out.println("Enter customer name:");
        String name = sc.nextLine();

        System.out.println("Enter customer phone number:");
        String phoneNumber = sc.nextLine();

        System.out.println("Enter customer email:");
        String email = sc.nextLine();

        System.out.println("Enter customer address:");
        String address = sc.nextLine();

        Customer newCustomer = new Customer(customerId, name, phoneNumber, email, address);

        boolean success = clothingStore.addCustomer(newCustomer);

        if (success) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add the customer. Please check the inputs.");
        }
    }

    // 2
    private static void removeCustomer(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Removing a customer...");

        System.out.println("Customer ID:");
        int customerId = selectIntOption(sc, 1, 100);

        boolean success = clothingStore.removeCustomer(customerId);

        if (success) {
            System.out.println("Customer removed successfully.");
        } else {
            System.out.println("Failed to remove the customer. Please check the customer ID.");
        }
    }

    // 3
    private static void displayCustomerInfo(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Displaying customer info...");

        System.out.println("Enter customer ID:");
        int customerId = selectIntOption(sc, 1, 100);

        Customer customer = clothingStore.getCustomer(customerId);

        if (customer != null) {
            System.out.println("Customer ID: " + customer.getUserId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Address: " + customer.getAddress());
        } else {
            System.out.println("Customer with ID " + customerId + " not found.");
        }
    }

    // 4
    private static void displayAllCustomerIDs(ClothingStore clothingStore) {
        System.out.println("Displaying all customer IDs...");

        List<String> customerIDs = clothingStore.getAllCustomerIDs();

        if (!customerIDs.isEmpty()) {
            System.out.println("All Customer IDs:");

            for (String customerID : customerIDs) {
                System.out.println(customerID);
            }
        } else {
            System.out.println("No customers found.");
        }
    }

    // 5
    private static void addClothingItem(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Adding a clothing item...");

        System.out.println("Enter clothing item ID:");
        int itemID = selectIntOption(sc, 100, 999);

        System.out.println("Enter clothing item type:");
        String itemType = sc.nextLine();

        System.out.println("Enter clothing item price:");
        double itemPrice = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter the initial stock quantity:");
        int initialStock = selectIntOption(sc, 1, 100);

        System.out.println("Enter clothing item size:");
        String itemSize = sc.nextLine();

        System.out.println("Enter clothing item brand:");
        String itemBrand = sc.nextLine();

        ClothingItem newClothingItem = new ClothingItem(itemID, itemType, itemPrice, initialStock, itemSize, itemBrand);

        int addedItemID = clothingStore.addClothingItem(newClothingItem);

        System.out.println("Clothing item with ID " + addedItemID + " added successfully.");
    }

    // 6
    private static void removeItem(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Removing an item...");

        System.out.println("Enter item ID:");
        int itemID = selectIntOption(sc, 100, 999);

        boolean success = clothingStore.removeItem(itemID);

        if (success) {
            System.out.println("Item with ID " + itemID + " removed successfully.");
        } else {
            System.out.println("Failed to remove the item. Please check the item ID.");
        }
    }
    // 7
    private static void displayItemInfo(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Displaying item info...");

        System.out.println("Enter item ID:");
        int itemID = selectIntOption(sc, 100, 999);

        clothingStore.displayItemInfo(itemID);
    }

    // 8
    private static void displayAllItemIDs(ClothingStore clothingStore) {
        System.out.println("Displaying all item IDs...");

        List<Integer> itemIDs = clothingStore.getAllItemIDs();

        if (!itemIDs.isEmpty()) {
            System.out.println("All Item IDs:");

            for (Integer itemID : itemIDs) {
                System.out.println(itemID);
            }
        } else {
            System.out.println("No items found.");
        }
    }

    // 9
    private static void searchItemsByBrand(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Searching items by brand...");

        System.out.println("Enter brand name:");
        String brandName = sc.nextLine();

        List<Item> items = clothingStore.searchItemsByBrand(brandName);

        if (!items.isEmpty()) {
            System.out.println("Items with brand '" + brandName + "':");

            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("No items found with brand '" + brandName + "'.");
        }
    }

    // 10
    private static void searchItemsBySize(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Searching items by size...");

        System.out.println("Enter size:");
        String size = sc.nextLine();

        List<Item> items = clothingStore.searchItemsBySize(size);

        if (!items.isEmpty()) {
            System.out.println("Items with size '" + size + "':");

            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("No items found with size '" + size + "'.");
        }
    }
    // 11
    private static void purchaseItem(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Purchasing an item...");

        System.out.println("Enter customer ID:");
        int customerId = selectIntOption(sc, 1, 100);

        System.out.println("Enter item ID:");
        int itemID = selectIntOption(sc, 100, 999);

        System.out.println("Enter size:");
        String size = sc.nextLine();

        boolean success = clothingStore.purchaseItem(customerId, itemID, size);

        if (success) {
            System.out.println("Item purchased successfully.");
        } else {
            System.out.println("Failed to purchase the item. Please check your input.");
        }
    }

    // 12
    private static void checkItemStock(ClothingStore clothingStore, Scanner sc) {
        System.out.println("Checking item stock...");

        System.out.println("Enter item ID:");
        int itemID = selectIntOption(sc, 100, 999);

        System.out.println("Enter size:");
        String size = sc.nextLine();

        int stock = clothingStore.checkItemStock(itemID, size);

        if (stock != -1) {
            System.out.println("Current stock of item ID " + itemID + " (Size: " + size + ") is: " + stock);
        } else {
            System.out.println("Item not found. Please check your input.");
        }
    }

    // 13
    private static void loadDatabaseFromDisk(ClothingStore clothingStore, String customersFileName, String itemsFileName) {
        try {
            List<String> customerLines = Files.readAllLines(Path.of(customersFileName));
            for (String line : customerLines) {
                String[] customerData = line.split(",");
                if (customerData.length == 5) {
                    int customerId = Integer.parseInt(customerData[0]);
                    String name = customerData[1];
                    String phoneNumber = customerData[2];
                    String email = customerData[3];
                    String address = customerData[4];

                    Customer newCustomer = new Customer(customerId, name, phoneNumber, email, address);
                    clothingStore.addCustomer(newCustomer);
                }
            }

            List<String> itemLines = Files.readAllLines(Path.of(itemsFileName));
            for (String line : itemLines) {
                String[] itemData = line.split(",");
                if (itemData.length == 6) {
                    int itemID = Integer.parseInt(itemData[0]);
                    String itemType = itemData[1];
                    double itemPrice = Double.parseDouble(itemData[2]);
                    int initialStock = Integer.parseInt(itemData[3]);
                    String itemSize = itemData[4];
                    String itemBrand = itemData[5];

                    ClothingItem newClothingItem = new ClothingItem(itemID, itemType, itemPrice, initialStock, itemSize, itemBrand);
                    clothingStore.addClothingItem(newClothingItem);
                }
            }

            System.out.println("Database loaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load the database from disk. Error: " + e.getMessage());
        }
    }

    // 14
    private static void saveDatabaseToDisk(ClothingStore clothingStore, String customersFileName, String itemsFileName) {
        try (FileWriter customersFileWriter = new FileWriter(customersFileName);
             PrintWriter customersPrintWriter = new PrintWriter(customersFileWriter);
             FileWriter itemsFileWriter = new FileWriter(itemsFileName);
             PrintWriter itemsPrintWriter = new PrintWriter(itemsFileWriter)) {

            List<Customer> customers = clothingStore.getAllCustomers();
            for (Customer customer : customers) {
                customersPrintWriter.println(customer.getUserId() + "," +
                        customer.getName() + "," +
                        customer.getPhoneNumber() + "," +
                        customer.getEmail() + "," +
                        customer.getAddress());
            }

            List<Item> items = clothingStore.getAllItems();
            for (Item item : items) {
                if (item instanceof ClothingItem clothingItem) {
                    itemsPrintWriter.println(clothingItem.getItemId() + "," +
                            clothingItem.getItemType() + "," +
                            clothingItem.getPrice() + "," +
                            clothingItem.getStock() + "," +
                            clothingItem.getSize() + "," +
                            clothingItem.getBrand());
                }
            }

            System.out.println("Database saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save the database to disk. Error: " + e.getMessage());
        }
    }
}