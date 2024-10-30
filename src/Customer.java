import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private final List<Item> purchasedItems;

    public Customer(int userId, String name, String phoneNumber, String email, String address) {
        super(String.valueOf(userId), name, phoneNumber, email, address);
        this.purchasedItems = new ArrayList<>();
    }

    public void purchaseItem(Item item) {
        purchasedItems.add(item);
    }
}