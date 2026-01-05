import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String phone;
    private final List<Order> orders = new ArrayList<>();

    public Customer(String id, String name, String username, String password, String phone) {
        super(id, name, username, password);
        this.phone = phone;
    }

    public Order createOrder(Table table) {
        Order order = new Order("O" + (orders.size()+1), this, table);
        orders.add(order);
        System.out.println("Khach " + name + " tao don hang " + order.getOrderId() + " tai ban " + table.getTableId());
        return order;
    }

    public void viewMenu(Menu menu) {
        menu.printMenu();
    }

    public void receiveNotification(String msg) {
        System.out.println("[Customer - " + name + "] Thong bao: " + msg);
    }
}
