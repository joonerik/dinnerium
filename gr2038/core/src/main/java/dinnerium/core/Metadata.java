package dinnerium.core;

public class Metadata {

    private final String author;
    private final double portion;
    private final String image;
    private final String recipeName;
    private final String recipeDescription;

    /**
     * Constructor for a Metadata object.
     *
     * @param author the author of the Recipe.
     * @param portion the amount of portions the Recipe is meant for.
     * @param image the image of the recipe.
     * @param recipeName the name of the dish the recipe is for.
     * @param recipeDescription a description of the dish the recipe makes.
     */
    public Metadata(String author, double portion,
                    String image, String recipeName, String recipeDescription) {
        this.author = author;
        this.portion = portion;
        this.image = image;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }

    public String getAuthor() {
        return author;
    }

    public Double getPortion() {
        return portion;
    }

    public String getImage() {
        return image;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }


}
