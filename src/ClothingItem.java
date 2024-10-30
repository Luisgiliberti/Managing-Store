public class ClothingItem extends Item {

    private final String size;
    private final String brand;

    public ClothingItem(int itemId, String itemType, double price, int stock, String size, String brand) {
        super(String.valueOf(itemId), itemType, price, stock);

        this.size = size;
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public String getBrand() {
        return brand;
    }

    public void reduceStock() {
        if (this.stock > 0) {
            this.stock--;
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Type: %s, Price: %.2f, Stock: %d, Size: %s, Brand: %s",
                getItemId(), getItemType(), getPrice(), getStock(), getSize(), getBrand());
    }
}
