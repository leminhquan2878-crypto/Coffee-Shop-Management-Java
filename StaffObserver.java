public class StaffObserver implements Observer {
    @Override
    public void updateOrder(Order order, String message) {
        System.out.println("[Observer-Staff] " + message);
    }
}
