package dinnerium.core;

public class Recipe {

    private IngredientContainer ingredientContainer;
    private RecipeInstructions recipeInstructions;
    private Metadata metadata;

    public Recipe(IngredientContainer ingredientContainer, RecipeInstructions recipeInstructions, Metadata metadata) {
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
