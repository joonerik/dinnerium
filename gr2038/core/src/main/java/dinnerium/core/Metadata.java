package dinnerium.core;

public class Metadata {

    private final String author;
    private final double portion;
    private final String image;
    private final String recipeName;
    private final String recipeDescription;
    private final int minutes;

    /**
     * Constructor for a Metadata object.
     *
     * @param author            the author of the Recipe.
     * @param portion           the amount of portions the Recipe is meant for.
     * @param image             the image of the recipe.
     * @param recipeName        the name of the dish the recipe is for.
     * @param recipeDescription a description of the dish the recipe makes.
     * @param minutes           the time it takes to make the recipe.
     */
    public Metadata(String author, double portion,
                    String image, String recipeName, String recipeDescription, int minutes) {
        if (validateString(author, image, recipeDescription, recipeName)) {
            this.author = author;
            this.image = image;
            this.recipeName = recipeName;
            this.recipeDescription = recipeDescription;

        } else {
            throw new IllegalArgumentException("Invalid string input");
        }

        if (validateNumber(portion, minutes)) {
            this.portion = portion;
            this.minutes = minutes;
        } else {
            throw new IllegalArgumentException("Can't be zero");
        }

    }

    private boolean validateNumber(double portion, int minutes) {
        if (portion == 0.0 || minutes == 0) {
            return false;
        }
        return true;
    }

    private boolean validateString(String author, String image, String recipeDescription,
                                   String recipeName) {
        if (author.isBlank() || image.isBlank() || recipeDescription.isBlank() ||
            recipeName.isBlank()) {
            return false;
        }
        return true;
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

    public int getMinutes() {
        return minutes;
    }


}
