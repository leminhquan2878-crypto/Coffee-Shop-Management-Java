public interface OrderState {
    void next(Order order);
    void prev(Order order);
    String name();
    default void printStatus() {
        System.out.println("Trang thai don hang: " + name());
    }
}
