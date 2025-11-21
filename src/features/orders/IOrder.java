package features.orders;

public interface IOrder {
    public int getTotalAmount();
    public String getOrderSummary();
    public void process(String state);
}
