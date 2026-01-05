public class PaidState implements OrderState {
    @Override
    public void next(Order order) { System.out.println("Don hang da thanh toan xong."); }
    @Override
    public void prev(Order order) { order.setState(new ProcessingState()); }
    @Override
    public String name() { return "Paid"; }
}
