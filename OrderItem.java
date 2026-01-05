public class OrderItem {
    private Dish dish;
    private int quantity;

    public OrderItem(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public double getSubtotal() { return dish.getPrice() * quantity; }
    public Dish getDish() { return dish; }
    public int getQuantity() { return quantity; }
}
