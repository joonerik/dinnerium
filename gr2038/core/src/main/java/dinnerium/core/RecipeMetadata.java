package dinnerium.core;

public class RecipeMetadata {

    private final String author;
    private final double portion;
    private final String recipeName;
    private final String recipeDescription;
    private final int minutes;

    /**
     * Constructor for a RecipeMetadata object.
     *
     * @param author            the author of the Recipe.
     * @param portion           the amount of portions the Recipe is meant for.
     * @param recipeName        the name of the dish the recipe is for.
     * @param recipeDescription a description of the dish the recipe makes.
     * @param minutes           the time it takes to make the recipe.
     */
    public RecipeMetadata(String author, double portion,
                          String recipeName, String recipeDescription, int minutes) {
        if (validateAuthor(author)) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author missing");
        }
        if (validateRecipeName(recipeName)) {
            this.recipeName = recipeName;
        } else {
            throw new IllegalArgumentException("Recipename Missing");
        }
        if (validateRecipeDescription(recipeDescription)) {
            this.recipeDescription = recipeDescription;
        } else {
            throw new IllegalArgumentException("Recipedescription missing");
        }
        if (validateNumber(portion, minutes)) {
            this.portion = portion;
            this.minutes = minutes;
        } else {
            throw new IllegalArgumentException("Portions and minutes must be larger than zero");
        }

    }

    private boolean validateNumber(double portion, int minutes) {
        return portion > 0.0 && minutes > 0;
    }

    private boolean validateAuthor(String author) {
        return !author.isBlank();
    }

    private boolean validateRecipeName(String recipeName) {
        return !recipeName.isBlank();
    }

    private boolean validateRecipeDescription(String recipeDescription) {
        return !recipeDescription.isBlank();
    }


    public String getAuthor() {
        return author;
    }

    public Double getPortion() {
        return portion;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public int getMinutes() {
        return minutes;
    }


}
