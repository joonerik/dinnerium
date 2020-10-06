package dinnerium.core;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class RecipeContainerTest {

    RecipeContainer recipeContainer = new RecipeContainer();

    @BeforeEach
    void setUp() {
        Quantity q = new Quantity(1.0, "dl");
        Ingredient i = new Ingredient(q, "milk");

        Ingredient ingredient1 = new Ingredient(new Quantity(2.0, "stk"), "egg");
        Ingredient ingredient2 = new Ingredient(new Quantity(5.0, "dl"), "vann");
        Ingredient ingredient3 = new Ingredient(new Quantity(200.0, "gram"), "sukker");
        IngredientContainer ic1 = new IngredientContainer(Arrays.asList(ingredient1, ingredient2, ingredient3));
        IngredientContainer ic2 = new IngredientContainer(Arrays.asList(ingredient1, ingredient3));

        RecipeInstructions recipeInstructions1 = new RecipeInstructions(Arrays.asList("Bland", "Hell i vann", "Pisk"));
        RecipeInstructions recipeInstructions2 = new RecipeInstructions(Arrays.asList("Bland", "Pisk"));

        Metadata metadata1 = new Metadata("Ole", 3.0, "image",
            "Eggedosis m/vann", "God eggedosis med vann!", 10);
        Metadata metadata2 = new Metadata("Kari", 1.5, "image",
            "Eggedosis", "Vanlig eggedosis!", 7);

        Recipe recipe1 = new Recipe(ic1, recipeInstructions1, metadata1);
        Recipe recipe2 = new Recipe(ic2, recipeInstructions2, metadata2);

        recipeContainer.addItem(recipe1);
        recipeContainer.addItem(recipe2);
    }

    @Test
    public void testSetCollection() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                RecipeContainer rc = new RecipeContainer();
                rc.setCollection(new ArrayList<>());
            }
        });
    }

    @Test
    public void testAddItem() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                RecipeContainer rc = new RecipeContainer();
                rc.addItem(null);
            }
        });
    }

    @Test
    public void testGetContainerSize() {
        assertEquals(recipeContainer.getContainerSize(), 2);
    }



}