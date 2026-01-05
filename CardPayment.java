public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.printf("Thanh toan the: %.0f VND%n", amount);
    }
    @Override
    public String name() { return "Card"; }
}
