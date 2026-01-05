import java.util.Arrays;

public class SeedData {
    public static void seed(Menu menu, Manager manager) {
        menu.addDish(new Dish.DishBuilder().id("D1").name("Ca phe sua").size("M").category("Drink").price(25000).build());
        menu.addDish(new Dish.DishBuilder().id("D2").name("Tra dao").size("M").category("Drink").price(30000).build());
        menu.addDish(new Dish.DishBuilder().id("D3").name("Banh croissant").size("1c").category("Food").price(22000).build());
        menu.addDish(new Dish.DishBuilder().id("D4").name("Americano").size("L").category("Drink").price(22000).build());
        
        manager.addTable(new Table("T1", 2));
        manager.addTable(new Table("T2", 4));
        manager.addTable(new Table("T3", 6));

        manager.addPromotion(new Promotion("WELCOME10", 10));
        manager.addPromotion(new Promotion("HAPPYHOUR20", 20));

        manager.addReport(new Report("R1", "Doanh thu ngay", "Tong đơn: 36 - Doanh thu: 5,400,000 VND"));
        manager.addReport(new Report("R2", "Top mon ban chay", "1) Ca phe sua  2)Americano  "));
    }
}
