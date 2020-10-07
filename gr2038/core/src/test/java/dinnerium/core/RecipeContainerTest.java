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

    @Test
    void testCollection() {
        Collection<Recipe> collection = new ArrayList<Recipe>() {{
            add(recipe1);
        }};

        RecipeContainer rc = new RecipeContainer(collection);

        assertEquals(rc.getContainer(), collection);
    }

    @Test
    public void testSetCollection() {
        assertThrows(IllegalArgumentException.class, () -> {
                RecipeContainer rc = new RecipeContainer();
                rc.setCollection(new ArrayList<>());
        });
    }

    @Test
    public void testAddItem() {
        assertThrows(IllegalArgumentException.class, () -> {
                RecipeContainer rc = new RecipeContainer();
                rc.addItem(null);
        });
    }


    @Test
    public void testGetContainer() {
        recipeContainer.addItem(recipe1);
        recipeContainer.addItem(recipe2);
        assertEquals(recipeContainer.getContainer(), new ArrayList<>(Arrays.asList(recipe1, recipe2)));
    }

    @Test
    public void testGetContainerSize() {
        recipeContainer.addItem(recipe1);
        recipeContainer.addItem(recipe2);
        assertEquals(recipeContainer.getContainerSize(), 2);
        recipeContainer.getContainer().clear();
        assertEquals(recipeContainer.getContainerSize(), 0);
        recipeContainer.addItem(recipe1);
        assertEquals(recipeContainer.getContainerSize(), 1);
    }

    @Test
    public void testIterator() {
        assertEquals(recipeContainer.iterator().hasNext(), false);
        recipeContainer.addItem(recipe2);
        assertEquals(recipeContainer.iterator().hasNext(), true);
        assertEquals(recipeContainer.iterator().next(), recipe2);
    }



}