package dinnerium.core;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.Test;

class RecipeContainerTest {

    private final RecipeContainer recipeContainer = new RecipeContainer();

    private final Ingredient ingredient1 = new Ingredient(new Quantity(2.0, "stk"), "egg");
    private final Ingredient ingredient2 = new Ingredient(new Quantity(5.0, "dl"), "vann");
    private final Ingredient ingredient3 = new Ingredient(new Quantity(200.0, "gram"), "sukker");
    private final IngredientContainer ic1 = new IngredientContainer(Arrays.asList(ingredient1, ingredient2, ingredient3));
    private final IngredientContainer ic2 = new IngredientContainer(Arrays.asList(ingredient1, ingredient3));

    private final RecipeInstructions recipeInstructions1 = new RecipeInstructions(Arrays.asList("Bland", "Hell i vann", "Pisk"));
    private final RecipeInstructions recipeInstructions2 = new RecipeInstructions(Arrays.asList("Bland", "Pisk"));

    private final Metadata metadata1 = new Metadata("Ole", 3.0, "image",
        "Eggedosis m/vann", "God eggedosis med vann!", 10);
    private final Metadata metadata2 = new Metadata("Kari", 1.5, "image",
        "Eggedosis", "Vanlig eggedosis!", 7);

    private final Recipe recipe1 = new Recipe(ic1, recipeInstructions1, metadata1);
    private final Recipe recipe2 = new Recipe(ic2, recipeInstructions2, metadata2);

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