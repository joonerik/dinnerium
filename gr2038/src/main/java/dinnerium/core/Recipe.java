package dinnerium.core;

public class Recipe {

    private IngredientContainer ingredientContainer;
    private RecipeInstructions recipeInstructions;
    private Metadata metadata;

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

    public IngredientContainer getIngredientContainer() {
        return ingredientContainer;
    }

    public RecipeInstructions getRecipeInstructions() {
        return recipeInstructions;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
