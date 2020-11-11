package dinnerium.core;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;
import org.junit.jupiter.api.Test;

class UserTest {

    private final Ingredient egg = new Ingredient(new Quantity(2.0, "stk"), "egg");
    private final Ingredient sugar = new Ingredient(new Quantity(200.0, "gram"), "sukker");
    private final IngredientContainer ingredientContainer= new IngredientContainer(Arrays.asList(egg, sugar));

    private final RecipeContainer recipeContainer = new RecipeContainer();
    private final RecipeInstructions recipeInstructions = new RecipeInstructions(Arrays.asList("Bland", "Pisk"));

    private final RecipeMetadata recipeMetadata = new RecipeMetadata("Ole", 3.0,
        "Eggedosis m/vann", "God eggedosis med vann!", 10);

    private final Recipe recipe = new Recipe(ingredientContainer, recipeInstructions,
        recipeMetadata);

    private final User user = new User(ingredientContainer, recipeContainer, "Ole");

    @Test
    void getIngredientContainer() {
        assertEquals(user.getIngredientContainer(), ingredientContainer);
    }

    @Test
    void getRecipeContainer() {
        assertEquals(user.getRecipeContainer(), recipeContainer);
    }

    @Test
    void getUsername() {
        assertEquals(user.getUsername(), "Ole");
        assertNotEquals(user.getUsername(), "ole");
    }

    @Test
    void addRecipeToContainer() {
        recipeContainer.addRecipe(recipe);
        assertEquals(1, user.getRecipeContainer().getContainerSize());
    }

    @Test
    void setUsername() {
        user.setUsername("Kari");
        assertEquals(user.getUsername(), "Kari");

        assertThrows(IllegalArgumentException.class, () -> user.setUsername("Ji"));

        assertThrows(IllegalArgumentException.class, () ->
            user.setUsername("ThisIsAVeryVeryVeryVeryLongName"));

        assertThrows(IllegalArgumentException.class, () -> user.setUsername(""));

        assertThrows(IllegalArgumentException.class, () -> user.setUsername("   "));

    }
}