package testNG;

import org.testng.annotations.Test;
import shop.VirtualItem;
import static org.testng.Assert.assertEquals;

public class VirtualItemTests {

    @Test
    public void testVirtualItemName() {
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("Sample Virtual Item");
        virtualItem.setPrice(50.0);
        virtualItem.setSizeOnDisk(1024.0);

        String expectedToString = "Class: class shop.VirtualItem; Name: Sample Virtual Item; Price: 50.0; Size on disk: 1024.0";

        assertEquals(virtualItem.toString(), expectedToString);
    }
}
