package dinnerium.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    void getQuantityTest() {
        Quantity q = new Quantity(1.0, "dl");
        Ingredient i = new Ingredient(q, "milk");

        assertEquals(q, i.getQuantity());
    }

    @Test
    void getNameTest() {
        Quantity q = new Quantity(1.0, "dl");
        Ingredient i = new Ingredient(q, "milk");

        assertEquals("milk", i.getName());
    }

    @Test
    void setNameTest() {
        Quantity q = new Quantity(1.0, "dl");
        Ingredient i = new Ingredient(q, "milk");

        Quantity q1 = new Quantity(2.0, "stk");
        i.setQuantity(q1);

        assertEquals(q1, i.getQuantity());
    }

    @Test
    void setQuantityTest() {
        Quantity q = new Quantity(1.0, "dl");
        Ingredient i = new Ingredient(q, "milk");

        i.setName("flour");

        assertEquals("flour", i.getName());
    }
}
