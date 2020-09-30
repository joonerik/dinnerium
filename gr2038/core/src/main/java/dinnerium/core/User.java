package dinnerium.core;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class User {
    private IngredientContainer ingredientContainer;
    private RecipeContainer recipeContainer;
    private String username;

    public User(IngredientContainer ingredientContainer, RecipeContainer recipeContainer, String username) {
        this.ingredientContainer = ingredientContainer;
        this.recipeContainer = recipeContainer;
        this.setUsername(username);
    }

    public IngredientContainer getIngredientContainer() {
        return ingredientContainer;
    }

    public RecipeContainer getRecipeContainer() {
        return recipeContainer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(validateUsername(username))
            this.username = username;
    }

    private boolean validateUsername (String username){
        String pattern = "^(?=.{3,15}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(username);
        if(!m.matches())
            throw new IllegalArgumentException("Invalid username - Must be at least 3 characters and should not exceed 15");
        return m.matches();
    }
}
