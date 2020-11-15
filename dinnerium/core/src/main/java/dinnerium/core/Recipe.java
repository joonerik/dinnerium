package dinnerium.core;

/** Class for representing a Recipe with corresponding ingredients, instructions and metadata. */
public class Recipe {

    private final IngredientContainer ingredientContainer;
    private final RecipeInstructions recipeInstructions;
    private final RecipeMetadata recipeMetadata;

    /**
     * Constructor for Recipe.
     *
     * @param ingredientContainer of Ingredients to be used in Recipe
     * @param recipeInstructions of instructions to be used in Recipe
     * @param recipeMetadata of Recipe
     */
    public Recipe(IngredientContainer ingredientContainer,
                  RecipeInstructions recipeInstructions,
                  RecipeMetadata recipeMetadata) {
        this.ingredientContainer = ingredientContainer;
        this.recipeInstructions = recipeInstructions;
        this.recipeMetadata = recipeMetadata;
    }

    /**
     * Returns the container to the ingredients.
     *
     * @return ingredientContainer
     */
    public IngredientContainer getIngredientContainer() {
        return ingredientContainer;
    }

    /**
     * Returns the instructions to the recipe.
     *
     * @return recipeInstructions
     */
    public RecipeInstructions getRecipeInstructions() {
        return recipeInstructions;
    }

    /**
     * Returns the RecipeMetadata for the recipe.
     *
     * @return recipeMetadata
     */
    public RecipeMetadata getRecipeMetadata() {
        return recipeMetadata;
    }
}
