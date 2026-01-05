public class CustomerObserver implements Observer {
    @Override
    public void updateOrder(Order order, String message) {
        System.out.println("[Observer-Customer] " + message);
    }
}
