public class CanceledState implements OrderState {
    @Override
    public void next(Order order) { System.out.println("Don hang da huy."); }
    @Override
    public void prev(Order order) { System.out.println("Khong the quay lai sau khi huy."); }
    @Override
    public String name() { return "Canceled"; }
}
