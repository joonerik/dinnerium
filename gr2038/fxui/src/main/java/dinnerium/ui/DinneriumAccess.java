package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.Recipe;
import dinnerium.core.Units;
import dinnerium.core.User;

public interface DinneriumAccess {

    User registerUser(String username) throws IllegalArgumentException;

    User login(String username);

    User postIngredient(String username, Ingredient ingredient) throws IllegalArgumentException;

    User postRecipe(String username, Recipe recipe) throws IllegalArgumentException;

    Units[] getUnits();
}
