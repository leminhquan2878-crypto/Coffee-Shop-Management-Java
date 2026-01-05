public interface PaymentStrategy {
    void pay(double amount);
    String name();
}
