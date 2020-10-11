package dinnerium.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuantityTest {

    private final Quantity q = new Quantity(1.0, "dl");

    @Test
    public void checkUnitTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            Quantity quantity = new Quantity(12, "lb");
        });
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
    public void setAmountTest() {
        q.setAmount(2.0);

        assertEquals(2.0, q.getAmount());
    }

    @Test
    public void setUnitTest() {
        q.setUnit("stk");
        assertEquals("stk", q.getUnit());
    }

    @Test
    void testToString() {
        String correct = q.getAmount() + " " + q.getUnit();
        assertEquals(q.toString(), correct);
    }
}
