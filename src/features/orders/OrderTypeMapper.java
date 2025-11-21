package features.orders;

public class OrderTypeMapper {

    public static ORDER_TYPE mapStringToOrderType(String orderTypeStr) throws IllegalArgumentException {
        return switch (orderTypeStr.toLowerCase()) {
            case "dinein" -> ORDER_TYPE.DINE_IN;
            case "takeaway" -> ORDER_TYPE.TAKEAWAY;
            case "delivery" -> ORDER_TYPE.DELIVERY;
            default -> throw new IllegalArgumentException("Invalid order type: " + orderTypeStr);
        };
    }

}
