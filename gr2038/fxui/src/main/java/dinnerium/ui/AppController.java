package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Quantity;
import dinnerium.core.Recipe;
import dinnerium.json.HandlePersistency;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AppController {

    // contains our current stock of ingredients/groceries
    private IngredientContainer ingredientContainer;
    private ObservableList<Ingredient> newRecipeIngredients = FXCollections.observableArrayList();

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
    TextField newRecipe_nameInput;
    @FXML
    TextField newRecipe_amountInput;
    @FXML
    ComboBox<String> newRecipe_unitComboBox;


    @FXML
    void initialize() throws Exception {
        recipesListView.setItems(newRecipeIngredients);
        setup();
    }

    // need to handle throws from init!!
    private void setup() {
        // sets up our tableview with correct rows and columns

        unitComboBox.getItems().setAll(Quantity.units);
        newRecipe_unitComboBox.getItems().setAll(Quantity.units);
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

    @FXML
    private void handleAddIngredient() {

        try {
            Quantity quantity =
                    new Quantity(Double.valueOf(amountInput.getText()),
                            unitComboBox.getSelectionModel().getSelectedItem());
            Ingredient ingredient = new Ingredient(quantity, nameInput.getText());
            this.ingredientContainer.addIngredient(ingredient);
        } catch (IllegalArgumentException e) {
            // write error output in app
            errorOutput.setVisible(true);
            errorOutput.setText(e.getMessage());
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
                errorOutput.setVisible(false);
            });
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
        }
        updateTableView();
        // writes our new ingredient to the file
        try {
            HandlePersistency.writeJsonToFile(ingredientContainer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write to file");
        }

    }

    @FXML
    private void handleChangeToFridge() {
        changeScene("fridge");
    }

    @FXML
    private void handleChangeToYourRecipes() {
        newRecipeIngredients.clear();
        changeScene("recipes");
    }
    @FXML
    private void handleNewRecipeAddIngredient() {
        try {
            Quantity quantity =
                    new Quantity(Double.valueOf(newRecipe_amountInput.getText()),
                            newRecipe_unitComboBox.getSelectionModel().getSelectedItem());
            Ingredient ingredient = new Ingredient(quantity, newRecipe_nameInput.getText());
            this.newRecipeIngredients.add(ingredient);
        } catch (IllegalArgumentException e) {
            // write error output in app
            errorOutput.setVisible(true);
            errorOutput.setText(e.getMessage());
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
                errorOutput.setVisible(false);
            });
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleChangeToSettings() {
        changeScene("settings");
    }

    private void changeScene(String newScene) {
        settingsText.setFill(Color.valueOf(newScene.equals("settings") ? "#f4c20d" : "#ebe8bf"));
        fridgeText.setFill(Color.valueOf(newScene.equals("fridge") ? "#f4c20d" : "#ebe8bf"));
        yourRecipesText.setFill(Color.valueOf(newScene.equals("recipes") ? "#f4c20d" : "#ebe8bf"));

        settingsPane.setVisible(newScene.equals("settings"));
        fridgePane.setVisible(newScene.equals("fridge"));
        recipesPane.setVisible(newScene.equals("recipes"));
    }

    // updates our tableView with an observable list
    private void updateTableView() {
        ObservableList<Ingredient> observableList =
                FXCollections.observableArrayList((this.ingredientContainer.getIngredients()));
        ingredientTableView.setItems(observableList);
    }

}
