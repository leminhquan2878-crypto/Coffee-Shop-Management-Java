public class NewState implements OrderState {
    @Override
    public void next(Order order) { order.setState(new ProcessingState()); }
    @Override
    public void prev(Order order) { System.out.println("Dang o trang thai dau tien."); }
    @Override
    public String name() { return "New"; }
}
