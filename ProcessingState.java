public class ProcessingState implements OrderState {
    @Override
    public void next(Order order) { order.setState(new PaidState()); }
    @Override
    public void prev(Order order) { order.setState(new NewState()); }
    @Override
    public String name() { return "Processing"; }
}
