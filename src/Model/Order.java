package Model;

public class Order {
    private int OrderID;
    private String OrderName;
    private double OrderPrice;
    private int OrderQuantity;

    public Order(int orderID, String orderName, double orderPrice, int orderQuantity) {
        OrderID = orderID;
        OrderName = orderName;
        OrderPrice = orderPrice;
        OrderQuantity = orderQuantity;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public double getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        OrderPrice = orderPrice;
    }

    public int getOrderQuantity() {
        return OrderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        OrderQuantity = orderQuantity;
    }
}
