package utils.parsers;

public class PriceParser {
    public static int floatToCents(float price) {
        return Math.round(price * 100);
    }

    public static float centsToFloat(int cents) {
        return cents / 100.0f;
    }

    public static String centsToString(int cents) {
        return String.format("$%.2f", centsToFloat(cents));
    }
}
