import org.junit.jupiter.api.Test;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualItemTest {

    @Test
    void testVirtualItemName() {
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("Sample Virtual Item");
        virtualItem.setPrice(50.0);
        virtualItem.setSizeOnDisk(1024.0);

        String expectedToString = "Class: class shop.VirtualItem; Name: Sample Virtual Item; Price: 50.0; Size on disk: 1024.0";
        assertEquals(expectedToString, virtualItem.toString());
    }
}
