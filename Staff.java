public class Staff extends User {
    private String role;

    public Staff(String id, String name, String username, String password, String role) {
        super(id, name, username, password);
        this.role = role;
    }

    public void addDish(Menu menu, Dish dish) {
        menu.addDish(dish);
        System.out.println("Nhan vien da them mon: " + dish.getName());
    }

    public void removeDish(Menu menu, String dishId) {
        if (menu.removeDish(dishId)) {
            System.out.println("Nhan vien da xoa mon: " + dishId);
        } else {
            System.out.println("Khong tim thay mon co id: " + dishId);
        }
    }

    public void printInvoice(Invoice invoice) {
        System.out.println(invoice.render());
    }
}
