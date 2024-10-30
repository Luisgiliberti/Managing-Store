public class Item {

    private final String itemId;
    private final String itemType;
    private final double price;
    int stock;

    public Item(String itemId, String itemType, double price, int stock) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.price = price;
        this.stock = stock;
    }

    public Integer getItemId() {
        return Integer.valueOf(itemId);
    }

    public String getItemType() {
        return itemType;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

}