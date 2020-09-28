package dinnerium.ui;

import dinnerium.core.*;
import dinnerium.json.HandlePersistency;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AppController {

    // contains our current stock of ingredients/groceries
    private IngredientContainer ingredientContainer;
    private RecipeContainer recipeContainer = new RecipeContainer();
    private ObservableList<Ingredient> newRecipeIngredients = FXCollections.observableArrayList();
    private ObservableList<String> newRecipeInstructions = FXCollections.observableArrayList();

    @FXML
    TextField nameInput;
    @FXML
    TextField amountInput;
    @FXML
    Text errorOutput;
    @FXML
    ComboBox<String> unitComboBox;
    @FXML
    Button addButton;
    @FXML
    TableView<Ingredient> ingredientTableView;
    @FXML
    TableColumn<Ingredient, Quantity> quantityColumn;
    @FXML
    TableColumn<Ingredient, String> itemColumn;
    @FXML
    Text settingsText;
    @FXML
    Text fridgeText;
    @FXML
    Text yourRecipesText;
    @FXML
    ScrollPane recipesScrollPane;
    @FXML
    Pane fridgePane;
    @FXML
    Pane settingsPane;
    @FXML
    Pane recipesPane;
    @FXML
    ListView<Ingredient> recipesListView;
    @FXML
    TextField newRecipeNameInput;
    @FXML
    TextField newRecipeAmountInput;
    @FXML
    ComboBox<String> newRecipeUnitComboBox;
    @FXML
    TextArea instructionTextArea;
    @FXML
    ListView<String> instructionsListView;
    @FXML
    TextField portionsInput;
    @FXML
    Text recipesSubMenuText;
    @FXML
    Text newRecipeSubMenuText;
    @FXML
    Pane newRecipePane;
    @FXML
    AnchorPane recipesAnchorPane;


    @FXML
    void initialize() throws Exception {
        recipesListView.setItems(newRecipeIngredients);
        instructionsListView.setItems(newRecipeInstructions);
        setup();
    }

    // need to handle throws from init!!
    private void setup() {
        // sets up our tableview with correct rows and columns

        unitComboBox.getItems().setAll(Quantity.units);
        newRecipeUnitComboBox.getItems().setAll(Quantity.units);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // loading our stock of ingredients (populating (?) our ingredientContainer)
        try {
            this.ingredientContainer = HandlePersistency.loadDataFromFile();
        } catch (IOException e) {
            this.ingredientContainer = new IngredientContainer();
        }
        updateTableView();
    }

    //Adds the ingreident to the ingreidentContainer first and then updates the tableview.
    //The saves the new ingredientContainer to the file, if an error occurs it throws an IAE
    @FXML
    private void handleAddIngredient() {
        addIngredient(false);
        updateTableView();
        // writes our new ingredient to the file
        try {
            HandlePersistency.writeJsonToFile(ingredientContainer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write to file");
        }
    }

    @FXML
    private void handleNewRecipeAddIngredient() {
        addIngredient(true);
    }

    //Adds the new ingreident to either the newRecipeIngredients list (if newRecipe is true)
    //Or adds the new ingredient to the ingredientContainer elsewise.
    //where it gets the values from is decided by if newRecipe is true or not.
    //If an IAE is thrown due to an error in the input values it writes the error message caught
    //to the errorOutput textField.
    private void addIngredient(boolean newRecipe) {
        try {
            String amountText = (newRecipe ? newRecipeAmountInput : amountInput).getText();
            String unit = (newRecipe ? newRecipeUnitComboBox : unitComboBox)
                    .getSelectionModel()
                    .getSelectedItem();
            String name = (newRecipe ? newRecipeNameInput : nameInput).getText();
            Ingredient i = new Ingredient(new Quantity(Double.valueOf(amountText), unit), name);

            if (newRecipe) {
                newRecipeIngredients.add(i);
            } else {
                this.ingredientContainer.addItem(i);
            }
        } catch (IllegalArgumentException e) {
            // write error output in app
            errorOutput.setVisible(true);
            errorOutput.setText(e.getMessage());
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
                errorOutput.setVisible(false);
            });
        }
    }

    @FXML
    private void handleAddInstruction() {
        this.newRecipeInstructions.add(instructionTextArea.getText());
    }

    @FXML
    private void handleAddRecipe() {
        //Change username with name of the user logged in to the app when User class is ready
        Metadata metadata = new Metadata("username", Double.valueOf(portionsInput.getText()));
        IngredientContainer ic = new IngredientContainer(this.newRecipeIngredients);
        RecipeInstructions rc = new RecipeInstructions(this.newRecipeInstructions);

        recipeContainer.addItem(new Recipe(ic, rc, metadata));
        updateRecipeAnchorPane();
    }

    //Sende inn en recipe som man henter ut all infoen fra, slik at man kan hente ut infoen fra den.
    //Så blir det lettere å initializere appen fra en fil med recipes.
    private void updateRecipeAnchorPane() {
        Pane pane = new Pane();
        pane.setPrefWidth(522);
        pane.setPrefHeight(167);
        //Need to set layout x and y to a value calculated from the amount of recipes.

        Text recipeName = new Text("Inser name here");
        recipeName.getStyleClass().add("recipe-name");
        recipeName.setLayoutX(1);
        recipeName.setLayoutY(25);

        Pane childPane = new Pane();
        childPane.setPrefHeight(135);
        childPane.setPrefWidth(522);
        childPane.setLayoutY(30);
        childPane.getStyleClass().add("child-pane");

        /* Can't figure out how to make imageView with image from the web
        URL img = new URL("http://folk.ntnu.no/anderobs/images/tikkaMasala.png");
        ImageIcon image = new ImageIcon(img);
        ImageView imageView = new ImageView(image);
        //how to set the imageView URL ?
        imageView.setSize(105, 105);
        */
        Text recipeInfo = new Text("4 ingredients missing  |  1 hour 56 mins  |  70kr");
        recipeInfo.setLayoutY(30);
        recipeInfo.setLayoutX(127);
        recipeInfo.getStyleClass().add("recipe-info");
        recipeInfo.setWrappingWidth(390);

        Text recipeDescription = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing"
                + " elit. Proin vel felis pharetra, ornare nisi at, egestas sapien. Aliquam non "
                + "faucibus nisi. Curabitur scelerisque orci nulla, dapibus pretium lorem pulvinar "
                + "ac. Suspendisse sit amet arcu finibus, interdum odio eget, mattis mi. Fusce "
                + "imperdiet nisl sed dolor bibendum luctus. Nunc");
        recipeDescription.setWrappingWidth(370);
        recipeDescription.getStyleClass().add("recipe-description");
        recipeDescription.setLayoutX(132);
        recipeDescription.setLayoutY(52);

        childPane.getChildren().addAll(recipeInfo, recipeDescription);

        pane.getChildren().addAll(recipeName, childPane);
        pane.setLayoutY(13 + 180 * (recipeContainer.getContainerSize() - 1));
        pane.setLayoutX(10);
        recipesAnchorPane.getChildren().add(pane);
        if (recipesAnchorPane.getChildren().size() > 3) {
            recipesAnchorPane.setPrefHeight(13 + 180 * (recipeContainer.getContainerSize()));
        }
    }

    @FXML
    private void handleChangeToFridge() {
        changeScene("fridge");
    }

    @FXML
    private void handleChangeToYourRecipes() {
        newRecipeIngredients.clear();
        newRecipeInstructions.clear();
        changeScene("recipes");
    }

    @FXML
    private void handleChangeToSettings() {
        changeScene("settings");
    }

    //Changes between the scenes of the application, and sets the color of the headertext
    private void changeScene(String newScene) {
        settingsText.setFill(Color.valueOf(newScene.equals("settings") ? "#f4c20d" : "#ebe8bf"));
        fridgeText.setFill(Color.valueOf(newScene.equals("fridge") ? "#f4c20d" : "#ebe8bf"));
        yourRecipesText.setFill(Color.valueOf(newScene.equals("recipes") ? "#f4c20d" : "#ebe8bf"));

        settingsPane.setVisible(newScene.equals("settings"));
        fridgePane.setVisible(newScene.equals("fridge"));
        recipesPane.setVisible(newScene.equals("recipes"));
    }

    @FXML
    private void handleChangeToRecipes() {
        changeSubScene("recipes");
    }

    @FXML
    private void handleChangeToNewRecipe() {
        changeSubScene("newRecipe");
    }

    //Changes between subscenes in the recipe scene
    private void changeSubScene(String newSubScene) {
        newRecipeSubMenuText.setFill(
                Color.valueOf(newSubScene.equals("newRecipe") ? "#f4c20d" : "#ebe8bf"));
        recipesSubMenuText.setFill(
                Color.valueOf(newSubScene.equals("recipes") ? "#f4c20d" : "#ebe8bf"));

        newRecipePane.setVisible(newSubScene.equals("newRecipe"));
        recipesScrollPane.setVisible(newSubScene.equals("recipes"));
    }

    // updates our tableView with an observable list
    private void updateTableView() {
        ObservableList<Ingredient> observableList =
                FXCollections.observableArrayList((this.ingredientContainer.getContainer()));
        ingredientTableView.setItems(observableList);
    }

}
