package features.checkout;

import features.cart.Cart;
import features.cart.PlainItem;
import features.items.Category;
import features.items.Item;
import features.orders.ORDER_TYPE;
import features.payment.creditCard.CreditCardCredentials;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CheckoutManagerTest {

    @Test
    @DisplayName("Sample Test Case")
    public void sampleTest() {
        Category category = new Category("Beverages");
        Item item = new Item("Coffee", 999, category);

        PlainItem plainItem = new PlainItem(item);

        Cart.getInstance().addItem(plainItem, 1);

        CheckoutManager.getInstance().checkout("Ahmed", ORDER_TYPE.DELIVERY, "CreditCard", new CreditCardCredentials(
                "4539 1488 0343 6467",
                "Ahmed",
                "12/25",
                "123"
        ));
    }
}
