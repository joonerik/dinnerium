package dinnerium.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityTest {

    @Test
    public void getterTest() {
        Quantity q = new Quantity(12, "dl");

        assertEquals(12, q.getAmount());
        assertEquals("dl", q.getUnit());
    }

    @Test
    public void constructorShouldRound() {
        Quantity q = new Quantity(12.3456, "dl");
        Quantity q1 = new Quantity(1.2345, "dl");

        assertEquals(12.35, q.getAmount());
        assertEquals(1.23, q1.getAmount());
    }

    @Test
    public void constructorShouldValidateUnit() {
        Quantity q = new Quantity(12, "lb");
        //not finished
    }
}
