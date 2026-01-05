public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.printf("Thanh toan tien mat: %.0f VND%n", amount);
    }
    @Override
    public String name() { return "Cash"; }
}
