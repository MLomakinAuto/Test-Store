package testNG;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.testng.Assert.*;

public class CartTests {

    @Test(groups = {"group1"})
    void testAddRealItemAndCalculateTotalPrice() {
        Cart cart = new Cart("Sample Cart");
        RealItem realItem = new RealItem();
        realItem.setName("Sample Real Item");
        realItem.setPrice(100.0);
        realItem.setWeight(2.5);

        cart.addRealItem(realItem);

        double expectedTotalPrice = 100.0 + 100.0 * 0.2;
        assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }


    @Test(groups = {"group2"})
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
    @Parameters({"username", "password"})
    void testAddRealItemAndCalculateTotalPriceWithGroupedAssertion() {
        Cart cart = new Cart("Sample Cart");
        RealItem realItem = new RealItem();
        realItem.setName("Sample Real Item");
        realItem.setPrice(100.0);
        realItem.setWeight(2.5);

        cart.addRealItem(realItem);

        double expectedTotalPrice = 100.0 + (100.0 * 0.2);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(cart.getCartName(), "Sample Cart", "Cart name mismatch");
        softAssert.assertTrue(cart.getTotalPrice() >= 0, "Total price is negative");
        softAssert.assertEquals(cart.getTotalPrice(), expectedTotalPrice, "Total price mismatch");

        softAssert.assertAll();
    }

    @Test
    public void testTotalPriceAfterAddingAndDeletingItems() {
        Cart cart = new Cart("TestCart");
        RealItem realItem1 = new RealItem();
        realItem1.setName("Item1");
        realItem1.setPrice(10.0);
        RealItem realItem2 = new RealItem();
        realItem2.setName("Item2");
        realItem2.setPrice(20.0);
        VirtualItem virtualItem1 = new VirtualItem();
        virtualItem1.setName("Sample Virtual Item");
        virtualItem1.setPrice(30.0);

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
