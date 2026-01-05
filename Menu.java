import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<Dish> dishes = new ArrayList<>();

    public void addDish(Dish d) { dishes.add(d); }
    public boolean removeDish(String dishId) { return dishes.removeIf(d -> d.getId().equals(dishId)); }

    public Dish findById(String id) {
        for (Dish d : dishes) if (d.getId().equals(id)) return d;
        return null;
    }

    public List<Dish> getDishes() { return dishes; }

    public void printMenu() {
        System.out.println("=== MENU ===");
        if (dishes.isEmpty()) {
            System.out.println("(Trong)");
            return;
        }
        for (Dish d : dishes) {
            System.out.printf("%s - %s - %s - %.0f VND%n", d.getId(), d.getName(), d.getSize(), d.getPrice());
        }
    }
}
