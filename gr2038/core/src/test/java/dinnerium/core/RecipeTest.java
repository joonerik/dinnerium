package dinnerium.core;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest {

    Recipe r;
    IngredientContainer ic;
    RecipeInstructions ri;
    RecipeMetadata md;

    @BeforeEach
    public void setUp() {
        Quantity q = new Quantity(1000.0, "gram");
        Ingredient i = new Ingredient(q, "Pepper");
        Collection<String> c1 = new ArrayList<>();
        Collection<Ingredient> c2 = new ArrayList<>();
        c2.add(i);

        ic = new IngredientContainer(c2);
        ri = new RecipeInstructions(c1);
        md = new RecipeMetadata(
            "Bakemester Harepus",
            5.0,
            "Pepperkaker",
            "...",
            20
        );

        r = new Recipe(ic, ri, md);
    }

    @Test
    void getIngredientContainer() {
        assertEquals(r.getIngredientContainer(), ic);
    }

    @Test
    void getRecipeInstructions() {
        assertEquals(r.getRecipeInstructions(), ri);
    }

    @Test
    void getMetadata() {
        assertEquals(r.getRecipeMetadata(), md);
    }
}