package dinnerium.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuantityTest {

    private final Quantity q = new Quantity(1.0, "dl");

    @Test
    public void checkUnitTest() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(12, "lb"));
    }

    @Test void testIllegalAmount() {
        assertThrows(IllegalAccessException.class, () -> new Quantity(-1, "stk"));
    }

    @Test
    public void getAmountTest() {
        assertEquals(1.0, q.getAmount());
    }

    @Test
    public void getUnitTest() {
        assertEquals("dl", q.getUnit());
    }


    @Test
    void testToString() {
        String correct = q.getAmount() + " " + q.getUnit();
        assertEquals(q.toString(), correct);
    }
}
