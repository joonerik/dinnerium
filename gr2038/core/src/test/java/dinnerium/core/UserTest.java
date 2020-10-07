package dinnerium.core;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    Ingredient egg = new Ingredient(new Quantity(2.0, "stk"), "egg");
    Ingredient sugar = new Ingredient(new Quantity(200.0, "gram"), "sukker");
    IngredientContainer ingredientContainer= new IngredientContainer(Arrays.asList(egg, sugar));

    RecipeContainer recipeContainer = new RecipeContainer();
    RecipeInstructions recipeInstructions = new RecipeInstructions(Arrays.asList("Bland", "Pisk"));

    Metadata metadata = new Metadata("Ole", 3.0, "image",
        "Eggedosis m/vann", "God eggedosis med vann!", 10);

    Recipe recipe = new Recipe(ingredientContainer, recipeInstructions, metadata);

    User user = new User(ingredientContainer, recipeContainer, "Ole");

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
    void setUsername() {
        user.setUsername("Kari");
        assertEquals(user.getUsername(), "Kari");

        assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("Ji");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("ThisIsAVeryVeryVeryVeryLongName");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("   ");
        });

    }
}