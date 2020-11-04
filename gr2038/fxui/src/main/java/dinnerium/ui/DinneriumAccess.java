package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.Recipe;
import dinnerium.core.User;
import java.util.List;

public interface DinneriumAccess {

    User registerUser(String username) throws IllegalArgumentException;

    User login(String username);

    User postIngredient(String username, Ingredient ingredient) throws IllegalArgumentException;

    User postRecipe(String username, Recipe recipe) throws IllegalArgumentException;

    List<String> getUnits();
}
