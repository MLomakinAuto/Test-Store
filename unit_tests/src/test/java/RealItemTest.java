import org.junit.jupiter.api.Test;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.*;

public class RealItemTest {

    @Test
    void testGetSetWeight() {
        RealItem realItem = new RealItem();
        double expectedWeight = 10.0;
        realItem.setWeight(expectedWeight);
        double actualWeight = realItem.getWeight();

        assertEquals(expectedWeight, actualWeight, 0.01);
    }

    @Test
    void testToRealItemName() {
        RealItem realItem = new RealItem();
        realItem.setName("Sample Item");
        realItem.setPrice(100.0);
        realItem.setWeight(5.0);

        String expectedToString = "Class: class shop.RealItem; Name: Sample Item; Price: 100.0; Weight: 5.0";

        assertEquals(expectedToString, realItem.toString());
    }
}
