package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.Recipe;
import dinnerium.core.Units;
import dinnerium.core.User;

/**
 * Interface stating the methods the application needs to access data from core package.
 */
public interface DinneriumAccess {

    User registerUser(String username) throws IllegalArgumentException;

    User login(String username);

    User postIngredient(String username, Ingredient ingredient) throws IllegalArgumentException;

    User postRecipe(String username, Recipe recipe) throws IllegalArgumentException;

    Units[] getUnits();
}
