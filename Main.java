
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Main {

    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Khởi tạo hệ thống cơ bản
        Menu menu = new Menu();
        Manager manager = new Manager("M1", "Manager", "manager", "123", menu);
        SeedData.seed(menu, manager);

        Staff staff = new Staff("S1", "Nhan vien A", "staff", "123", "Cashier");
        Customer customer = new Customer("C1", "Khach B", "cust", "123", "0909xxxxxx");

        // Observer cho đơn hàng sẽ gồm: Staff, Kitchen, Customer
        Observer staffObs = new StaffObserver();
        Observer kitchenObs = new KitchenObserver();
        Observer customerObs = new CustomerObserver();

        while (true) {
            System.out.println("\n===== HE THONG QUAN CAFE =====");
            System.out.println("1) Customer");
            System.out.println("2) Staff");
            System.out.println("3) Manager");
            System.out.println("0) Thoat");
            System.out.print("Chon vai tro: ");
            String role = sc.nextLine().trim();

            switch (role) {
                case "1":
                    customer.login();
                    customerMenu(customer, manager, menu, staffObs, kitchenObs, customerObs);
                    customer.logout();
                    break;
                case "2":
                    staff.login();
                    staffMenu(staff, manager, menu);
                    staff.logout();
                    break;
                case "3":
                    manager.login();
                    managerMenu(manager);
                    manager.logout();
                    break;
                case "0":
                    System.out.println("Tam biet!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // ---------------- CUSTOMER FLOW ----------------
    static void customerMenu(Customer customer, Manager manager, Menu menu,
            Observer staffObs, Observer kitchenObs, Observer customerObs) {
        while (true) {
            System.out.println("\n-- CUSTOMER --");
            System.out.println("1) Xem Menu");
            System.out.println("2) Tao don hang");
            System.out.println("0) Quay lai");
            System.out.print("Chon: ");
            String c = sc.nextLine().trim();
            if ("0".equals(c)) {
                return;
            }

            switch (c) {
                case "1":
                    customer.viewMenu(menu);
                    break;
                case "2":
                    // Chọn bàn
                    System.out.println("Chon ban (vi du T1): ");
                    String tId = sc.nextLine().trim();
                    Table t = manager.findTable(tId);
                    if (t == null) {
                        System.out.println("Khong tim thay ban nao.");
                        break;
                    }
                    t.occupy();
                    Order order = customer.createOrder(t);
                    t.setCurrentOrder(order);
                    order.attach(staffObs);
                    order.attach(kitchenObs);
                    order.attach(customerObs);

                    // Thêm món
                    while (true) {
                        menu.printMenu();
                        System.out.print("Nhap ID mon (hoac 0 de dung): ");
                        String id = sc.nextLine().trim();
                        if ("0".equals(id)) {
                            break;
                        }
                        Dish d = menu.findById(id);
                        if (d == null) {
                            System.out.println("Khong co mon nay");
                            continue;
                        }
                        System.out.print("So luong: ");
                        int qty = readInt(1);
                        order.addItem(d, qty);
                    }
                    // Chuyển trạng thái
                    order.getState().printStatus();
                    System.out.println("Chuyen sang Processing...");
                    order.setState(new ProcessingState());
                    order.getState().printStatus();
                    break;
                default:
                    System.out.println("Sai lua chon!");
            }
        }
    }

    // ---------------- STAFF FLOW ----------------
    // ---------------- STAFF FLOW ----------------
    static void staffMenu(Staff staff, Manager manager, Menu menu) {
        while (true) {
            System.out.println("\n-- STAFF --");
            System.out.println("1) Them mon vao Order cua ban");
            System.out.println("2) Xoa mon trong Order cua ban");
            System.out.println("3) Thanh toan don hang");
            System.out.println("4) In hoa don");
            System.out.println("0) Quay lai");
            System.out.print("Chon: ");
            String c = sc.nextLine().trim();
            if ("0".equals(c)) {
                return;
            }

            switch (c) {
                case "1": {
                    System.out.print("Nhap ID ban: ");
                    String tbId1 = sc.nextLine().trim();
                    Table tb1 = manager.findTable(tbId1);
                    if (tb1 == null || tb1.getCurrentOrder() == null) {
                        System.out.println("Khong tim thay Order cua ban " + tbId1);
                        break;
                    }
                    Order o1 = tb1.getCurrentOrder();
                    menu.printMenu();
                    System.out.print("Nhap ID mon muon them: ");
                    String did = sc.nextLine().trim();
                    Dish dish = menu.findById(did);
                    if (dish == null) {
                        System.out.println("Khong co mon nay!");
                        break;
                    }
                    System.out.print("So luong: ");
                    int q = readInt(1);
                    o1.addItem(dish, q);
                    System.out.println("Da them " + q + " " + dish.getName() + " vao Order cua ban " + tbId1);
                    break;
                }

                case "2": {
                    System.out.print("Nhap ID ban: ");
                    String tbId2 = sc.nextLine().trim();
                    Table tb2 = manager.findTable(tbId2);
                    if (tb2 == null || tb2.getCurrentOrder() == null) {
                        System.out.println("Khong tim thay Order cua ban " + tbId2);
                        break;
                    }
                    Order o2 = tb2.getCurrentOrder();
                    System.out.println("Danh sach mon hien tai:");
                    o2.printItems();
                    System.out.print("Nhap ID mon can xoa: ");
                    String rmId = sc.nextLine().trim();
                    o2.removeItem(rmId);
                    System.out.println("Da xoa mon " + rmId + " trong Order cua ban " + tbId2);
                    break;
                }

                case "3": {
                    // Nhap id ban va thanh toan
                    System.out.print("Nhap ID ban can thanh toan (vd: T1): ");
                    String tableId = sc.nextLine().trim();
                    Table table = manager.findTable(tableId);
                    if (table == null) {
                        System.out.println("Khong tim thay ban " + tableId);
                        break;
                    }

                    Order orderToPay = table.getCurrentOrder();
                    if (orderToPay == null) {
                        System.out.println("Ban " + tableId + " chua co don hang nao.");
                        break;
                    }

                    Invoice invoice = new Invoice(orderToPay);

                    System.out.print("Nhap ma khuyen mai (enter de bo qua): ");
                    String code = sc.nextLine().trim();
                    if (!code.isEmpty()) {
                        Promotion p = manager.findPromotion(code);
                        if (p != null) {
                            invoice.applyPromotion(p);
                            System.out.println("Ap dung " + p);
                        } else {
                            System.out.println("Ma khong hop le!");
                        }
                    }

                    System.out.println(invoice.render());

                    PaymentContext ctx = new PaymentContext();
                    System.out.println("Chon phuong thuc: 1) Cash  2) Card  3) E-Wallet");
                    String pm = sc.nextLine().trim();
                    switch (pm) {
                        case "1":
                            ctx.setStrategy(new CashPayment());
                            break;
                        case "2":
                            ctx.setStrategy(new CardPayment());
                            break;
                        case "3":
                            ctx.setStrategy(new EWalletPayment());
                            break;
                        default:
                            System.out.println("Mac dinh: Cash");
                            ctx.setStrategy(new CashPayment());
                    }
                    ctx.pay(invoice.amountToPay());

                    orderToPay.setState(new PaidState());
                    table.free();
                    manager.addCompletedOrder(orderToPay);
                    System.out.println("Thanh toan thanh cong cho ban " + tableId);
                    break;
                }

                case "4": {
                    // In hoa don theo so ban
                    System.out.print("Nhap ID ban can in hoa don (vd: T1): ");
                    String tId = sc.nextLine().trim();
                    Table t2 = manager.findTable(tId);
                    if (t2 == null) {
                        System.out.println("Khong tim thay ban " + tId);
                        break;
                    }

                    Order ord2 = t2.getCurrentOrder();
                    if (ord2 == null) {
                        System.out.println("Ban " + tId + " chua co don hang nao.");
                        break;
                    }

                    Invoice inv = new Invoice(ord2);
                    staff.printInvoice(inv);
                    break;
                }

                default: {
                    System.out.println("Sai lua chon!");
                    break;
                }
            }
        }
    }

    // ---------------- MANAGER FLOW ----------------
    static void managerMenu(Manager manager) {
        while (true) {

            System.out.println("\n-- MANAGER --");
            System.out.println("1) Quan ly Menu");
            System.out.println("2) Quan ly ban");
            System.out.println("3) Quan ly khuyen mai");
            System.out.println("4) Xem bao cao");
            System.out.println("5) Xuat bao cao");
            System.out.println("0) Quay lai");
            System.out.print("Chon: ");
            String c = sc.nextLine().trim();
            if ("0".equals(c)) {
                return;
            }

            switch (c) {
                case "1":
                    manager.showMenu();
                    System.out.println("a) Them mon | b) Xoa mon | c) Sua gia | khac) quay lai");
                    String sub = sc.nextLine().trim();
                    if ("a".equalsIgnoreCase(sub)) {
                        manager.addDish(inputDishFromConsole());
                    } else if ("b".equalsIgnoreCase(sub)) {
                        System.out.print("Nhap ID mon can xoa: ");
                        String id = sc.nextLine().trim();
                        System.out.println(manager.removeDish(id) ? "Da xoa." : "Khong tim thay.");
                    } else if ("c".equalsIgnoreCase(sub)) {
                        System.out.print("Nhap ID mon: ");
                        String id = sc.nextLine().trim();
                        System.out.print("Gia moi: ");
                        double price = readDouble(0);
                        manager.updateDishPrice(id, price);
                        System.out.println("Da cap nhat gia.");
                    }
                    break;
                case "2":
                    List<Table> ts = manager.getTables();
                    System.out.println("Danh sach ban:");
                    for (Table t : ts) {
                        System.out.println(" - " + t);
                    }
                    System.out.println("a) Them ban | b) Cap nhat trang thai | khac) quay lai");
                    String sub2 = sc.nextLine().trim();
                    if ("a".equalsIgnoreCase(sub2)) {
                        System.out.print("ID ban: ");
                        String id = sc.nextLine().trim();
                        System.out.print("So ghe: ");
                        int seats = readInt(1);
                        manager.addTable(new Table(id, seats));
                        System.out.println("Da them ban.");
                    } else if ("b".equalsIgnoreCase(sub2)) {
                        System.out.print("ID ban: ");
                        String id = sc.nextLine().trim();
                        Table t = manager.findTable(id);
                        if (t == null) {
                            System.out.println("Khong thay ban");
                            break;
                        }
                        System.out.println("1) Occupy | 2) Free");
                        String act = sc.nextLine().trim();
                        if ("1".equals(act)) {
                            t.occupy();
                        } else if ("2".equals(act)) {
                            t.free();
                        }
                        System.out.println("Cap nhat: " + t);
                    }
                    break;
                case "3":
                    System.out.println("Khuyen mai hien co:");
                    for (Promotion p : manager.getPromotions()) {
                        System.out.println(" - " + p);
                    }
                    System.out.println("a) Them khuyen mai | khac) quay lai");
                    String sub3 = sc.nextLine().trim();
                    if ("a".equalsIgnoreCase(sub3)) {
                        System.out.print("Ma: ");
                        String code = sc.nextLine().trim();
                        System.out.print("Phan tram (0-100): ");
                        double per = readDouble(0);
                        manager.addPromotion(new Promotion(code, per));
                        System.out.println("Da them.");
                    }
                    break;
                case "4":
                    System.out.println("Danh sach bao cao:");
                    for (Report r : manager.getReports()) {
                        System.out.println(r.getReportId() + " - " + r.getTitle());
                    }
                    break;
                case "5":

                    // Báo cáo doanh thu + món bán chạy trong ngày
                    // Tạo nội dung báo cáo
                    double totalRevenue = 0;
                    Map<String, Integer> dishCount = new HashMap<>();

                    for (Order od : manager.getCompletedOrders()) {
                        Invoice inv = new Invoice(od);
                        totalRevenue += inv.amountToPay();

                        for (OrderItem item : od.getItems()) {
                            dishCount.put(item.getDish().getName(),
                                    dishCount.getOrDefault(item.getDish().getName(), 0) + item.getQuantity());
                        }
                    }

                    StringBuilder content = new StringBuilder();
                    content.append("=== BAO CAO DOANH THU TRONG NGAY ===\n");
                    content.append("Tong doanh thu: ").append(totalRevenue).append(" VND\n");

                    if (!dishCount.isEmpty()) {
                        String bestDish = Collections.max(dishCount.entrySet(), Map.Entry.comparingByValue()).getKey();
                        int bestQty = dishCount.get(bestDish);
                        content.append("Mon ban chay nhat: ").append(bestDish).append(" (").append(bestQty).append(" phan)\n");
                    } else {
                        content.append("Chua co don hang nao duoc thanh toan.\n");
                    }

                    Report report = new Report("R1", "Bao cao ngay", content.toString());

                    // Hỏi người dùng muốn xuất định dạng nào
                    System.out.println("Xuat bao cao: 1) PDF  2) Excel  3) HTML  4) Console");
                    String opt = sc.nextLine().trim();

                    switch (opt) {
                        case "1":
                            new PDFExporter().export(report);
                            break;
                        case "2":
                            new ExcelExporter().export(report);
                            break;
                        case "3":
                            new HTMLExporter().export(report);
                            break;
                        case "4":
                            System.out.println(report.getContent()); // in ra console
                            break;
                        default:
                            System.out.println("Sai lua chon!");
                    }
            }
        }
    }

    // --------- Helpers ----------
    static Dish inputDishFromConsole() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Ten: ");
        String name = sc.nextLine().trim();
        System.out.print("Size: ");
        String size = sc.nextLine().trim();
        System.out.print("Loai: ");
        String cat = sc.nextLine().trim();
        System.out.print("Gia: ");
        double price = readDouble(0);
        return new Dish.DishBuilder().id(id).name(name).size(size).category(cat).price(price).build();
    }

    static int readInt(int min) {
        while (true) {
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min) {
                    return v;
                }
            } catch (Exception ignored) {
            }
            System.out.print("Nhap lai (>= " + min + "): ");
        }
    }

    static double readDouble(double min) {
        while (true) {
            try {
                double v = Double.parseDouble(sc.nextLine().trim());
                if (v >= min) {
                    return v;
                }
            } catch (Exception ignored) {
            }
            System.out.print("Nhap lai (>= " + min + "): ");
        }
    }
}
