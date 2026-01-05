import java.util.ArrayList;
import java.util.List;

public class Manager extends User {
    private final Menu menu;
    private final List<Table> tables;
    private final List<Promotion> promotions;
    private final List<Report> reports;

    public Manager(String id, String name, String username, String password, Menu menu) {
        super(id, name, username, password);
        this.menu = menu;
        this.tables = new ArrayList<>();
        this.promotions = new ArrayList<>();
        this.reports = new ArrayList<>();
    }
// Trong Manager.java
private List<Order> completedOrders = new ArrayList<>();

public void addCompletedOrder(Order o) { completedOrders.add(o); }
public List<Order> getCompletedOrders() { return completedOrders; }

    // Quản lý Menu
    public void addDish(Dish dish) { menu.addDish(dish); }
    public boolean removeDish(String dishId) { return menu.removeDish(dishId); }
    public void updateDishPrice(String dishId, double newPrice) {
        Dish d = menu.findById(dishId);
        if (d != null) d.setPrice(newPrice);
    }
    public void showMenu() { menu.printMenu(); }

    // Quản lý Bàn
    public void addTable(Table t) { tables.add(t); }
    public List<Table> getTables() { return tables; }
    public Table findTable(String id) {
        return tables.stream().filter(t -> t.getTableId().equals(id)).findFirst().orElse(null);
    }

    // Quản lý khuyến mãi
    public void addPromotion(Promotion p) { promotions.add(p); }
    public List<Promotion> getPromotions() { return promotions; }
    public Promotion findPromotion(String code) {
        return promotions.stream().filter(p -> p.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

    // Báo cáo
    public void addReport(Report r) { reports.add(r); }
    public List<Report> getReports() { return reports; }

    public void exportReport(Report r, String type) {
        Exporter exporter = ExporterFactory.create(type);
        if (exporter == null) {
            System.out.println("Dinh dang khong ho tro: " + type);
            return;
        }
        exporter.export(r);
    }
}
