package dinnerium.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is for generating users.
 */
public class User {
    private final IngredientContainer ingredientContainer;
    private final RecipeContainer recipeContainer;
    private String username;

    /**
     * Constructor for User.
     *
     * @param ingredientContainer The users "fridge" - sets the ingredientContainer for User.
     *
     * @param recipeContainer   The users "cook-book" - sets the recipeContainer for User.
     *
     * @param username  The users username - sets the username for User.
     */
    public User(IngredientContainer ingredientContainer,
                RecipeContainer recipeContainer,
                String username) {
        this.ingredientContainer = ingredientContainer;
        this.recipeContainer = recipeContainer;
        this.setUsername(username);
    }

    /**
     * Standard getter for the users ingredientContainer - "fridge".
     *
     * @return the users ingredientContainer.
     */
    public IngredientContainer getIngredientContainer() {
        return ingredientContainer;
    }

    /**
     * Standard getter for the users recipeContainer - "cook-book".
     *
     * @return the users recipeContainer.
     */
    public RecipeContainer getRecipeContainer() {
        return recipeContainer;
    }

    /**
     * Standard getter for the users username.
     *
     * @return the users username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the users username.
     *
     * @param username The username to set on the user.
     */
    public void setUsername(String username) {
        if (validateUsername(username)) {
            this.username = username;
        }

    }

    /**
     * Method for validating usernames. It uses regex,
     * and is quite an easy validator to change.
     *
     * @param username The username to validate.
     * @return A boolean value which indicates if the username is valid or not.
     */
    private boolean validateUsername(String username) {
        String pattern = "^(?=.{3,15}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(username);
        if (!m.matches()) {
            throw new IllegalArgumentException(
                    "Username must be at least 3 characters and should not exceed 15"
            );
        }
        return m.matches();
    }
}
