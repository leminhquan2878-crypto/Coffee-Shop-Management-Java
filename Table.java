public class Table {
    private String tableId;
    private int seats;
    private boolean occupied;

    public Table(String tableId, int seats) {
        this.tableId = tableId;
        this.seats = seats;
        this.occupied = false;
    }
private Order currentOrder;

public void setCurrentOrder(Order order) {
    this.currentOrder = order;
}

public Order getCurrentOrder() {
    return this.currentOrder;
}

    public String getTableId() { return tableId; }
    public int getSeats() { return seats; }
    public boolean isOccupied() { return occupied; }

    public void occupy() { occupied = true; }
    public void free() { occupied = false; }

    @Override
    public String toString() {
        return "Table{" + tableId + ", seats=" + seats + ", occupied=" + occupied + '}';
    }
}
