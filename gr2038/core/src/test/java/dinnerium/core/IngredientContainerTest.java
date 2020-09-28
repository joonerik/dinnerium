package dinnerium.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientContainerTest {

    @Test
    void addIngredientTest() {
        Quantity q = new Quantity(1.0, "dl");
        Ingredient i = new Ingredient(q, "milk");

        Collection<Ingredient> ict = new ArrayList<Ingredient>() {{
            add(i);
        }};

        IngredientContainer ic = new IngredientContainer(ict);

        ic.addItem(i);

        assertEquals(ict, ic.getContainer());
    }
}
