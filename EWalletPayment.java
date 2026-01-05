public class EWalletPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.printf("Thanh toan vi dien tu: %.0f VND%n", amount);
    }
    @Override
    public String name() { return "E-Wallet"; }
}
