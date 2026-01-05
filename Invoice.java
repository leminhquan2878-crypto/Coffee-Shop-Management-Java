public class Invoice {
    private final Order order;
    private double discountPercent;

    public Invoice(Order order) {
        this.order = order;
        this.discountPercent = 0.0;
    }

    public void applyPromotion(Promotion promo) {
        if (promo != null) {
            this.discountPercent = promo.getPercent();
        }
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== HOA DON =====\n");
        sb.append("Order: ").append(order.getOrderId()).append(" | Ban: ").append(order.getTable().getTableId()).append("\n");
        double total = 0;
        for (OrderItem i : order.getItems()) {
            sb.append(String.format("- %s x%d : %.0f VND%n",
                i.getDish().getName(), i.getQuantity(), i.getSubtotal()));
            total += i.getSubtotal();
        }
        double discount = total * discountPercent / 100.0;
        double toPay = total - discount;
        sb.append(String.format("Tam tinh: %.0f VND%n", total));
        sb.append(String.format("Giam gia: -%.0f VND (%.0f%%)%n", discount, discountPercent));
        sb.append(String.format("Thanh tien: %.0f VND%n", toPay));
        sb.append("===================\n");
        return sb.toString();
    }

    public double amountToPay() {
        double total = order.calculateTotal();
        return total - total * discountPercent / 100.0;
    }
}
