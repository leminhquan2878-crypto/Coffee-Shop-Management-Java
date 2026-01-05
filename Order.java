import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String orderId;
    private final Customer customer;
    private final Table table;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderState state = new NewState();
    private final List<Observer> observers = new ArrayList<>();

    public Order(String orderId, Customer customer, Table table) {
        this.orderId = orderId;
        this.customer = customer;
        this.table = table;
    }

    public String getOrderId() { return orderId; }
    public OrderState getState() { return state; }
    public void setState(OrderState s) { 
        this.state = s; 
        notifyObservers("Don " + orderId + " chuyen sang trang thai: " + s.name()); 
    }
    public List<OrderItem> getItems() { return items; }
    public Customer getCustomer() { return customer; }
    public Table getTable() { return table; }

    public void addItem(Dish dish, int qty) {
        items.add(new OrderItem(dish, qty));
        notifyObservers("Them mon " + dish.getName() + " x" + qty + " vao don " + orderId);
    }

    public void removeItem(String dishId) {
        items.removeIf(i -> i.getDish().getId().equals(dishId));
        notifyObservers("Xoa mon " + dishId + " khoi don " + orderId);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public void attach(Observer obs) { observers.add(obs); }
    public void detach(Observer obs) { observers.remove(obs); }

    public void notifyObservers(String message) {
        for (Observer o : observers) o.updateOrder(this, message);
    }

    // ---------------- Thêm mới ----------------

    // In danh sách món trong order
    public void printItems() {
        if (items.isEmpty()) {
            System.out.println("Don hang rong.");
            return;
        }
        System.out.println("=== Danh sach mon trong don " + orderId + " ===");
        double subtotal = 0;
        for (OrderItem item : items) {
            Dish d = item.getDish();
            int qty = item.getQuantity();
            double lineTotal = item.getSubtotal();
            subtotal += lineTotal;
            System.out.printf("- %s (ID:%s) x%d | Don gia: %.2f | Thanh tien: %.2f%n",
                    d.getName(), d.getId(), qty, d.getPrice(), lineTotal);
        }
        System.out.printf("Tong tam tinh: %.2f%n", subtotal);
    }

    // Kiểm tra đơn có rỗng không (tiện dùng thêm)
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
