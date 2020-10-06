package dinnerium.core;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientTest {

    Quantity q = new Quantity(1.0, "dl");
    Ingredient i = new Ingredient(q, "milk");

    @Test
    void getQuantityTest() {
        assertEquals(q, i.getQuantity());
    }

    @Test
    void getNameTest() {
        assertEquals("milk", i.getName());
    }

    @Test
    void setQuantityTest() {
        Quantity q1 = new Quantity(2.0, "stk");
        i.setQuantity(q1);
        assertEquals(q1, i.getQuantity());

    }

    @Test
    void setNameTest() {
        i.setName("flour");
        assertEquals("flour", i.getName());
    }

    @Test
    void testLowerCaseName() {
        i.setName("Flour");
        assertEquals("flour", i.getName());
    }

    @Test
    void testSetName2() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                i.setName("");
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                i.setName("32456");
            }
        });
    }

    @Test
    void testToString() {
        String correct = "milk " + i.getQuantity().toString();
        assertEquals(i.toString(), correct);
    }

}
