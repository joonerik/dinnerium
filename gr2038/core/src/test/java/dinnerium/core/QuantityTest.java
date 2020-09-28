package dinnerium.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuantityTest {

    @Test
    public void checkUnitTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            Quantity q = new Quantity(12, "lb");
        });
    }

    @Test
    public void getAmountTest() {
        Quantity q = new Quantity(1.0, "dl");

        assertEquals(1.0, q.getAmount());
        //assertTrue(1.0 == q.getAmount());
    }

    @Test
    public void getUnitTest() {
        Quantity q = new Quantity(1.0, "dl");

        assertEquals("dl", q.getUnit());
    }

    @Test
    public void setAmountTest() {
        Quantity q = new Quantity(1.0, "dl");

        q.setAmount(2.0);

        assertEquals(2.0, q.getAmount());
    }

    @Test
    public void setUnitTest() {
        Quantity q = new Quantity(1.0, "dl");

        q.setUnit("stk");

        assertEquals("stk", q.getUnit());
    }
}
