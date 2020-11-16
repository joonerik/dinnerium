package dinnerium.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class ContainerTest {

    private final Quantity quantity1 = new Quantity(2.0, "stk");
    private final Quantity quantity2 = new Quantity(5.0, "dl");
    private final Quantity quantity3 = new Quantity(200.0, "gram");
    private final Ingredient ingredient1 = new Ingredient(quantity1, "egg");
    private final Ingredient ingredient2 = new Ingredient(quantity2, "vann");
    private final Ingredient ingredient3 = new Ingredient(quantity3, "sukker");

    private final IngredientContainer ic1 = new IngredientContainer(
        Arrays.asList(ingredient1, ingredient2, ingredient3));
    private final IngredientContainer ic2 =
        new IngredientContainer(Arrays.asList(ingredient1, ingredient3));

    private final RecipeInstructions recipeInstructions1 =
        new RecipeInstructions(Arrays.asList("Bland", "Hell i vann", "Pisk"));
    private final RecipeInstructions recipeInstructions2 =
        new RecipeInstructions(Arrays.asList("Bland", "Pisk"));

    private final RecipeMetadata recipeMetadata1 = new RecipeMetadata("Ole", 3.0,
        "Eggedosis m/vann", "God eggedosis med vann!", 10);
    private final RecipeMetadata recipeMetadata2 = new RecipeMetadata("Kari", 1.5,
        "Eggedosis", "Vanlig eggedosis!", 7);

    private final Recipe recipe1 = new Recipe(ic1, recipeInstructions1, recipeMetadata1);
    private final Recipe recipe2 = new Recipe(ic2, recipeInstructions2, recipeMetadata2);

    final Quantity q = new Quantity(1.0, "dl");
    final Ingredient i = new Ingredient(q, "milk");

    @Test
    void testConstruction() {
        Collection<Recipe> recipes = new ArrayList<>() {{
            add(recipe1);
        }};

        Collection<Ingredient> ingredients = new ArrayList<>() {{
            add(ingredient1);
        }};

        RecipeContainer rc = new RecipeContainer(recipes);
        assertEquals(rc.getContainer(), recipes);
        IngredientContainer ic = new IngredientContainer(ingredients);
        assertEquals(ic.getContainer(), ingredients);

    }

    @Test
    public void testEmptyListConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RecipeContainer(new ArrayList<>());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new IngredientContainer(new ArrayList<>());
        });
    }

    @Test
    public void testIllegalAddRecipe() {
        assertThrows(IllegalArgumentException.class, () -> {
            Collection<Recipe> rct = new ArrayList<>() {{
               add(recipe1);
            }};
            RecipeContainer rc = new RecipeContainer(rct);
            rc.addRecipe(null);
        });
    }

    @Test
    void testAddIngredient() {
        Collection<Ingredient> ict = new ArrayList<>() {{
            add(i);
        }};

        IngredientContainer ic = new IngredientContainer(ict);
        assertEquals(ict, ic.getContainer());
        ic.addIngredient(i);
        ict.add(i);
        assertEquals(ict, ic.getContainer());
    }

    @Test
    public void testGetContainer() {
        RecipeContainer recipeContainer = new RecipeContainer(new ArrayList<>() {{
            add(recipe1);
            add(recipe2);
        }});
        IngredientContainer ingredientContainer = new IngredientContainer(new ArrayList<>() {{
            add(ingredient1);
            add(ingredient2);
        }});
        assertEquals(recipeContainer.getContainer(),
            new ArrayList<>(Arrays.asList(recipe1, recipe2)));

        assertEquals(ingredientContainer.getContainer(),
            new ArrayList<>(Arrays.asList(ingredient1, ingredient2)));
    }

    @Test
    public void testGetContainerSize() {
        RecipeContainer recipeContainer = new RecipeContainer(new ArrayList<>() {{
            add(recipe1);
            add(recipe2);
        }});
        assertEquals(recipeContainer.getContainerSize(), 2);
        recipeContainer.addRecipe(recipe1);
        assertEquals(recipeContainer.getContainerSize(), 3);

        IngredientContainer ingredientContainer = new IngredientContainer(new ArrayList<>() {{
            add(ingredient1);
            add(ingredient2);
        }});
        assertEquals(ingredientContainer.getContainerSize(), 2);
    }

    @Test
    public void testIterator() {
        RecipeContainer recipeContainer = new RecipeContainer(new ArrayList<>() {{
            add(recipe2);
        }});
        Iterator<Recipe> itRecipe = recipeContainer.iterator();
        assertTrue(itRecipe.hasNext());
        assertEquals(itRecipe.next(), recipe2);
        assertFalse(itRecipe.hasNext());

        IngredientContainer ingredientContainer = new IngredientContainer(new ArrayList<>() {{
            add(ingredient2);
        }});
        Iterator<Ingredient> itIngredient = ingredientContainer.iterator();
        assertTrue(itIngredient.hasNext());
        assertEquals(itIngredient.next(), ingredient2);
        assertFalse(itIngredient.hasNext());
    }
}
