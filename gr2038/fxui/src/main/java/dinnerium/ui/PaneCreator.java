package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.Recipe;
import java.util.Iterator;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PaneCreator {

    /**
     * Creates a Pane with the key information from the Recipe provided. The pane contains a
     * rectangle shaped pane with the key information formatted in a nice way.
     *
     * @param recipe     the recipe to extract key information from to add to the pane created.
     * @param anchorPane the AnchorPane where the new pane should be appended to.
     * @return the Pane created during the method with all the information from the recipe.
     */
    public static Pane createRecipePane(Recipe recipe, AnchorPane anchorPane) {
        Pane pane = new Pane();
        pane.setPrefWidth(522);
        pane.setPrefHeight(167);

        Text recipeName = new Text(recipe.getMetadata().getRecipeName());
        recipeName.getStyleClass().add("recipe-name");
        recipeName.setLayoutX(1);
        recipeName.setLayoutY(25);

        Pane childPane = new Pane();
        childPane.setPrefHeight(135);
        childPane.setPrefWidth(522);
        childPane.setLayoutY(30);
        childPane.getStyleClass().add("child-pane");

        Text recipeInfo = new Text(recipe.getIngredientContainer().getContainerSize()
            + " ingredients required  | " + recipe.getMetadata().getMinutes() + " minutes");
        recipeInfo.setLayoutY(30);
        recipeInfo.setLayoutX(127);
        recipeInfo.getStyleClass().add("recipe-info");
        recipeInfo.setWrappingWidth(390);

        Text recipeDescription = new Text(recipe.getMetadata().getRecipeDescription());
        recipeDescription.setWrappingWidth(370);
        recipeDescription.getStyleClass().add("recipe-description");
        recipeDescription.setLayoutX(132);
        recipeDescription.setLayoutY(52);

        childPane.getChildren().addAll(recipeInfo, recipeDescription);
        pane.getChildren().addAll(recipeName, childPane);
        pane.setLayoutX(54);
        pane.setCursor(Cursor.HAND);

        anchorPane.getChildren().add(pane);
        pane.setLayoutY(13 + 180 * (anchorPane.getChildren().size() - 1));
        if (anchorPane.getChildren().size() > 3) {
            anchorPane
                .setPrefHeight(13 + 180 * (anchorPane.getChildren().size()));
        }
        return pane;
    }

    /**
     * Creates a Pane with all the information from the recipe provided formatted in a nice matter
     * to show the author, portions, time to make, title, description, ingredients and instructions.
     * To close the recipeInformation pane you need to set a onclick method on the button returned.
     *
     * @param recipe     the recipe to extract the information to show from.
     * @param anchorPane the AnchorPane to append the pane containing all the information to.
     * @return the button to close the pane with the recipe information with.
     */
    public static Button showRecipeInformation(Recipe recipe, AnchorPane anchorPane) {
        anchorPane.getChildren().clear();
        Pane recipeInfoPane = new Pane();
        recipeInfoPane.getStyleClass().add("recipes-info-pane");
        recipeInfoPane.setLayoutY(10);
        recipeInfoPane.setLayoutX(64);

        Text recipeName = new Text(recipe.getMetadata().getRecipeName());
        recipeName.getStyleClass().add("recipe-info-name");
        recipeName.setLayoutY(26);
        recipeName.setWrappingWidth(522);

        Text recipeDescription = new Text(recipe.getMetadata().getRecipeDescription());
        recipeDescription.getStyleClass().add("recipe-info-description");
        recipeDescription.setLayoutY(80);
        recipeDescription.setLayoutX(20);
        recipeDescription.setWrappingWidth(482);

        Text metadata = new Text("Author: " + recipe.getMetadata().getAuthor() + "\n"
            + "Portions: " + recipe.getMetadata().getPortion() + "\n"
            + "Time: " + recipe.getMetadata().getMinutes() + " minutes");
        metadata.getStyleClass().add("recipe-info-metadata");
        metadata.setLayoutY(15);
        metadata.setLayoutX(10);

        Text ingredientsHeader = new Text("Ingredients");
        ingredientsHeader.getStyleClass().add("textview-header");
        ingredientsHeader.setLayoutY(105);
        ingredientsHeader.setLayoutX(40);

        Iterator<Ingredient> ingredientsIt = recipe.getIngredientContainer().iterator();
        Text ingredients = new Text(ingredientsIt.hasNext() ? "1. " + ingredientsIt.next() : "");
        int i = 2;
        while (ingredientsIt.hasNext()) {
            ingredients.setText(ingredients.getText() + "\n" + i + ". " + ingredientsIt.next());
            i++;
        }
        ingredients.setWrappingWidth(200);
        ingredients.setLayoutY(122);
        ingredients.setLayoutX(20);
        ingredients.getStyleClass().add("list-style");

        Text instructionsHeader = new Text("Instructions");
        instructionsHeader.getStyleClass().add("textview-header");
        instructionsHeader.setLayoutY(105);
        instructionsHeader.setLayoutX(270);

        Iterator<String> instructionIt = recipe.getRecipeInstructions().iterator();
        Text instructions = new Text(instructionIt.hasNext() ? "1. " + instructionIt.next() : "");
        int j = 2;
        while (instructionIt.hasNext()) {
            instructions.setText(instructions.getText() + "\n" + j + ". " + instructionIt.next());
            j++;
        }
        instructions.getStyleClass().add("list-style");
        instructions.setWrappingWidth(200);
        instructions.setLayoutY(122);
        instructions.setLayoutX(250);

        Button hideRecipeInformation = new Button();
        hideRecipeInformation.getStyleClass().add("button-style");
        hideRecipeInformation.setId("hideRecipeInformationButton");
        hideRecipeInformation.setText("Hide recipe");
        hideRecipeInformation.setLayoutY(20);
        hideRecipeInformation.setLayoutX(402);
        hideRecipeInformation.setPrefWidth(100);
        hideRecipeInformation.setCursor(Cursor.HAND);

        recipeInfoPane.getChildren()
            .addAll(recipeName, recipeDescription, hideRecipeInformation, ingredients, instructions,
                metadata, ingredientsHeader, instructionsHeader);
        anchorPane.getChildren().add(recipeInfoPane);

        return hideRecipeInformation;
    }

}
