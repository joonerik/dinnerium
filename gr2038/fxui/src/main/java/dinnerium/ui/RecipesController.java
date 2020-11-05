package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Metadata;
import dinnerium.core.Quantity;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeInstructions;
import dinnerium.core.Units;
import dinnerium.core.User;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RecipesController {

    private User user;
    final ObservableList<Ingredient> newRecipeIngredients = FXCollections.observableArrayList();
    final ObservableList<String> newRecipeInstructions = FXCollections.observableArrayList();
    private final List<Button> newRecipeIngredientsButtons = new ArrayList<>();
    private final List<Button> newRecipeInstructionsButtons = new ArrayList<>();
    private Pane msgPane;
    private DinneriumAccess dinneriumAccess;

    @FXML
    Pane recipesPane;
    @FXML
    ScrollPane recipesScrollPane;
    @FXML
    AnchorPane recipesAnchorPane;
    @FXML
    Text newRecipeSubMenuText;
    @FXML
    Text recipesSubMenuText;
    @FXML
    Pane newRecipePane;
    @FXML
    TextField newRecipePortions;
    @FXML
    ListView<Ingredient> recipesListView;
    @FXML
    TextField newRecipeNameIngredientInput;
    @FXML
    TextField newRecipeAmountInput;
    @FXML
    ComboBox<Units> newRecipeUnitComboBox;
    @FXML
    Button newRecipeAddButton;
    @FXML
    TextArea instructionTextArea;
    @FXML
    ListView<String> instructionsListView;
    @FXML
    TextField newRecipeRecipeName;
    @FXML
    TextField newRecipeMinutes;
    @FXML
    TextArea newRecipeRecipeDescription;

    @FXML
    void initialize() {
        recipesListView.setItems(newRecipeIngredients);
        instructionsListView.setItems(newRecipeInstructions);
    }

    @FXML
    void handleChangeToNewRecipe() {
        clearRecipeFields();
        changeSubScene("newRecipe");
    }

    @FXML
    void handleChangeToRecipes() {
        changeSubScene("recipes");
    }

    @FXML
    void handleNewRecipeAddIngredient() {
        try {
            String amountText = newRecipeAmountInput.getText();
            String unit = newRecipeUnitComboBox.getSelectionModel().getSelectedItem().toString();
            String name = newRecipeNameIngredientInput.getText();
            Ingredient i = new Ingredient(new Quantity(Double.parseDouble(amountText), unit), name);
            Button button = createDeleteButton(i, newRecipeIngredientsButtons);
            button.setLayoutX(29);
            button.setLayoutY(newRecipeIngredients.size() * 30 + 265);
            newRecipeIngredients.add(i);
            newRecipeNameIngredientInput.clear();
            newRecipeAmountInput.clear();
        } catch (NumberFormatException e) {
            FeedbackHandler.showMessage(msgPane, "Quantityfield is empty", 'E');
        } catch (NullPointerException e) {
            FeedbackHandler.showMessage(msgPane, "Unit is not selected", 'E');
        } catch (IllegalArgumentException e) {
            FeedbackHandler.showMessage(msgPane, e.getMessage(), 'E');
        }
    }

    @FXML
    void handleAddInstruction() {
        if (!instructionTextArea.getText().isEmpty()) {
            String inst = instructionTextArea.getText();
            Button button = createDeleteButton(inst, newRecipeInstructionsButtons);
            button.setLayoutX(375);
            button.setLayoutY(newRecipeInstructions.size() * 30 + 265);
            newRecipeInstructions.add(inst);
            instructionTextArea.clear();
        } else {
            FeedbackHandler.showMessage(msgPane, "Instruction can't be empty", 'E');
        }
    }

    @FXML
    void handleAddRecipe() {
        try {
            double portions = Double.parseDouble(newRecipePortions.getText());
            int minutes = Integer.parseInt(newRecipeMinutes.getText());

            Metadata md = new Metadata(user.getUsername(),
                portions,
                newRecipeRecipeName.getText(),
                newRecipeRecipeDescription.getText(),
                minutes);

            IngredientContainer ic =
                new IngredientContainer(new ArrayList<>(this.newRecipeIngredients));
            RecipeInstructions rc =
                new RecipeInstructions(new ArrayList<>(this.newRecipeInstructions));
            Recipe recipe = new Recipe(ic, rc, md);

            this.user.getRecipeContainer().addItem(recipe);
            updateRecipeAnchorPane(recipe);
            dinneriumAccess.postRecipe(user.getUsername(), recipe);

            clearRecipeFields();
            changeSubScene("recipes");
        } catch (NumberFormatException e) {
            FeedbackHandler.showMessage(msgPane, "Quantiy or minutes invalid", 'E');
        } catch (IllegalArgumentException e) {
            FeedbackHandler.showMessage(msgPane, e.getMessage(), FeedbackHandler.ERROR);
        }
    }

    private void removeItem(Object item, Button button) {
        if (item instanceof String) {
            newRecipeInstructions.remove(item);
            placeButtonsCorrectly(button, newRecipeInstructionsButtons);
        } else if (item instanceof Ingredient) {
            newRecipeIngredients.remove(item);
            placeButtonsCorrectly(button, newRecipeIngredientsButtons);
        }
        newRecipePane.getChildren().remove(button);
    }

    private Button createDeleteButton(Object object, List<Button> buttonList) {
        Button button = new Button();
        button.setText("X");
        button.setOnMouseClicked(click -> removeItem(object, button));
        button.setPrefHeight(15);
        newRecipePane.getChildren().add(button);
        buttonList.add(button);
        //Adds an id to the buttons for testing purposes.
        button.setId((object instanceof String ? "deleteInst_" : "deleteIng_") + buttonList.size());
        return button;
    }

    private void placeButtonsCorrectly(Button button, List<Button> buttonsList) {
        buttonsList.remove(button);
        int index = 0;
        for (Button b : buttonsList) {
            b.setLayoutY(index * 30 + 265);
            index++;
        }
    }

    private void removeDeleteButtons() {
        for (Button b : newRecipeInstructionsButtons) {
            newRecipePane.getChildren().remove(b);
        }
        for (Button b : newRecipeIngredientsButtons) {
            newRecipePane.getChildren().remove(b);
        }
    }

    private void clearRecipeFields() {
        newRecipeRecipeName.clear();
        newRecipeNameIngredientInput.clear();
        newRecipeAmountInput.clear();
        newRecipeMinutes.clear();
        newRecipePortions.clear();
        newRecipeRecipeDescription.clear();
        instructionTextArea.clear();
        newRecipeIngredients.clear();
        newRecipeInstructions.clear();
        removeDeleteButtons();
    }

    private void updateRecipeAnchorPane(Recipe recipe) {
        Pane pane = PaneCreator.createRecipePane(recipe, recipesAnchorPane);
        pane.setOnMouseClicked(click -> showRecipeInformation(recipe));
    }

    private void showRecipeInformation(Recipe recipe) {
        Button button = PaneCreator.showRecipeInformation(recipe, recipesAnchorPane);
        button.setOnAction(click -> showUserRecipes());
    }

    private void changeSubScene(String newSubScene) {
        newRecipeSubMenuText.setFill(
            Color.valueOf(newSubScene.equals("newRecipe") ? "#f4c20d" : "#ebe8bf"));
        recipesSubMenuText.setFill(
            Color.valueOf(newSubScene.equals("recipes") ? "#f4c20d" : "#ebe8bf"));
        newRecipePane.setVisible(newSubScene.equals("newRecipe"));
        recipesScrollPane.setVisible(newSubScene.equals("recipes"));
    }

    void showUserRecipes() {
        recipesAnchorPane.getChildren().clear();
        for (Recipe recipe : user.getRecipeContainer()) {
            updateRecipeAnchorPane(recipe);
        }
    }

    void setScene(Boolean thisScene) {
        if (newRecipeUnitComboBox.getItems().size() == 0) {
            try {
                newRecipeUnitComboBox.getItems().setAll(dinneriumAccess.getUnits());
            } catch (IllegalArgumentException e) {
                FeedbackHandler.showMessage(msgPane, e.getMessage(), FeedbackHandler.ERROR);
            }
        }
        recipesPane.setVisible(thisScene);
    }

    void setUser(User user) {
        this.user = user;
    }

    void setMsgPane(Pane msgPane) {
        this.msgPane = msgPane;
    }

    void setDinneriumAccess(DinneriumAccess dinneriumAccess) {
        this.dinneriumAccess = dinneriumAccess;
    }


    //getters for testing purposes

    public ObservableList<Ingredient> getNewRecipeIngredients() {
        return newRecipeIngredients;
    }

    public ObservableList<String> getNewRecipeInstructions() {
        return newRecipeInstructions;
    }

    public List<Button> getNewRecipeIngredientsButtons() {
        return newRecipeIngredientsButtons;
    }

    public List<Button> getNewRecipeInstructionsButtons() {
        return newRecipeInstructionsButtons;
    }
}
