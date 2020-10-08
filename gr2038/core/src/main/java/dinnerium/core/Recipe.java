package dinnerium.core;

public class Recipe {

    private final IngredientContainer ingredientContainer;
    private final RecipeInstructions recipeInstructions;
    private final Metadata metadata;

    /**
     * Constructor for Recipe
     *
     * @param ingredientContainer of Ingredients to be used in Recipe
     * @param recipeInstructions of instructions to be used in Recipe
     * @param metadata of Recipe
     */
    public Recipe(IngredientContainer ingredientContainer,
                  RecipeInstructions recipeInstructions,
                  Metadata metadata) {
        this.ingredientContainer = ingredientContainer;
        this.recipeInstructions = recipeInstructions;
        this.metadata = metadata;
    }

    /**
     * Getter for ingredientContainer
     *
     * @return ingredientContainer
     */
    public IngredientContainer getIngredientContainer() {
        return ingredientContainer;
    }

    /**
     * Getter for recipeInstructions
     *
     * @return recipeInstructions
     */
    public RecipeInstructions getRecipeInstructions() {
        return recipeInstructions;
    }

    /**
     * Getter for metadata
     *
     * @return metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }
}
