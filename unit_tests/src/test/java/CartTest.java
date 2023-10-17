import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest {
    private Cart cart;
    private RealItem realItem1;
    private RealItem realItem2;
    private VirtualItem virtualItem1;

    @BeforeEach
    void setUp() {
        cart = new Cart("TestCart");
        realItem1 = new RealItem();
        realItem1.setName("Item1");
        realItem1.setPrice(10.0);
        realItem2 = new RealItem();
        realItem2.setName("Item2");
        realItem2.setPrice(20.0);
        virtualItem1 = new VirtualItem();
        virtualItem1.setName("Sample Virtual Item");
        virtualItem1.setPrice(30.0);
    }

    @Test
    void testAddRealItemAndCalculateTotalPrice() {
        cart.addRealItem(realItem1);

        double expectedTotalPrice = 10.0 + (10.0 * 0.2);
        assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }

    @Test
    void testAddVirtualItemAndCalculateTotalPrice() {

        cart.addVirtualItem(virtualItem1);

        double expectedTotalPrice = 30.0 + (30.0 * 0.2);

        assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }

    @Test
    void testAddRealItemAndCalculateTotalPriceWithGroupedAssertion() {
        cart.addRealItem(realItem2);

        double expectedTotalPrice = 20.0 + (20.0 * 0.2);

        assertAll("Cart Properties",
                () -> assertEquals("TestCart", cart.getCartName(), "Cart name mismatch"),
                () -> assertTrue(cart.getTotalPrice() >= 0, "Total price is negative"),
                () -> assertEquals(expectedTotalPrice, cart.getTotalPrice(), "Total price mismatch")
        );
    }

    @Test
    public void testTotalPriceAfterAddingAndDeletingItems() {
        cart.addRealItem(realItem1);
        assertEquals(12.0, cart.getTotalPrice(), 0.01); // Total price should be 12.0

        cart.addRealItem(realItem2);
        assertEquals(36.0, cart.getTotalPrice(), 0.01); // Total price should be 36.0

        cart.addVirtualItem(virtualItem1);
        assertEquals(72.0, cart.getTotalPrice(), 0.01); // Total price should be 72.0

        cart.deleteRealItem(realItem1);
        assertEquals(60.0, cart.getTotalPrice(), 0.01); // Total price should be 60.0

        cart.deleteVirtualItem(virtualItem1);
        assertEquals(24.0, cart.getTotalPrice(), 0.01); // Total price should be 24.0
    }
}