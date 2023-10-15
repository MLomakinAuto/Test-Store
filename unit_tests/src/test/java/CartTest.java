import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest {

    @Test
    void testAddRealItemAndCalculateTotalPrice() {
        Cart cart = new Cart("Sample Cart");
        RealItem realItem = new RealItem();
        realItem.setName("Sample Real Item");
        realItem.setPrice(100.0);
        realItem.setWeight(2.5);

        cart.addRealItem(realItem);

        double expectedTotalPrice = 100.0 + (100.0 * 0.2);
        assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }

    @Test
    void testAddVirtualItemAndCalculateTotalPrice() {
        Cart cart = new Cart("Sample Cart");

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("Sample Virtual Item");
        virtualItem.setPrice(50.0);
        virtualItem.setSizeOnDisk(1024.0);

        cart.addVirtualItem(virtualItem);

        double expectedTotalPrice = 50.0 + (50.0 * 0.2);

        assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }

    @Test
    void testAddRealItemAndCalculateTotalPriceWithGroupedAssertion() {
        Cart cart = new Cart("Sample Cart");

        RealItem realItem = new RealItem();
        realItem.setName("Sample Real Item");
        realItem.setPrice(100.0);
        realItem.setWeight(2.5);

        cart.addRealItem(realItem);

        double expectedTotalPrice = 100.0 + (100.0 * 0.2);

        assertAll("Cart Properties",
                () -> assertEquals("Sample Cart", cart.getCartName(), "Cart name mismatch"),
                () -> assertTrue(cart.getTotalPrice() >= 0, "Total price is negative"),
                () -> assertEquals(expectedTotalPrice, cart.getTotalPrice(), "Total price mismatch")
        );
    }
}