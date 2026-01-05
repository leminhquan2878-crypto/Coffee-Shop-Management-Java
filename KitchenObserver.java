public class KitchenObserver implements Observer {
    @Override
    public void updateOrder(Order order, String message) {
        System.out.println("[Observer-Kitchen] " + message);
    }
}
