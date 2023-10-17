package testNG;

import org.testng.annotations.Test;
import shop.RealItem;

import static org.testng.Assert.assertEquals;

public class RealItemTests {

    @Test
    public void testGetSetWeight() {
        RealItem realItem = new RealItem();
        double expectedWeight = 10.0;
        realItem.setWeight(expectedWeight);
        double actualWeight = realItem.getWeight();

        assertEquals(actualWeight, expectedWeight, 0.01);
    }

    @Test
    public void testToRealItemName() {
        RealItem realItem = new RealItem();
        realItem.setName("Sample Item");
        realItem.setPrice(100.0);
        realItem.setWeight(5.0);

        String expectedToString = "Class: class shop.RealItem; Name: Sample Item; Price: 100.0; Weight: 5.0";

        assertEquals(realItem.toString(), expectedToString);
    }
}